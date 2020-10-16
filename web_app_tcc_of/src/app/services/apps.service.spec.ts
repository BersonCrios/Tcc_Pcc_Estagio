import { TestBed, inject } from '@angular/core/testing';

import { AppsService } from './apps.service';

describe('UsuarioService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppsService]
    });
  });

  it('should be created', inject([AppsService], (service: AppsService) => {
    expect(service).toBeTruthy();
  }));
});
