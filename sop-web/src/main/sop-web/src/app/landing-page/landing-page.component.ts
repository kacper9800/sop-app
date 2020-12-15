import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {CollegeRegistrationComponent} from '../authentication/college-registration/college-registration.component';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AuthService} from '../_services/auth/auth.service';
import {TokenStorageService} from '../_services/auth/token-storage.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {
  @ViewChild('collegeRegistrationForm', {read: ViewContainerRef, static: false}) entry: ViewContainerRef;
  public componentRef: any;

  public roles: string[] = [];

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder,
              private resolver: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
  }

  scroll(el: HTMLElement) {
    el.scrollIntoView();
  }

  public showRegisterCollegeForm(): void {
    this.entry.clear();
    const factory = this.resolver.resolveComponentFactory(CollegeRegistrationComponent);
    this.componentRef = this.entry.createComponent(factory);
    this.componentRef.instance.show();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(data => {
      this.refresh();
    });
  }

  public refresh(): void {
    window.location.reload();
  }
}
