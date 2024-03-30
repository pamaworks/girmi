export type ChatRoom = {
  roomId: string;
  roomNm: string;
};

export type ChatMessage = {
  roomId: string;
  sender: string;
  message: string;
};
