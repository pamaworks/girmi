<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Board Edit">
        <v-form>
          <div>
            <v-select :items="brdTypes" v-model="brdType.value" density="compact" label="Board Type"></v-select>
          </div>
          <div>
            <v-text-field v-model="brdTitle.value"></v-text-field>
          </div>
          <div>
            <v-textarea v-model="brdContent.value"></v-textarea>
          </div>
          <v-btn variant="outlined" @click="registBoard">Save</v-btn>
          <v-btn variant="outlined" class="float-right" :to="{ name: 'BoardList' }">List</v-btn>
        </v-form>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<script lang="ts">
import { ref, onMounted } from 'vue';
import { router } from '@/router';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { getBoard, saveBoard } from '@/apis/board';
import { useField, useForm } from 'vee-validate';
import { type Board } from '@/types/models/board';

export default {
  name: 'BoardEdit',
  props: ['brdIdx'],
  methods: {},
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

    const brdTypes = ref(['MAINPOST', 'FEATUREPOST']);

    const registBoard = handleSubmit((values: any) => {
      const data: Board = values;
      data.useYn = 'Y';
      if (props.brdIdx !== 'write') {
        data.brdIdx = props.brdIdx;
      }
      saveBoard(data as Board)
        .then((res: any) => {
          if (res) {
            router.push({ name: 'BoardList' });
          }
        })
        .catch((err: any) => alert('error'));
    });

    return {
      page,
      breadcrumbs,
      BaseBreadcrumb,
      UiParentCard,
      brdType,
      brdTitle,
      brdContent,
      brdTypes,
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
        getBoard(brdIdx).then((res: Board) => {
          brdTitle.value.value = res.brdTitle;
          brdContent.value.value = res.brdContent;
          brdType.value.value = res.brdType;
        });
      }
    });

    return { brdTitle, brdContent, brdType };
  }
};
</script>
