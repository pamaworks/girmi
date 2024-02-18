import { takeEvery } from 'redux-saga/effects';
import { signin, signup, userInfo } from '../../apis/sign';
import createAsyncSaga from '../../lib/createAsyncSaga';
import { SIGNIN, SIGNUP, USERINFO, signinUserAsync, signupUserAsync, userInfoAsync } from './actions';

const signinSaga = createAsyncSaga(signinUserAsync, signin);

const signupSaga = createAsyncSaga(signupUserAsync, signup);

const userInfoSaga = createAsyncSaga(userInfoAsync, userInfo);

export function* signSaga() {
	yield takeEvery(SIGNIN, signinSaga);
	yield takeEvery(SIGNUP, signupSaga);
	yield takeEvery(USERINFO, userInfoSaga);
}
