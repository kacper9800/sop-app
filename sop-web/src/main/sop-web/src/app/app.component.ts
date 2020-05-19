import { Component } from '@angular/core';
import { TokenStorageService } from './_services/auth/token-storage.service';
import { User } from './security/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public hasAdminAuthorities: boolean;
  public hasModeratorAuthorities: boolean;
  public hasSupervisorAuthorities: boolean;
  public hasStudentAuthorities: boolean;
  public hasUserAuthorities: boolean;

  private roles: string[];
  isLoggedIn: boolean;

  user: User;

  constructor(private tokenStorageService: TokenStorageService) {
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.hasAdminAuthorities = this.roles.includes('ROLE_ADMIN');
      this.hasModeratorAuthorities = this.roles.includes('ROLE_MODERATOR');
      this.hasSupervisorAuthorities = this.roles.includes('ROLE_SUPERVISER');
      this.hasStudentAuthorities = this.roles.includes('ROLE_STUDENT');
      this.hasUserAuthorities = this.roles.includes('ROLE_USER');
      this.user = user;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
