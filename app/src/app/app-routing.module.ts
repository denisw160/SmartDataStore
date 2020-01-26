import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {DashboardComponent} from './components/dashboard/dashboard.component';
import {UsersComponent} from './components/users/users.component';

/* Define the routes */
const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent, data: {title: 'Dashboard'}},
  {path: 'users', component: UsersComponent, data: {title: 'Users'}},
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
