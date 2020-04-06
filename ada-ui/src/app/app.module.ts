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
import { ElementInsightComponent } from './element-insight/element-insight.component';
import { GraphMenuComponent } from './graph-menu/graph-menu.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { QueryFormComponent } from './query-form/query-form.component';
import {MatGridListModule} from "@angular/material/grid-list";
import { DashboardSidePanelComponent } from './dashboard-side-panel/dashboard-side-panel.component';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatCardModule} from "@angular/material/card";
import {MatTabsModule} from "@angular/material/tabs";
import { NodeInsightComponent } from './node-insight/node-insight.component';
import { EdgeInsightComponent } from './edge-insight/edge-insight.component';

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
    ElementInsightComponent,
    GraphMenuComponent,
    QueryFormComponent,
    DashboardSidePanelComponent,
    NodeInsightComponent,
    EdgeInsightComponent,
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
        MatProgressSpinnerModule,
        MatCheckboxModule,
        MatGridListModule,
        MatExpansionModule,
        MatSlideToggleModule,
        MatCardModule,
        MatTabsModule
    ],
  providers: [AnalyserService, SidebarService],
  bootstrap: [AppComponent]
})
export class AppModule { }
