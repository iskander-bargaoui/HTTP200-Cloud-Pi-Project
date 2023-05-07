import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllTemplateAdminComponent } from './all-template-admin.component';

describe('AllTemplateAdminComponent', () => {
  let component: AllTemplateAdminComponent;
  let fixture: ComponentFixture<AllTemplateAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllTemplateAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllTemplateAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
