import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NodeInsightComponent } from './node-insight.component';

describe('NodeInsightComponent', () => {
  let component: NodeInsightComponent;
  let fixture: ComponentFixture<NodeInsightComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NodeInsightComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NodeInsightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
