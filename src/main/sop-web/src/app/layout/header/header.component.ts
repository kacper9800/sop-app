import { Component, ComponentFactoryResolver, Input, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { MenuItem } from 'primeng';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../_services/token-storage.service';
import { LoginComponent } from '../../login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Input()
  isLoggedIn: boolean;
  displayLoginDialog: Boolean = false;

  @ViewChild('loginForm', {read: ViewContainerRef, static: false}) entry: ViewContainerRef;
  componentRef: any;


  items: MenuItem[];

  activeItem: MenuItem;
  logged: boolean;
  planner: Boolean;

  aboutDialog = false;


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

  showLoginForm() {
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
}
