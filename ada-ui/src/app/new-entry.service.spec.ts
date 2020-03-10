import { TestBed } from '@angular/core/testing';

import { NewEntryService } from './new-entry.service';

describe('NewEntryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NewEntryService = TestBed.get(NewEntryService);
    expect(service).toBeTruthy();
  });
});
