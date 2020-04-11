import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class PrincipalService {

  private roles: string[];
  private user: any;

  constructor() {
    this.getUser();
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
