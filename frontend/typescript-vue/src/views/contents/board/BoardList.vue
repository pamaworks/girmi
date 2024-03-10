<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Board">
        <v-table density="compact" class="h-100">
          <thead>
            <tr>
              <th class="text-left">Board Type</th>
              <th class="text-left">Title</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in boardList" :key="item.brdIdx">
              <td>
                <router-link :to="{ name: 'BoardEdit', params: { brdIdx: item.brdIdx } }">
                  {{ item.brdType }}
                </router-link>
              </td>
              <td>
                <router-link :to="{ name: 'BoardEdit', params: { brdIdx: item.brdIdx } }">
                  {{ item.brdTitle }}
                </router-link>
              </td>
              <td>
                <v-btn density="compact" icon="mdi-delete-circle" @click="deleteBoard(item.brdIdx)"> </v-btn>
              </td>
            </tr>
          </tbody>
        </v-table>
        <v-btn variant="outlined" class="float-right" @click="addBtn">Add</v-btn>
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
import { ref, onMounted, watch } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { deleteBoard, getBoardList } from '@/apis/board';
export default {
  name: 'BoardList',
  methods: {
    reload() {},
    addBtn() {
      this.$router.push({ name: 'BoardEdit', params: { brdIdx: 'write' } });
    },
    deleteBoard(brdIdx: number) {
      deleteBoard(brdIdx).then((status: number) => {
        console.log(status);
        if (status == 200) {
          this.$router.go(0);
        }
      });
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

    return { page, breadcrumbs, BaseBreadcrumb, UiParentCard };
  },
  data() {
    const boardList = ref([]);

    onMounted(() => {
      getBoardList().then((res) => {
        boardList.value = res;
      });
    });

    return {
      boardList
    };
  }
};
</script>
