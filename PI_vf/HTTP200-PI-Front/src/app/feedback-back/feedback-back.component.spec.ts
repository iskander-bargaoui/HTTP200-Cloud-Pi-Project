import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackBackComponent } from './feedback-back.component';

describe('FeedbackBackComponent', () => {
  let component: FeedbackBackComponent;
  let fixture: ComponentFixture<FeedbackBackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackBackComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
