import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardWorkerComponent } from './board-worker.component';

describe('BoardWorkerComponent', () => {
  let component: BoardWorkerComponent;
  let fixture: ComponentFixture<BoardWorkerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardWorkerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoardWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
