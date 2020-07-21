import {
  Component,
  ComponentFactoryResolver,
  Input,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {MenuItem} from 'primeng';
import {TranslateService} from '@ngx-translate/core';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../_services/auth/token-storage.service';
import {User} from '../../security/user';
import {RegistrationComponent} from '../../authentication/registration/registration.component';
import {LoginComponent} from '../../authentication/login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Input()
  public isLoggedIn: boolean;

  @Input()
  public user: User;
  public displayLoginDialog = false;

  @ViewChild('loginForm', {read: ViewContainerRef, static: false}) entry: ViewContainerRef;
  public componentRef: any;

  public items: MenuItem[];
  public activeItem: MenuItem;
  public logged: boolean;
  public planner: boolean;
  public aboutDialog = false;

  constructor(private translateService: TranslateService,
              private router: Router,
              private tokenStorageService: TokenStorageService,
              private resolver: ComponentFactoryResolver
  ) {
    translateService.setDefaultLang('pl');

  }


  ngOnInit(): void {
    console.log(this.displayLoginDialog);
  }

  refersh(): void {
    window.location.reload();
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  public showLoginForm(): void {
    if (!this.isLoggedIn) {
      this.entry.clear();
      const factory = this.resolver.resolveComponentFactory(LoginComponent);
      this.componentRef = this.entry.createComponent(factory);
      this.componentRef.instance.show();
      this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(data => {
        this.refersh();
      });
    }
  }

  public showRegistrationForm(): void {
    if (!this.isLoggedIn) {
      this.entry.clear();
      const factory = this.resolver.resolveComponentFactory(RegistrationComponent);
      this.componentRef = this.entry.createComponent(factory);
      this.componentRef.instance.show();
      this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(data => {
        this.refersh();
      });
    }
  }
}
