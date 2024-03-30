import type { ChatRoom } from '@/types/models/chat';
import { getCommonInstance } from './instance';

const apisServiceCommon = 'serviceCommon';

export async function getChatRoomList() {
  const result = await getCommonInstance(apisServiceCommon).get<ChatRoom[]>(`/chat/room/list`);
  return result.data;
}

export async function createChatRoom(roomNm: string) {
  const result = await getCommonInstance(apisServiceCommon).put<ChatRoom>(`/chat/room/create?roomNm=${roomNm}`);
  return result.data;
}
