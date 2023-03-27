import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersAccountProfileComponent } from './users-account-profile.component';

describe('UsersAccountProfileComponent', () => {
  let component: UsersAccountProfileComponent;
  let fixture: ComponentFixture<UsersAccountProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersAccountProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersAccountProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
