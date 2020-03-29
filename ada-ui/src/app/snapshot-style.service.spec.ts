import { TestBed } from '@angular/core/testing';

import { SnapshotStyleService } from './snapshot-style.service';

describe('SnapshotStyleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SnapshotStyleService = TestBed.get(SnapshotStyleService);
    expect(service).toBeTruthy();
  });
});
