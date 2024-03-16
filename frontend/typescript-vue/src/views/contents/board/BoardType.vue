<template>
  <v-dialog v-model="useBoard.isOpenBrdTypeModal" width="auto">
    <v-card max-width="400" prepend-icon="mdi-update" title="Board Type">
      <v-row>
        <v-col cols="12">
          <v-text-field v-model="boardType.brdType" label="Board Type"></v-text-field>
          <v-text-field v-model="boardType.brdNm" label="Board Name"></v-text-field>
          <v-btn @click="addBrdType">Add</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-table density="compact" class="h-100">
            <thead>
              <tr>
                <th class="text-left">Board Type</th>
                <th class="text-left">Board Name</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in useBoard.boardTypeList" :key="item.brdType">
                <td>
                  <router-link :to="{ name: 'BoardEdit', params: { brdIdx: item.brdType } }">
                    {{ item.brdType }}
                  </router-link>
                </td>
                <td>
                  <router-link :to="{ name: 'BoardEdit', params: { brdIdx: item.brdType } }">
                    {{ item.brdNm }}
                  </router-link>
                </td>
                <td>
                  <v-btn density="compact" icon="mdi-delete-circle" @click="deleteBrdType(item.brdType)"> </v-btn>
                </td>
              </tr>
            </tbody>
          </v-table>
        </v-col>
      </v-row>
      <template v-slot:actions>
        <v-btn class="ms-auto" text="Close" @click="useBoard.isOpenBrdTypeModal = false"></v-btn>
      </template>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { computed, onMounted, ref } from 'vue';
import { useBoardStore } from '@/stores/board';
import type { BoardType } from '@/types/models/board';
const useBoard = useBoardStore();
export default {
  name: 'BoardType',
  setup() {
    const boardType = ref({ brdType: '', brdNm: '' } as BoardType);
    const addBrdType = () => {
      if (boardType.value.brdType === '' || boardType.value.brdNm === '') {
        return;
      }
      useBoard.saveBoardType(boardType.value);
    };
    const deleteBrdType = (brdType: string) => {
      useBoard.deleteBoardType(brdType);
    };
    return { useBoard, boardType, addBrdType, deleteBrdType };
  },
  data() {
    onMounted(() => {
      useBoard.getBoardTypeList();
    });
    return { useBoard };
  }
};
</script>
