import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAlertComponent } from './form-alert.component';

describe('FormAlertComponent', () => {
  let component: FormAlertComponent;
  let fixture: ComponentFixture<FormAlertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormAlertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
