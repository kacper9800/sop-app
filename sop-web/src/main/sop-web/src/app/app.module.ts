import { ActivationKeysComponent } from './sop/activation-keys/activation-keys.component';
// tslint:disable-next-line:max-line-length
import { AddEditDialogActivitiesComponent } from './sop/planner/planner-activities/add-edit-dialog-activities/add-edit-dialog-activities.component';
// tslint:disable-next-line:max-line-length
import { AddEditDialogLocationsComponent } from './sop/planner/planner-locations/add-edit-dialog-locations/add-edit-dialog-locations.component';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { CalendarComponent } from './widgets/calendar/calendar.component';
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
import { CollegesComponent } from './sop/colleges/colleges.component';
import { CompaniesComponent } from './sop/companies/companies.component';
import { ConfirmDeleteDialogComponent } from './common/confirm-delete-dialog/confirm-delete-dialog.component';
import { FooterComponent } from './layout/footer/footer.component';
import { FormsComponent } from './sop/forms/forms.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HeaderComponent } from './layout/header/header.component';
import { InternsComponent } from './sop/interns/interns.component';
import { BlockUIModule} from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { PanelModule } from 'primeng/panel';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { TreeTableModule } from 'primeng/treetable';
import { HomeComponent } from './home/home.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LogoutComponent } from './sop/logout/logout.component';
import { LoginComponent } from './authentication/login/login.component';
import { LogbookComponent } from './sop/logbook/logbook.component';
import { NavBarComponent } from './layout/nav-bar/nav-bar.component';
import { NewInternComponent } from './sop/new-intern/new-intern.component';
import {LOCALE_ID, NgModule} from '@angular/core';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { PlannerActivitiesComponent } from './sop/planner/planner-activities/planner-activities.component';
// import { PlannerCalendarComponent } from './sop/planner/planner-calendar/planner-calendar.component';
import { PlannerComponent } from './planner/planner.component';
import { PlannerGeneratorComponent } from './sop/planner/planner-generator/planner-generator.component';
import { PlannerLocationsComponent } from './sop/planner/planner-locations/planner-locations.component';
import { PlannerSettingsComponent } from './sop/planner/planner-settings/planner-settings.component';
import { PlannerWorkHoursComponent } from './sop/planner/planner-work-hours/planner-work-hours.component';
import { RegistrationComponent } from './authentication/registration/registration.component';
import { SettingsComponent } from './settings/settings.component';
import { StatisticsComponent } from './sop/statistics/statistics.component';
import { TodoComponent } from './widgets/todo/todo.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { UsersComponent } from './sop/users/users.component';
import { WidgetsComponent } from './widgets/widgets.component';
import { DocumentsComponent } from './sop/documents/documents.component';
import { SuperVisorsComponent } from './sop/super-visors/super-visors.component';
import { CollegeStructureComponent } from './sop/college-structure/college-structure.component';
import { CollegeRegistrationComponent } from './authentication/college-registration/college-registration.component';
import { RegistrationConfirmationComponent } from './authentication/registration-confirmation/registration-confirmation.component';
// tslint:disable-next-line:max-line-length
import { AddEditDialogActivationKeysComponent } from './sop/activation-keys/add-edit-dialog-activation-keys/add-edit-dialog-activation-keys.component';
// tslint:disable-next-line:max-line-length
import { AddEditDialogCollegeStructureComponent } from './sop/college-structure/add-edit-dialog-college-structure/add-edit-dialog-college-structure.component';
import { AddEditDialogCompaniesComponent } from './sop/companies/add-edit-dialog-companies/add-edit-dialog-companies.component';
import { AddEditDialogCollegesComponent } from './sop/colleges/add-edit-dialog-colleges/add-edit-dialog-colleges.component';
import { ExportTableComponent } from './sop/export-table/export-table.component';
import { ExportAsModule } from 'ngx-export-as';
import '@angular/common/locales/global/pl';
import {FullCalendarModule} from 'primeng/fullcalendar';
import { InternDialogComponent } from './sop/interns/intern-dialog/intern-dialog.component';
import { InternDialogBasicDataComponent } from './sop/interns/intern-dialog/intern-dialog-basic-data/intern-dialog-basic-data.component';
import { InternDialogLogbookComponent } from './sop/interns/intern-dialog/intern-dialog-logbook/intern-dialog-logbook.component';
import { InternDialogDocumentsComponent } from './sop/interns/intern-dialog/intern-dialog-documents/intern-dialog-documents.component';
import { DirectionsComponent } from './sop/directions/directions.component';
import { AddEditDialogDirectionsComponent } from './sop/directions/add-edit-dialog-directions/add-edit-dialog-directions.component';
import { AddEditDialogDocumentsComponent } from './sop/documents/add-edit-dialog-documents/add-edit-dialog-documents.component';
import {FileUploadModule} from "primeng/fileupload";
import { FileUploadComponent } from './common/file-upload/file-upload.component';
import { RequestsComponent } from './sop/requests/requests.component';
import { AddEditDialogRequestsComponent } from './sop/requests/add-edit-dialog-requests/add-edit-dialog-requests.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { AddDialogRequestsComponent } from './sop/requests/add-dialog-requests/add-dialog-requests.component';
import { ViewDialogRequestsComponent } from './sop/requests/view-dialog-requests/view-dialog-requests.component';
import { AddEditDialogLogbooksComponent } from './sop/logbook/add-edit-dialog-logbooks/add-edit-dialog-logbooks.component';
import { AnswerRequestDialogRequestsComponent } from './sop/requests/answer-request-dialog-requests/answer-request-dialog-requests.component';

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
    CollegesComponent,
    AddEditDialogActivationKeysComponent,
    SettingsComponent,
    UsersComponent,
    PlannerComponent,
    HeaderComponent,
    WidgetsComponent,
    TodoComponent,
    CalendarComponent,
    PlannerSettingsComponent,
    // PlannerCalendarComponent,
    PlannerActivitiesComponent,
    PlannerWorkHoursComponent,
    PlannerLocationsComponent,
    AddEditDialogActivitiesComponent,
    AddEditDialogLocationsComponent,
    PlannerGeneratorComponent,
    ConfirmDeleteDialogComponent,
    DocumentsComponent,
    SuperVisorsComponent,
    CollegeStructureComponent,
    CollegeRegistrationComponent,
    RegistrationConfirmationComponent,
    AddEditDialogCollegeStructureComponent,
    AddEditDialogCompaniesComponent,
    AddEditDialogCollegesComponent,
    ExportTableComponent,
    InternsComponent,
    InternDialogComponent,
    InternDialogBasicDataComponent,
    InternDialogLogbookComponent,
    InternDialogDocumentsComponent,
    DirectionsComponent,
    AddEditDialogDirectionsComponent,
    AddEditDialogDocumentsComponent,
    FileUploadComponent,
    RequestsComponent,
    AddEditDialogRequestsComponent,
    LandingPageComponent,
    AddDialogRequestsComponent,
    ViewDialogRequestsComponent,
    AddEditDialogLogbooksComponent,
    AnswerRequestDialogRequestsComponent,
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
    DropdownModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    FullCalendarModule,
    TreeTableModule,
    ExportAsModule,
    FileUploadModule,
  ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    authInterceptorProviders,
    { provide: LOCALE_ID, useValue: 'pl' } ],
  bootstrap: [AppComponent],
  entryComponents: [
    LoginComponent,
    AddEditDialogActivitiesComponent,
    AddEditDialogLocationsComponent
  ]
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
