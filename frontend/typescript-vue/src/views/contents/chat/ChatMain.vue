<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Chat">
        <v-row>
          <v-col cols="12" md="12">
            <v-card title="Chatting Room" width="100%">
              <v-col cols="12" md="3">
                <v-text-field ref="user" name="user" label="User Name" :rules="[rules.user]" v-model="useChat.user"></v-text-field>
              </v-col>
              <v-list lines="one" v-if="useChat.roomList.length == 0">
                <v-list-item>Empty</v-list-item>
              </v-list>
              <v-list lines="one">
                <v-list-item v-for="(room, index) in useChat.roomList" :key="index" @click="goChatRoom(room)">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-home"></v-icon>
                  </template>
                  <v-list-item-title v-text="room.roomNm"></v-list-item-title>
                </v-list-item>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="3">
            <v-text-field name="roomNm" label="Room Name" :rules="[rules.roomNm]" v-model="useChat.roomNm"></v-text-field>
          </v-col>

          <v-col cols="12" md="3">
            <v-btn color="info" size="large" @click="createChatRoom()">Create</v-btn>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<script lang="ts">
import { ref, onMounted, computed, watch } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { useChatStore } from '@/stores/chat';
import { createChatRoom } from '@/apis/chat';
import type { ChatRoom } from '@/types/models/chat';
import { router } from '@/router';

const useChat = useChatStore();

export default {
  name: 'ChatMain',
  components: {
    BaseBreadcrumb,
    UiParentCard
  },
  setup() {
    const page = ref({ title: 'Chat' });
    const breadcrumbs = ref([
      {
        title: 'Contents',
        disabled: false,
        href: '#'
      },
      {
        title: 'Chat',
        disabled: true,
        href: '#'
      }
    ]);

    return {
      page,
      breadcrumbs
    };
  },
  methods: {
    createChatRoom() {
      if (useChat.roomNm) {
        createChatRoom(useChat.roomNm)
          .then((res: ChatRoom) => {
            if (res.roomId) {
              useChat.getChatRoomList();
            }
          })
          .catch((err) => alert('error'));
      }
    },
    goChatRoom(room: ChatRoom) {
      if (useChat.user !== '') {
        useChat.chatRoom = room;
        router.push('/contents/chat/room');
      } else {
        this.$refs['user'].focus();
      }
    }
  },
  data() {
    onMounted(() => {
      useChat.getChatRoomList();
    });
    const rules = {
      roomNm: (value: string) => !!value || 'input Room Name',
      user: (value: string) => !!value || 'input User Name'
    };
    return {
      useChat,
      rules
    };
  }
};
</script>
