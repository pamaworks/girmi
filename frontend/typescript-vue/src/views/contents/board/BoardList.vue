<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Board">
        <v-row>
          <v-col>
            <v-row>
              <v-col cols="12">
                <v-btn style="float: right" @click="useBoard.isOpenBrdTypeModal = true">Board Type</v-btn>
                <board-type></board-type>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-table density="compact" class="h-100" style="width: 100%">
                  <thead>
                    <tr>
                      <th class="text-left">Board Type</th>
                      <th class="text-left">Title</th>
                      <th>Delete</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="item in useBoard.boardPaging.boardList" :key="item.brdIdx">
                      <td>
                        <router-link :to="{ name: 'BoardEdit', params: { brdIdx: item.brdIdx } }">
                          {{ item.boardType.brdNm }}
                        </router-link>
                      </td>
                      <td>
                        <router-link :to="{ name: 'BoardEdit', params: { brdIdx: item.brdIdx } }">
                          {{ item.brdTitle }}
                        </router-link>
                      </td>
                      <td>
                        <v-btn density="compact" icon="mdi-delete-circle" @click="deleteBoard(itembrdIdx)"> </v-btn>
                      </td>
                    </tr>
                  </tbody>
                </v-table>
                <v-overlay :model-value="useBoard.loadingBoardList" class="align-center justify-center" scrim="#C0C0C0" contained>
                  <v-progress-circular color="primary" size="64" indeterminate></v-progress-circular>
                </v-overlay>
              </v-col>
            </v-row>
            <r-row>
              <v-col>
                <v-pagination
                  v-model="useBoard.param.pageNo"
                  :length="useBoard.boardPaging.page ?? 0"
                  rounded="circle"
                  @update:model-value="goPage"
                ></v-pagination>
              </v-col>
            </r-row>
            <v-row>
              <v-col>
                <v-btn variant="outlined" class="float-right" @click="addBtn">Add</v-btn>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style>
a {
  text-decoration: none;
  color: 'black';
}
</style>

<script lang="ts">
import { ref, onMounted, computed, watch } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import BoardType from './BoardType.vue';
import { useBoardStore } from '@/stores/board';
import type { Board } from '@/types/models/board';
const useBoard = useBoardStore();
export default {
  name: 'BoardList',
  components: {
    BaseBreadcrumb,
    UiParentCard,
    BoardType
  },
  methods: {
    reload() {},
    goPage(pageNo: number) {
      useBoard.param.pageNo = pageNo;
      useBoard.getBoardList();
    },
    addBtn() {
      this.$router.push({ name: 'BoardEdit', params: { brdIdx: 'write' } });
    },
    deleteBoard(brdIdx: number) {
      useBoard.deleteBoard(brdIdx);
    }
  },
  setup() {
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

    return {
      page,
      breadcrumbs
    };
  },
  data() {
    onMounted(() => {
      useBoard.getBoardList();
    });

    return {
      useBoard
    };
  }
};
</script>
