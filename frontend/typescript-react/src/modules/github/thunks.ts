import { getUserProfile } from '../../apis/github';
import { getUserProfileAsync } from './actions';
import createAsyncThunk from '../../lib/createAsyncThunk';

export const getUserProfileThunk = createAsyncThunk(getUserProfileAsync, getUserProfile)
/* 
export function getUserProfileThunk(username: string): ThunkAction<void, RootState, null, GithubAction> {
  return async dispatch => {    
    const { request, success, failure } = getUserProfileAsync;
    dispatch(request());
    try {
      const userProfile = await getUserProfile(username);
      dispatch(success(userProfile));
    } catch (e) {
      dispatch(failure(e as any));
    }
  };
} */