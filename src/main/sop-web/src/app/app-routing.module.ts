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


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'app/new-intern', component: NewInternComponent},
  {path: 'app/interns', component: InternsComponent},
  {path: 'app/companies', component: CompaniesComponent},
  {path: 'app/forms', component: FormsComponent},
  {path: 'app/statistics', component: StatisticsComponent},
  {path: 'app/login', component: LoginComponent},
  {path: 'app/logout', component: LogoutComponent},
  {path: 'app/registration', component: RegistrationComponent},
  {path: 'app/activation-keys', component: ActivationKeysComponent},
  {path: 'app/logbook', component: LogbookComponent},
  {path: 'app/college', component: CollegeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
