import type { Board } from '@/types/models/board';
import { getCommonInstance } from './instance';

const apisDataJpa = 'dataJpa';

export async function getBoardList() {
  const result = await getCommonInstance(apisDataJpa).get<Board[]>('/board/list');
  return result.data;
}

export async function saveBoard(board: Board) {
  const result = await getCommonInstance(apisDataJpa).post('/board/save', JSON.stringify(board));
  return result.data;
}

export async function getBoard(brdIdx: number) {
  const result = await getCommonInstance(apisDataJpa).get(`/board/${brdIdx}`);
  return result.data;
}

export async function deleteBoard(brdIdx: number) {
  const reuslt = await getCommonInstance(apisDataJpa).delete(`/board/${brdIdx}/delete`);
  return reuslt.status;
}
