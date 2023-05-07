import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllnotComponent } from './allnot.component';

describe('AllnotComponent', () => {
  let component: AllnotComponent;
  let fixture: ComponentFixture<AllnotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllnotComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllnotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
