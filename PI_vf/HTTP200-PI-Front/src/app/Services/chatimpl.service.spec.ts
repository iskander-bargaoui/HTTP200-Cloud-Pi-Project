import { TestBed } from '@angular/core/testing';

import { ChatimplService } from './chatimpl.service';

describe('ChatimplService', () => {
  let service: ChatimplService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChatimplService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
