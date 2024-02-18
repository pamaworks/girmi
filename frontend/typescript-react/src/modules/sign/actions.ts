import { createAsyncAction } from 'typesafe-actions';
import { AxiosError } from 'axios';
import { Sign, User } from '../../models/sign';

export const SIGNIN = 'SIGNIN';
export const SIGNIN_SUCCESS = 'SIGNIN_SUCCESS';
export const SIGNIN_ERROR = 'SIGNIN_ERROR';

export const signinUserAsync = createAsyncAction(SIGNIN, SIGNIN_SUCCESS, SIGNIN_ERROR)<FormData, Sign, AxiosError>();

export const SIGNUP = 'SIGNUP';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const SIGNUP_ERROR = 'SIGNUP_ERROR';

export const signupUserAsync = createAsyncAction(SIGNUP, SIGNUP_SUCCESS, SIGNUP_ERROR)<FormData, boolean, AxiosError>();

export const USERINFO = 'USERINFO';
export const USERINFO_SUCESS = 'USERINFO_SUCESS';
export const USERINFO_ERROR = 'USERINFO_ERROR';

export const userInfoAsync = createAsyncAction(USERINFO, USERINFO_SUCESS, USERINFO_ERROR)<null, User, AxiosError>();
