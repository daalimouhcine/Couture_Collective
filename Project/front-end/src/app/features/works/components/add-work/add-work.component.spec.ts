import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkComponent } from './add-work.component';

describe('AddWorkComponent', () => {
  let component: AddWorkComponent;
  let fixture: ComponentFixture<AddWorkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddWorkComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
