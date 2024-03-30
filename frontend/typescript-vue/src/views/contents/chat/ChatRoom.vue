<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Chat">
        <v-row>
          <v-col cols="12" md="12">
            <v-card width="100%">
              <v-card-title>Chat Room ({{ useChat.chatRoom.roomNm }})</v-card-title>
              <v-list lines="one">
                <v-list-item v-for="(recv, index) in recvList" :key="index">
                  <v-list-item-title v-if="recv.sender === useChat.user">*나*</v-list-item-title>
                  <v-list-item-title v-if="recv.sender !== useChat.user">{{ recv.sender }}</v-list-item-title>
                  <v-list-item-subtitle>{{ recv.message }}</v-list-item-subtitle>
                </v-list-item>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="3">
            <v-text-field name="msg" label="Message" :rules="[rules.message]" v-model="message"></v-text-field>
          </v-col>

          <v-col cols="12" md="3">
            <v-btn color="info" size="large" @click="send()">Send</v-btn>
            <v-btn color="info" size="large" style="margin-left: 10px" :to="{ name: 'ChatMain' }">Back</v-btn>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<script lang="ts">
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import Stomp from 'webstomp-client';
import { useChatStore } from '@/stores/chat';
import { router } from '@/router';
import type { ChatMessage } from '@/types/models/chat';
const useChat = useChatStore();
export default {
  name: 'ChatRoom',
  components: {
    BaseBreadcrumb,
    UiParentCard
  },
  data() {
    const rules = {
      message: (value: string) => !!value || 'input Message'
    };

    return {
      useChat,
      rules,
      recvList: [] as ChatMessage[],
      massage: '',
      stompClient: Stomp.client as any,
      connected: false
    };
  },
  methods: {
    connect() {
      let websocket = new WebSocket('ws://localhost:8030/service-common/stomp/chat');
      this.stompClient = Stomp.over(websocket);
      this.stompClient.connect(
        {},
        (frame: any) => {
          console.log('connected');
          this.connected = true;
          this.stompClient.subscribe('/sub/chat/room/' + useChat.chatRoom.roomId, (res: any) => {
            this.recvList.push(JSON.parse(res.body) as ChatMessage);
          });
        },
        (error: any) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    send() {
      if (this.connected) {
        const messageInfo = {} as ChatMessage;
        messageInfo.sender = useChat.user;
        messageInfo.roomId = useChat.chatRoom.roomId;
        messageInfo.message = this.message;
        this.stompClient.send('/pub/chat/message', JSON.stringify(messageInfo));
      }
    }
  },
  setup() {
    if (useChat.chatRoom.roomId === undefined) {
      router.push({ name: 'ChatMain' });
    }

    const page = ref({ title: 'Chat Room' });
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
    const message = ref('');

    return {
      page,
      breadcrumbs,
      message
    };
  },
  created() {
    this.connect();
  }
};
</script>
