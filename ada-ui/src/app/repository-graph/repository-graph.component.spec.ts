import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RepositoryGraphComponent } from './repository-graph.component';

describe('RepositoryGraphComponent', () => {
  let component: RepositoryGraphComponent;
  let fixture: ComponentFixture<RepositoryGraphComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RepositoryGraphComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RepositoryGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
