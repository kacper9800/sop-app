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
import { PlannerCalendarComponent } from './sop/planner/planner-calendar/planner-calendar.component';
import { PlannerActivitiesComponent } from './sop/planner/planner-activities/planner-activities.component';
import { PlannerLocationsComponent } from './sop/planner/planner-locations/planner-locations.component';
import { PlannerWorkHoursComponent } from './sop/planner/planner-work-hours/planner-work-hours.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'app/new-intern', component: NewInternComponent},
  {path: 'app/interns', component: InternsComponent},
  {path: 'app/companies', component: CompaniesComponent},
  {path: 'app/forms', component: FormsComponent},
  {path: 'app/statistics', component: StatisticsComponent},
  {path: 'app/login', component: LoginComponent},
  {path: 'app/logout', component: LogoutComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'app/activation-keys', component: ActivationKeysComponent},
  {path: 'app/logbook', component: LogbookComponent},
  {path: 'app/college', component: CollegeComponent},
  {path: 'app/users', component: UsersComponent},
  {
    path: 'app/planner', children: [
      {path: '', pathMatch: 'full', component: PlannerComponent},
      {path: 'activitiesList', component: PlannerActivitiesComponent},
      {path: 'activitiesCalendar', component: PlannerCalendarComponent},
      {path: 'locations', component: PlannerLocationsComponent},
      {path: 'workHours', component: PlannerWorkHoursComponent},
      {path: 'settings', component: PlannerSettingsComponent}
    ]
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
