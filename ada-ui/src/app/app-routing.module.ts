import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RepoFormComponent } from './repo-form/repo-form.component';
import { RepositoryGraphComponent } from './repository-graph/repository-graph.component';

const routes: Routes = [
  { path: 'repository-graph', component: RepositoryGraphComponent},
  { path: 'repo-form', component: RepoFormComponent},
  { path: '', redirectTo: '/repo-form', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
