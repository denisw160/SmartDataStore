import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AlertsComponent} from './views/alerts/alerts.component';
import {DashboardComponent} from './views/dashboard/dashboard.component';
import {LoginComponent} from './views/login/login.component';
import {ProfileComponent} from './views/profile/profile.component';
import {UsersComponent} from './views/users/users.component';

/* Define the routes */
const routes: Routes = [
  {path: 'alerts', component: AlertsComponent, data: {title: 'Alerts'}},
  {path: 'dashboard', component: DashboardComponent, data: {title: 'Dashboard'}},
  {path: 'login', component: LoginComponent, data: {title: 'Login'}},
  {path: 'profile', component: ProfileComponent, data: {title: 'Profile'}},
  {path: 'users', component: UsersComponent, data: {title: 'Users'}},
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
