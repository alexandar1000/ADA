import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RepoFormComponent } from './repo-form/repo-form.component';
import {GraphComponent} from "./graph/graph.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent},
  { path: 'repo-form', component: RepoFormComponent},
  { path: '', redirectTo: '/repo-form', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
