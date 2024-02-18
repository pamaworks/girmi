import { createReducer } from 'typesafe-actions';
import { asyncState, createAsyncReducer, transformToArray } from '../../lib/reducerUtils';
import { signinUserAsync, signupUserAsync, userInfoAsync } from './actions';
import { SignAction, SignState } from './types';

const initialState: SignState = {
	signin: asyncState.initial(),
	signup: asyncState.initial(),
	userInfo: asyncState.initial(),
};

export const signin = createReducer<SignState, SignAction>(initialState).handleAction(
	transformToArray(signinUserAsync),
	createAsyncReducer(signinUserAsync, 'signin'),
);

export const signup = createReducer<SignState, SignAction>(initialState).handleAction(
	transformToArray(signupUserAsync),
	createAsyncReducer(signupUserAsync, 'signup'),
);

export const userInfo = createReducer<SignState, SignAction>(initialState).handleAction(
	transformToArray(userInfoAsync),
	createAsyncReducer(userInfoAsync, 'userInfo'),
);
