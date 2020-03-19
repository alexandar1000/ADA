import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule}  from '@angular/material/snack-bar';
import { SidenavComponent } from './sidenav/sidenav.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RepoFormComponent } from './repo-form/repo-form.component';
import { AcceptedUrlDirective } from './shared/accepted-url.directive';
import { GraphComponent } from './graph/graph.component';
import {AnalyserService} from "./analyser.service";
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatSliderModule} from "@angular/material/slider";
import { SnapshotSliderComponent } from './snapshot-slider/snapshot-slider.component';
import { MetricSelectorComponent } from './metric-selector/metric-selector.component';
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import { OwnerComponent } from './owner/owner.component';
import { RepositoryComponent } from './repository/repository.component';
import { BranchComponent } from './branch/branch.component';
import { SnapshotComponent } from './snapshot/snapshot.component';
import { FilterPipe } from './filter.pipe';
import { SidebarService } from './sidebar.service';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    RepoFormComponent,
    AcceptedUrlDirective,
    GraphComponent,
    DashboardComponent,
    SnapshotSliderComponent,
    MetricSelectorComponent,
    OwnerComponent,
    RepositoryComponent,
    BranchComponent,
    SnapshotComponent,
    FilterPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatInputModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatSliderModule,
    MatOptionModule,
    MatSelectModule,
    MatProgressSpinnerModule
  ],
  providers: [AnalyserService, SidebarService],
  bootstrap: [AppComponent]
})
export class AppModule { }
