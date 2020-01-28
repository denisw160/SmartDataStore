import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {BackgroundComponent} from './components/background/background.component';
import {FooterComponent} from './components/footer/footer.component';
import {HeaderComponent} from './components/header/header.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {TemplateBigComponent} from './components/template-big/template-big.component';
import {TemplateMediumComponent} from './components/template-medium/template-medium.component';
import {TemplateSmallComponent} from './components/template-small/template-small.component';

import {LoggingService} from './services/logging.service';

import {AlertsComponent} from './views/alerts/alerts.component';
import {DashboardComponent} from './views/dashboard/dashboard.component';
import {LoginComponent} from './views/login/login.component';
import {ProfileComponent} from './views/profile/profile.component';
import {UsersComponent} from './views/users/users.component';


@NgModule({
  declarations: [
    AppComponent,
    BackgroundComponent,
    FooterComponent,
    HeaderComponent,
    NavbarComponent,
    TemplateBigComponent,
    TemplateMediumComponent,
    TemplateSmallComponent,
    AlertsComponent,
    DashboardComponent,
    LoginComponent,
    ProfileComponent,
    UsersComponent
  ],
  imports: [
    NgbModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    LoggingService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
