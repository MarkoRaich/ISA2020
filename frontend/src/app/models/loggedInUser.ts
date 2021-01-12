import { UserTokenState } from './userTokenState';

export class LoggedInUser {
    id: number;
    username: String;
    role: String;
    userTokenState: UserTokenState;
    
    constructor(id: number, username: String, role: String, userTokenState: UserTokenState) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.userTokenState = userTokenState;
    }
}