import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllresComponent } from './allres.component';

describe('AllresComponent', () => {
  let component: AllresComponent;
  let fixture: ComponentFixture<AllresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
