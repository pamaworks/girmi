export interface User {
	userNm: string;
}

export interface Sign {
	user: User;
	token: string;
}
