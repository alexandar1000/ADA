import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GitdownloaderComponent } from './gitdownloader.component';

describe('GitdownloaderComponent', () => {
  let component: GitdownloaderComponent;
  let fixture: ComponentFixture<GitdownloaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GitdownloaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GitdownloaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
