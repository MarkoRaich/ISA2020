import { UserTokenState } from './userTokenState';

export class LoggedInUser {
    id: number;
    email: String;
    role: String;
    userTokenState: UserTokenState;
    
    constructor(id: number, email: String, role: String, userTokenState: UserTokenState) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.userTokenState = userTokenState;
    }
}