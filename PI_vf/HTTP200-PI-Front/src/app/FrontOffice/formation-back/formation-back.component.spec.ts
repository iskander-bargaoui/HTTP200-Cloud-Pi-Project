import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationBackComponent } from './formation-back.component';

describe('FormationBackComponent', () => {
  let component: FormationBackComponent;
  let fixture: ComponentFixture<FormationBackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormationBackComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormationBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
