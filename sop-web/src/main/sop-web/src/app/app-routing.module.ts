import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NewInternComponent } from './sop/new-intern/new-intern.component';
import { StatisticsComponent } from './sop/statistics/statistics.component';
import { FormsComponent } from './sop/forms/forms.component';
import { LogoutComponent } from './sop/logout/logout.component';
import { CompaniesComponent } from './sop/companies/companies.component';
import { LoginComponent } from './login/login.component';
import { InternsComponent } from './sop/interns/interns.component';
import { RegistrationComponent } from './registration/registration.component';
import { ActivationKeysComponent } from './sop/activation-keys/activation-keys.component';
import { LogbookComponent } from './sop/logbook/logbook.component';
import { CollegeComponent } from './sop/college/college.component';
import { UsersComponent } from './sop/users/users.component';
import { PlannerComponent } from './planner/planner.component';
import { PlannerSettingsComponent } from './sop/planner/planner-settings/planner-settings.component';
// import { PlannerCalendarComponent } from './sop/planner/planner-calendar/planner-calendar.component';
import { PlannerActivitiesComponent } from './sop/planner/planner-activities/planner-activities.component';
import { PlannerLocationsComponent } from './sop/planner/planner-locations/planner-locations.component';
import { PlannerWorkHoursComponent } from './sop/planner/planner-work-hours/planner-work-hours.component';
import {DocumentsComponent} from './sop/documents/documents.component';
import {SuperVisorsComponent} from './sop/super-visors/super-visors.component';
import {CollegeStructureComponent} from './sop/college-structure/college-structure.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'app/colleges', component: CollegeComponent},                      // Lista uczelni
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
  {path: 'app/planner', children: [
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
