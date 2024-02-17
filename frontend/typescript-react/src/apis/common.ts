import axios, { Axios } from 'axios';

const serviceCommonUrl = '';
const loginUrl = '';

export function getInstance() {
	return axios.create({
		baseURL: serviceCommonUrl,
	});
}

export function getSignInstance(token: string): Axios {
	let signInstance: Axios;
	if (token) {
		signInstance = axios.create({
			headers: { token },
			baseURL: serviceCommonUrl,
		});
	}
	signInstance = axios.create({});
	return signInstance;
}
