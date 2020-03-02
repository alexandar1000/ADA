import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SnapshotSliderComponent } from './snapshot-slider.component';

describe('SnapshotSliderComponent', () => {
  let component: SnapshotSliderComponent;
  let fixture: ComponentFixture<SnapshotSliderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SnapshotSliderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SnapshotSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
