import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersAccountComponent } from './components/users-account/users-account.component';
import { UsersChangePasswordComponent } from './components/users-change-password/users-change-password.component';
import { UsersAccountProfileComponent } from './components/users-account-profile/users-account-profile.component';


@NgModule({
  declarations: [
    UsersAccountComponent,
    UsersChangePasswordComponent,
    UsersAccountProfileComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule
  ]
})
export class UsersModule { }
