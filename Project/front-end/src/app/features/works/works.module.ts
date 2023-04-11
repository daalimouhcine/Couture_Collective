import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WorksRoutingModule } from './works-routing.module';
import { AddWorkComponent } from './components/add-work/add-work.component';
import { AllWorksComponent } from './components/all-works/all-works.component';
import { WorkCardComponent } from './components/work-card/work-card.component';
import { EditWorkComponent } from './components/edit-work/edit-work.component';


@NgModule({
  declarations: [
    AddWorkComponent,
    AllWorksComponent,
    WorkCardComponent,
    EditWorkComponent
  ],
  imports: [
    CommonModule,
    WorksRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class WorksModule { }
