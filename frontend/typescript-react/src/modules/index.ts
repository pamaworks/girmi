import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import github from './github/reducer';
import { githubSaga } from './github';
import { signin, signup, userInfo } from './sign/reducer';
import { signSaga } from './sign';

const rootReducer = combineReducers({
	github,
	signin,
	signup,
	userInfo,
});

// 루트 리듀서를 내보내주세요.
export default rootReducer;

// 루트 리듀서의 반환값를 유추해줍니다
// 추후 이 타입을 컨테이너 컴포넌트에서 불러와서 사용해야 하므로 내보내줍니다.
export type RootState = ReturnType<typeof rootReducer>;

export function* rootSaga() {
	yield all([githubSaga(), signSaga()]);
}
