import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QueryFormComponent } from './query-form.component';

describe('QueryFormComponent', () => {
  let component: QueryFormComponent;
  let fixture: ComponentFixture<QueryFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QueryFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QueryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
