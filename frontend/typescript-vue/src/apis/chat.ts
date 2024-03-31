import type { ChatRoom } from '@/types/models/chat';
import { apisServiceCommon, getCommonInstance } from './instance';

export async function getChatRoomList() {
  const result = await getCommonInstance(apisServiceCommon).get<ChatRoom[]>(`/chat/room/list`);
  return result.data;
}

export async function createChatRoom(roomNm: string) {
  const result = await getCommonInstance(apisServiceCommon).put<ChatRoom>(`/chat/room/create?roomNm=${roomNm}`);
  return result.data;
}

export async function isExistRoomUser(roomId: string, user: string) {
  const result = await getCommonInstance(apisServiceCommon).get<boolean>(`/chat/room/${roomId}/${user}`);
  return result.data;
}
