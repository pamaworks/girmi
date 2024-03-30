<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Board Edit">
        <v-form>
          <div>
            <v-select
              name="brdType"
              :items="useBoard.boardTypeList"
              item-title="brdNm"
              item-value="brdType"
              v-model="brdType.value"
              density="compact"
              label="Board Type"
            ></v-select>
          </div>
          <div>
            <v-text-field name="brdTitle" v-model="brdTitle.value"></v-text-field>
          </div>
          <div>
            <v-textarea name="brdContent" v-model="brdContent.value"></v-textarea>
          </div>
          <v-btn variant="outlined" @click="registBoard">Save</v-btn>
          <v-btn variant="outlined" class="float-right" :to="{ name: 'BoardList' }">List</v-btn>
        </v-form>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<script lang="ts">
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { ref, onMounted } from 'vue';
import { getBoard } from '@/apis/board';
import { useField, useForm } from 'vee-validate';
import { type Board } from '@/types/models/board';
import { useBoardStore } from '@/stores/board';
const useBoard = useBoardStore();
export default {
  name: 'BoardEdit',
  props: ['brdIdx'],
  methods: {},
  components: {
    BaseBreadcrumb,
    UiParentCard
  },
  setup(props: any) {
    const page = ref({ title: 'Board' });
    const breadcrumbs = ref([
      {
        title: 'Contents',
        disabled: false,
        href: '#'
      },
      {
        title: 'Board',
        disabled: true,
        href: '#'
      }
    ]);

    const { handleSubmit } = useForm({
      validationSchema: {
        brdType(value: string) {
          if (value) return true;

          return 'Please Select Post Type';
        },
        brdTitle(value: string) {
          if (value) return true;

          return 'Please input board title';
        },
        brdContent(value: string) {
          if (value) return true;

          return 'Please input board content';
        }
      }
    });
    const brdType = useField('brdType');
    const brdTitle = useField('brdTitle');
    const brdContent = useField('brdContent');

    const registBoard = handleSubmit((board: any) => {
      useBoard.saveBoard(board, props.brdIdx);
    });

    return {
      page,
      breadcrumbs,
      BaseBreadcrumb,
      UiParentCard,
      brdType,
      brdTitle,
      brdContent,
      registBoard
    };
  },
  data(props: any) {
    const brdTitle = useField('brdTitle');
    const brdContent = useField('brdContent');
    const brdType = useField('brdType');
    onMounted(() => {
      const brdIdx = props.brdIdx;
      if (brdIdx !== 'write') {
        getBoard(brdIdx).then((board: Board) => {
          brdTitle.value.value = board.brdTitle;
          brdContent.value.value = board.brdContent;
          brdType.value.value = board.boardType.brdType;
        });
      }
      useBoard.getBoardTypeList();
    });

    return {
      brdTitle,
      brdType,
      brdContent,
      useBoard
    };
  }
};
</script>
