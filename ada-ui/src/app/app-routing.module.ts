import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RepoFormComponent } from './repo-form/repo-form.component';
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  { path: 'repo-form', component: RepoFormComponent},
  { path: '', redirectTo: '/repo-form', pathMatch: 'full' },
  { path: 'dashboard/:owner/:repository/:branch/:snapshot', component: DashboardComponent},
  { path: 'dashboard/current', component: DashboardComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
