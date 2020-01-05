import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NewInternComponent } from './new-intern/new-intern.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { FormsComponent } from './forms/forms.component';
import { LogoutComponent } from './logout/logout.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'app/new-intern', component: NewInternComponent},
  {path: 'app/statistics', component: StatisticsComponent},
  {path: 'app/forms', component: FormsComponent},
  {path: 'app/logout', component: LogoutComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
