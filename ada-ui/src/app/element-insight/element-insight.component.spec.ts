import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementInsightComponent } from './element-insight.component';

describe('ElementInsightComponent', () => {
  let component: ElementInsightComponent;
  let fixture: ComponentFixture<ElementInsightComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElementInsightComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementInsightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
