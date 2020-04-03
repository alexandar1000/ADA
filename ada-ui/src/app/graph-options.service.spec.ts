import { TestBed } from '@angular/core/testing';

import { GraphOptionsService } from './graph-options.service';

describe('GraphOptionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GraphOptionsService = TestBed.get(GraphOptionsService);
    expect(service).toBeTruthy();
  });
});
