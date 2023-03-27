import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllWorksComponent } from './components/all-works/all-works.component';
import { AddWorkComponent } from './components/add-work/add-work.component';

const routes: Routes = [
  {
    path: '',
    component: AllWorksComponent
  },
  {
    path: 'add',
    component: AddWorkComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorksRoutingModule { }
