import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormAlertComponent } from './components/form-alert/form-alert.component';



@NgModule({
  declarations: [
    FormAlertComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormAlertComponent
  ]
})
export class AlertModule { }
