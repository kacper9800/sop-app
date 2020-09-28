import {ActivationKeysComponent} from './sop/activation-keys/activation-keys.component';
import {CollegesComponent} from './sop/colleges/colleges.component';
import {CollegeStructureComponent} from './sop/college-structure/college-structure.component';
import {CompaniesComponent} from './sop/companies/companies.component';
import {DocumentsComponent} from './sop/documents/documents.component';
import {FormsComponent} from './sop/forms/forms.component';
import {HomeComponent} from './home/home.component';
import {InternsComponent} from './sop/interns/interns.component';
import {LogbookComponent} from './sop/logbook/logbook.component';
import {LoginComponent} from './authentication/login/login.component';
import {LogoutComponent} from './sop/logout/logout.component';
import {NgModule} from '@angular/core';
import {PlannerComponent} from './planner/planner.component';
import {PlannerActivitiesComponent} from './sop/planner/planner-activities/planner-activities.component';
import {PlannerLocationsComponent} from './sop/planner/planner-locations/planner-locations.component';
import {PlannerSettingsComponent} from './sop/planner/planner-settings/planner-settings.component';
import {PlannerWorkHoursComponent} from './sop/planner/planner-work-hours/planner-work-hours.component';
import {RegistrationComponent} from './authentication/registration/registration.component';
import {RouterModule, Routes} from '@angular/router';
import {StatisticsComponent} from './sop/statistics/statistics.component';
import {SuperVisorsComponent} from './sop/super-visors/super-visors.component';
import {UsersComponent} from './sop/users/users.component';
import {RegistrationConfirmationComponent} from "./authentication/registration-confirmation/registration-confirmation.component";


// import { PlannerCalendarComponent } from './sop/planner/planner-calendar/planner-calendar.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'app/colleges', component: CollegesComponent},                      // Lista uczelni
  {path: 'app/companies', component: CompaniesComponent},                   // Lista firm
  {path: 'app/college-structure', component: CollegeStructureComponent},    // Struktura uczelni
  {path: 'app/super-visors', component: SuperVisorsComponent},              // Kierownicy praktyk
  {path: 'app/interns', component: InternsComponent},                       // Praktykanci
  {path: 'app/users', component: UsersComponent},                           // Użytkownicy
  {path: 'app/forms', component: FormsComponent},                           // Ankiety
  {path: 'app/statistics', component: StatisticsComponent},                 // Statystyki

  {path: 'app/activation-keys', component: ActivationKeysComponent},        // Klucze aktywacyjne
  {path: 'app/logbook', component: LogbookComponent},                       // Dziennik praktyk
  {path: 'app/documents', component: DocumentsComponent},                   // Dokumenty

  {path: 'app/login', component: LoginComponent},                           // Logowanie
  {path: 'app/logout', component: LogoutComponent},                         // Wylogowywanie
  {path: 'register', component: RegistrationComponent},                     // Rejestracja
  {path: 'registered-successfully', component: RegistrationConfirmationComponent},
  {
    path: 'app/planner', children: [
      {path: '', pathMatch: 'full', component: PlannerComponent},
      {path: 'activitiesList', component: PlannerActivitiesComponent},
      // {path: 'activitiesCalendar', component: PlannerCalendarComponent},
      {path: 'locations', component: PlannerLocationsComponent},
      {path: 'workHours', component: PlannerWorkHoursComponent},
      {path: 'settings', component: PlannerSettingsComponent}
    ]
  },
  // {path: 'app/new-intern', component: NewInternComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
