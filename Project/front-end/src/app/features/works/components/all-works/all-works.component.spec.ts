import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllWorksComponent } from './all-works.component';

describe('AllWorksComponent', () => {
  let component: AllWorksComponent;
  let fixture: ComponentFixture<AllWorksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllWorksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllWorksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
