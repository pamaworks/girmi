import { ActionType } from 'typesafe-actions';
import * as actions from './actions';
import { AsyncState } from '../../lib/reducerUtils';
import { Sign, User } from '../../models/sign';

export type SignAction = ActionType<typeof actions>;

export type SignState = {
	signin: AsyncState<Sign, Error>;
	signup: AsyncState<boolean, Error>;
	userInfo: AsyncState<User, Error>;
};
