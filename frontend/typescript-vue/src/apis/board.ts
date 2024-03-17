import type { Board, BoardPaging, BoardType } from '@/types/models/board';
import { getCommonInstance } from './instance';

const apisDataJpa = 'dataJpa';

export async function getBoardList(param: any) {
  const result = await getCommonInstance(apisDataJpa).get<BoardPaging>(`/board/list?pageNo=${param.pageNo}&rowSize=${param.rowSize}`);
  return result.data;
}

export async function saveBoard(board: Board) {
  const result = await getCommonInstance(apisDataJpa).post('/board/save', JSON.stringify(board));
  return result.data;
}

export async function getBoard(brdIdx: number) {
  const result = await getCommonInstance(apisDataJpa).get<Board>(`/board/${brdIdx}`);
  return result.data;
}

export async function deleteBoard(brdIdx: number) {
  const reuslt = await getCommonInstance(apisDataJpa).delete(`/board/${brdIdx}/delete`);
  return reuslt.status;
}

export async function getBoardTypeList() {
  const result = await getCommonInstance(apisDataJpa).get<BoardType[]>('/board/type/list');
  return result.data;
}

export async function saveBoardType(param: any) {
  const result = await getCommonInstance(apisDataJpa).post('/board/type/save', JSON.stringify(param));
  return result.status;
}

export async function deleteBoardType(brdType: string) {
  const result = await getCommonInstance(apisDataJpa).delete(`/board/type/${brdType}/delete`);
  return result.status;
}
