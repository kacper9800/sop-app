import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {CollegeRegistrationComponent} from '../authentication/college-registration/college-registration.component';
import {FormBuilder} from '@angular/forms';
import {AuthService} from '../_services/auth/auth.service';
import {TokenStorageService} from '../_services/auth/token-storage.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {
  @ViewChild('collegeRegistrationForm', {
    read: ViewContainerRef,
    static: false
  }) entry: ViewContainerRef;
  public componentRef: any;

  public roles: string[] = [];

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder,
              private resolver: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
  }

  public scroll(el: HTMLElement) {
    document.body.scrollTop = 0;
    el.scrollIntoView();
    const pos = window.pageYOffset;
    console.log(pos);
  }

  public scrollTop(el: HTMLElement) {
    const scrollToTop = window.setInterval(() => {
      const pos = window.pageYOffset;
      console.log(pos);
      if (pos > 0) {
        window.scrollTo(0, pos - 20); // how far to scroll on each step
      } else {
        window.clearInterval(scrollToTop);
      }
    }, 16);
  }

  public scrollToSectionTwo(sectionTwo: HTMLElement) {
    const scrollToSectionTwo = window.setInterval(() => {
      const pos = window.pageYOffset;
      console.log(pos);
      if (pos < 1091.111083984375) {
        window.scrollTo(0, pos + 20); // how far to scroll on each step
      } else {
        window.clearInterval(scrollToSectionTwo);
      }

      // if (pos === 0) {
      //   window.scrollTo(0, pos + 20); // how far to scroll on each step
      // } else if (pos > 0) {
      //   window.scroll(0, pos + 20); // how far to scroll on each step
      // } else if (pos === 1100) {
      //
      // }
    }, 16);

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
