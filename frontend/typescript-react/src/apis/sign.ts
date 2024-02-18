import { getCookie } from '../lib/cookie';
import { Sign, User } from '../models/sign';
import { getCommonInstance, keySign } from './common';

export async function signin(formData: FormData) {
	const response = await getCommonInstance(keySign).post<Sign>('/auth/signin', formData);
	return response.data;
}

export async function signup(formData: FormData) {
	const response = await getCommonInstance(keySign).post<boolean>('/user/signup', formData);
	return response.data;
}

export async function userInfo() {
	const token = getCookie('token-jwt');
	const response = await getCommonInstance(keySign).get<User>('/auth/userInfo', { headers: { 'token-jwt': token } });
	return response.data;
}
