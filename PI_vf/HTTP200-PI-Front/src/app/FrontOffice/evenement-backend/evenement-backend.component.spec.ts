import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvenementBackendComponent } from './evenement-backend.component';

describe('EvenementBackendComponent', () => {
  let component: EvenementBackendComponent;
  let fixture: ComponentFixture<EvenementBackendComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvenementBackendComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EvenementBackendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
