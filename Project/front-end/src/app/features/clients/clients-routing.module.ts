import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllClientsComponent } from './components/all-clients/all-clients.component';
import { AddClientComponent } from './components/add-client/add-client.component';

const routes: Routes = [
  {
    path: '',
    component: AllClientsComponent,
  },
  {
    path: 'add',
    component: AddClientComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientsRoutingModule { }
