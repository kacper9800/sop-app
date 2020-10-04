import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

const TOKEN_KEY = 'pl.auth-token';
const USER_KEY = 'pl.auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private router: Router) {
  }

  static API = 'http://localhost:8082/api/';

  public signOut(): void {
    window.sessionStorage.clear();
    setTimeout(() => {
      this.router.navigate(['']);
    }, 2000 );
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }

}
