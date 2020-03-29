import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  BlockUIModule,
  ButtonModule,
  DialogModule,
  InputTextModule,
  PanelModule, ProgressSpinnerModule,
  TableModule,
  ToastModule
} from 'primeng';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './layout/nav-bar/nav-bar.component';
import { NewInternComponent } from './sop/new-intern/new-intern.component';
import { StatisticsComponent } from './sop/statistics/statistics.component';
import { FormsComponent } from './sop/forms/forms.component';
import { LogoutComponent } from './sop/logout/logout.component';
import { FooterComponent } from './layout/footer/footer.component';
import {
  ClarityModule,
  ClrCheckboxModule,
  ClrDatagridModule,
  ClrDatepickerModule,
  ClrDropdownModule,
  ClrIconModule,
  ClrInputModule,
  ClrLoadingModule,
  ClrMainContainerModule,
  ClrModalModule,
  ClrNavigationModule,
  ClrPasswordModule,
  ClrSelectModule
} from '@clr/angular';
import { CompaniesComponent } from './sop/companies/companies.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InternsComponent } from './sop/interns/interns.component';
import { RegistrationComponent } from './registration/registration.component';
import { ActivationKeysComponent } from './sop/activation-keys/activation-keys.component';
import { LogbookComponent } from './sop/logbook/logbook.component';
import { CollegeComponent } from './sop/college/college.component';
import { ActivationKeysDialogComponent } from './sop/activation-keys/activation-keys-dialog/activation-keys-dialog.component';
import { SettingsComponent } from './settings/settings.component';
import { UsersComponent } from './sop/users/users.component';
import { PlannerComponent } from './planner/planner.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ActivitiesComponent } from './sop/planner/activities/activities.component';
import { LocationsComponent } from './sop/planner/locations/locations.component';
import { WorkHoursComponent } from './sop/planner/work-hours/work-hours.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { HeaderComponent } from './layout/header/header.component';

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
    ActivationKeysComponent,
    LogbookComponent,
    CollegeComponent,
    ActivationKeysDialogComponent,
    SettingsComponent,
    UsersComponent,
    PlannerComponent,
    ActivitiesComponent,
    LocationsComponent,
    WorkHoursComponent,
    HeaderComponent,
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
    TableModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    InputTextModule,
    DialogModule,
    ToastModule,
    PanelModule,
    ButtonModule,
    BlockUIModule,
    ProgressSpinnerModule,

  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent],
  entryComponents: [
    LoginComponent
  ]
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
