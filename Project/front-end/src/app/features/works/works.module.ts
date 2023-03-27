import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorksRoutingModule } from './works-routing.module';
import { AddWorkComponent } from './components/add-work/add-work.component';
import { AllWorksComponent } from './components/all-works/all-works.component';


@NgModule({
  declarations: [
    AddWorkComponent,
    AllWorksComponent
  ],
  imports: [
    CommonModule,
    WorksRoutingModule
  ]
})
export class WorksModule { }
