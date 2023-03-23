import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthShowcaseComponent } from './auth-showcase.component';

describe('AuthShowcaseComponent', () => {
  let component: AuthShowcaseComponent;
  let fixture: ComponentFixture<AuthShowcaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthShowcaseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthShowcaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
