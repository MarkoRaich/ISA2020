export class UserTokenState {
    jwtAccessToken: string;
    expireIn: number;

    constructor(jwtAccessToken: string, expireIn: number) {
        this.jwtAccessToken = jwtAccessToken;
        this.expireIn = expireIn;
    }
}