import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';

const TOKEN_KEY = 'pl.auth-token';
const USER_KEY = 'pl.auth-user';

@Injectable({
    providedIn: 'root'
})
export class PrincipalService {

    private roles: string[];
    private user: any;

    constructor(private tokenStorage: TokenStorageService) {
        if (this.tokenStorage.getToken()) {
            this.getUser();
        }
    }

    public getUser() {
        this.user = JSON.parse(sessionStorage.getItem(USER_KEY));
        this.roles = this.user.roles;
    }

    public isSuperAdmin(): boolean {
        return this.roles.includes('ROLE_SUPERADMIN');
    }

    public isAdmin(): boolean {
        return this.roles.includes('ROLE_ADMIN');
    }

    public isModerator(): boolean {
        return this.roles.includes('ROLE_MODERATOR');
    }

    public isSuperviser(): boolean {
        return this.roles.includes('ROLE_SUPERVISER');
    }

    public isStudent(): boolean {
        return this.roles.includes('ROLE_STUDENT');
    }

}
