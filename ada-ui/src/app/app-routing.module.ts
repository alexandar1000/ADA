import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RepoFormComponent } from './repo-form/repo-form.component';

const routes: Routes = [
  { path: 'repo-form', component: RepoFormComponent},
  { path: '', redirectTo: '/repo-form', pathMatch: 'full' },
  { path: 'analysis/:owner/:repository/:branch/:snapshot', component: RepoFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
