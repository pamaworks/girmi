import type { ChatMessage } from '@/types/models/chat';

export function setChatMessage(roomId: string, sender: string, message: string, messageType: string = 'TALK') {
  const msg = {
    roomId,
    sender,
    message,
    messageType
  } as ChatMessage;

  return JSON.stringify(msg);
}
