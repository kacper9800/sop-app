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
import { MatToolbarModule } from '@angular/material';
import { ClarityModule } from "@clr/angular";


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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ClarityModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
