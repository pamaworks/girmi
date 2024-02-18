import { signin, signup, userInfo } from '../../apis/sign';
import createAsyncThunk from '../../lib/createAsyncThunk';
import { signinUserAsync, signupUserAsync, userInfoAsync } from './actions';

export const signinThunk = createAsyncThunk(signinUserAsync, signin);

export const signupThunk = createAsyncThunk(signupUserAsync, signup);

export const userInfoThunk = createAsyncThunk(userInfoAsync, userInfo);
