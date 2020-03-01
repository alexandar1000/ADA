import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MetricSelectorComponent } from './metric-selector.component';

describe('MetricSelectorComponent', () => {
  let component: MetricSelectorComponent;
  let fixture: ComponentFixture<MetricSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MetricSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MetricSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
