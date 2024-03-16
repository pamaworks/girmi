import { deleteBoard, deleteBoardType, getBoard, getBoardList, getBoardTypeList, saveBoard, saveBoardType } from '@/apis/board';
import { router } from '@/router';
import type { Board, BoardType } from '@/types/models/board';
import { defineStore } from 'pinia';

export const useBoardStore = defineStore({
  id: 'board',
  state: () => ({
    boardList: [] as Board[],
    board: {} as Board,
    loadingBoardList: false as boolean,
    isOpenBrdTypeModal: false as boolean,
    boardTypeList: [] as BoardType[]
  }),
  actions: {
    async getBoardList() {
      this.loadingBoardList = true;
      this.boardList = await getBoardList();
      this.loadingBoardList = false;
    },
    async getBoard(brdIdx: number) {
      this.board = await getBoard(brdIdx);
    },
    async saveBoard(board: Board, brdIdx: any) {
      const data = board;
      data.useYn = 'Y';
      if (brdIdx !== 'write') {
        data.brdIdx = brdIdx;
      }

      const boardType = {
        brdType: board.brdType,
        brdNm: ''
      } as BoardType;

      data.boardType = boardType;

      saveBoard(data as Board)
        .then((res: any) => {
          if (res) {
            router.push({ name: 'BoardList' });
          }
        })
        .catch((err: any) => alert('error'));
    },
    async deleteBoard(brdIdx: number) {
      deleteBoard(brdIdx)
        .then((res: any) => {
          if (res === 200) {
            this.getBoardList();
          }
        })
        .catch((e) => alert('error'));
    },
    async getBoardTypeList() {
      this.boardTypeList = await getBoardTypeList();
    },
    async saveBoardType(boardType: BoardType) {
      saveBoardType(boardType)
        .then((res) => {
          if (res === 200) {
            this.getBoardTypeList();
          }
        })
        .catch((err) => alert('error'));
    },
    async deleteBoardType(brdType: string) {
      deleteBoardType(brdType)
        .then((res) => {
          if (res === 200) {
            this.getBoardTypeList();
          }
        })
        .catch((err) => alert('error'));
    }
  }
});
