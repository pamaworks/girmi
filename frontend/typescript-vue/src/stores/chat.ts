import { getChatRoomList } from '@/apis/chat';
import type { ChatMessage, ChatRoom } from '@/types/models/chat';
import { defineStore } from 'pinia';

export const useChatStore = defineStore({
  id: 'chat',
  state: () => ({
    roomList: [] as ChatRoom[],
    user: '' as string,
    roomNm: '' as string,
    chatRoom: {} as ChatRoom,
    recvList: [] as ChatMessage[]
  }),
  actions: {
    async getChatRoomList() {
      this.roomList = await getChatRoomList();
    }
  }
});
