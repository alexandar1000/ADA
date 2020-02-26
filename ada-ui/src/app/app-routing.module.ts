import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RepoFormComponent } from './repo-form/repo-form.component';
import {GraphComponent} from "./graph/graph.component";

const routes: Routes = [
  { path: 'graph', component: GraphComponent},
  { path: 'repo-form', component: RepoFormComponent},
  { path: '', redirectTo: '/repo-form', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
