export class UserTokenState {
    accessToken: string;
    expireIn: number;

    constructor(accessToken: string, expireIn: number) {
        this.accessToken = accessToken;
        this.expireIn = expireIn;
    }
}