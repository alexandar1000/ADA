import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EdgeInsightComponent } from './edge-insight.component';

describe('EdgeInsightComponent', () => {
  let component: EdgeInsightComponent;
  let fixture: ComponentFixture<EdgeInsightComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EdgeInsightComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EdgeInsightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
