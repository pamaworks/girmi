import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';
import App from './App';
import rootReducer, { rootSaga } from './modules';

const sagaMiddleware = createSagaMiddleware();

const store = createStore(rootReducer, applyMiddleware(sagaMiddleware));

sagaMiddleware.run(rootSaga);

// eslint-disable-next-line react/no-deprecated
ReactDOM.render(
	<Provider store={store}>
		<App />
	</Provider>,
	document.getElementById('root'),
);
