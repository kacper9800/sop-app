import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SplitButtonModule, TabMenuModule, ToolbarModule } from 'primeng';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { NewInternComponent } from './new-intern/new-intern.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { FormsComponent } from './forms/forms.component';
import { LogoutComponent } from './logout/logout.component';
import { FooterComponent } from './footer/footer.component';
import {
  ClrCheckboxModule,
  ClrDatagridModule, ClrDatepickerModule,
  ClrDropdownModule,
  ClrIconModule, ClrInputModule, ClrLoadingModule,
  ClrMainContainerModule, ClrModalModule,
  ClrNavigationModule, ClrPasswordModule, ClrSelectModule
} from '@clr/angular';
import { CompaniesComponent } from './companies/companies.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { InternsComponent } from './interns/interns.component';


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

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
