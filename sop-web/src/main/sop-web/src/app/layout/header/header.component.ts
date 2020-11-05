import {
  Component,
  ComponentFactoryResolver,
  Input,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {MenuItem, MessageService} from 'primeng/api';
import {TranslateService} from '@ngx-translate/core';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../_services/auth/token-storage.service';
import {User} from '../../security/user';
import {RegistrationComponent} from '../../authentication/registration/registration.component';
import {LoginComponent} from '../../authentication/login/login.component';
import {UserService} from '../../_services/user.service';
import {HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [MessageService]
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
  public changeCollegeDialog = false;
  private selectedCollegeId: any;

  constructor(private translateService: TranslateService,
              private router: Router,
              private tokenStorageService: TokenStorageService,
              private resolver: ComponentFactoryResolver,
              private userService: UserService,
              private messageService: MessageService
  ) {
    translateService.setDefaultLang('pl');

  }


  ngOnInit(): void {
    console.log(this.user);
  }

  refersh(): void {
    window.location.reload();
  }

  logout() {
    this.tokenStorageService.signOut();
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

  public onCollegeChange(id) {
    this.changeCollegeDialog = true;
    this.selectedCollegeId = id;
  }

  public changeCollege() {
    this.userService.changeCollege(this.selectedCollegeId).subscribe(
      (res: HttpResponse<number>) => this.onSuccessChangeCollege(res),
      (err: HttpResponse<any>) => this.onErrorChangeCollege(err),
      () => this.changeCollegeDialog = false
    );
  }

  private onSuccessChangeCollege(res: HttpResponse<number>) {
    this.messageService.add({
      severity: 'success', summary: this.translateService.instant('toast.success'),
      detail: this.translateService.instant('toast.defaultSuccessDetailAdd')
    });
    this.logout();
  }

  private onErrorChangeCollege(err: HttpResponse<any>) {
    this.messageService.add({
      key: 'toast1', severity: 'error', summary: this.translateService.instant('toast.error'),
      detail: this.translateService.instant('toast.defaultErrorDetailLoad')
    });
  }
}
