export type ChatRoom = {
  roomId: string;
  roomNm: string;
  userCnt: number;
};

export type ChatMessage = {
  roomId: string;
  sender: string;
  message: string;
  messageType: string;
};
