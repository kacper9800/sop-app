import { Component, Input, OnInit } from '@angular/core';
import { MenuItem} from 'primeng/api';
import { TranslateService } from '@ngx-translate/core';
import { TokenStorageService } from '../../_services/auth/token-storage.service';
import { User } from '../../security/user';
import { PrincipalService } from '../../_services/auth/principal.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
  providers: [TranslateService]
})
export class NavBarComponent implements OnInit {

  @Input()
  isLoggedIn: boolean;

  user: User;

  items: MenuItem[];

  activeItem: MenuItem;
  logged: boolean;
  planner: boolean;

  aboutDialog = false;

  isSuperAdmin: boolean;
  isAdmin: boolean;
  isModerator: boolean;
  isSuperviser: boolean;
  isStudent: boolean;

  constructor(private translateService: TranslateService,
              private tokenStorageService: TokenStorageService,
              private principalService: PrincipalService) {
    translateService.setDefaultLang('pl');
  }

  ngOnInit() {
    this.items = [
      {label: 'Strona główna', routerLink: [''], icon: 'fa fa-fw fa-bar-chart'},
      {label: 'Rejestracja praktykanta', routerLink: ['app/new-intern'], icon: 'fa fa-fw fa-calendar'},
      {label: 'Spis firm', icon: 'fa fa-fw fa-book'},
      {label: 'Spis praktykantów', icon: 'fa fa-fw fa-support'},
      {label: 'Ankiety', routerLink: ['app/forms'], icon: 'fa fa-fw fa-twitter'},
      {label: 'Statystki', routerLink: ['app/statistics'], icon: 'fa fa-fw fa-twitter'},
      {label: 'Wyloguj', routerLink: ['app/logout'], icon: 'fa fa-fw fa-twitter'}
    ];
    if (this.tokenStorageService.getToken()) {
      this.isSuperAdmin = this.principalService.isSuperAdmin();
      this.isAdmin = this.principalService.isAdmin();
      this.isModerator = this.principalService.isModerator();
      this.isSuperviser = this.principalService.isSuperviser();
      this.isStudent = this.principalService.isStudent();
    }
    this.activeItem = this.items[0];
  }

  closeItem(event, index) {
    this.items = this.items.filter((item, i) => i !== index);
    event.preventDefault();
  }
}
