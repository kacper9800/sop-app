import { Component } from '@angular/core';
import { TokenStorageService } from './_services/auth/token-storage.service';
import { User } from './security/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  private roles: string[];
  isLoggedIn: boolean;
  showAdminBoard: boolean;
  showModeratorBoard: boolean;
  user: User;

  constructor(private tokenStorageService: TokenStorageService) {
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.user = user;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
