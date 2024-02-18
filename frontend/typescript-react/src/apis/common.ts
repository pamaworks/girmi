import axios, { Axios } from 'axios';

const serviceCommonUrl = '';
const loginUrl = '';
const gwUrl = 'https://localhost:8080';

export const keySign = 'sign';
const url = {
	sign: `${gwUrl}/sign-jwt`,
};

export function getCommonInstance(contextName: string) {
	return axios.create({ baseURL: `${url[contextName]}` });
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
