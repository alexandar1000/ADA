import { TestBed } from '@angular/core/testing';

import { ElementInsightService } from './element-insight.service';

describe('ElementInsightService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ElementInsightService = TestBed.get(ElementInsightService);
    expect(service).toBeTruthy();
  });
});
