import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardSidePanelComponent } from './dashboard-side-panel.component';

describe('DashboardSidePanelComponent', () => {
  let component: DashboardSidePanelComponent;
  let fixture: ComponentFixture<DashboardSidePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardSidePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardSidePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
