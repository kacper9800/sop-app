import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SplitButtonModule, TabMenuModule, ToolbarModule } from 'primeng';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './layout/nav-bar/nav-bar.component';
import { NewInternComponent } from './sop/new-intern/new-intern.component';
import { StatisticsComponent } from './sop/statistics/statistics.component';
import { FormsComponent } from './sop/forms/forms.component';
import { LogoutComponent } from './sop/logout/logout.component';
import { FooterComponent } from './layout/footer/footer.component';
import {
  ClrCheckboxModule,
  ClrDatagridModule, ClrDatepickerModule,
  ClrDropdownModule,
  ClrIconModule, ClrInputModule, ClrLoadingModule,
  ClrMainContainerModule, ClrModalModule,
  ClrNavigationModule, ClrPasswordModule, ClrSelectModule, ClarityModule
} from '@clr/angular';
import { CompaniesComponent } from './sop/companies/companies.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InternsComponent } from './sop/interns/interns.component';
import { RegistrationComponent } from './registration/registration.component';


// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    NewInternComponent,
    StatisticsComponent,
    FormsComponent,
    LogoutComponent,
    FooterComponent,
    CompaniesComponent,
    LoginComponent,
    InternsComponent,
    RegistrationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ClrIconModule,
    ClrMainContainerModule,
    ClrNavigationModule,
    ClrDropdownModule,
    ClrDatagridModule,
    ClrInputModule,
    FormsModule,
    ClrDatepickerModule,
    ClrSelectModule,
    ClrPasswordModule,
    ClrCheckboxModule,
    ClrModalModule,
    ClrLoadingModule,
    ClarityModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
