import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientsRoutingModule } from './clients-routing.module';
import { AddClientComponent } from './components/add-client/add-client.component';
import { AllClientsComponent } from './components/all-clients/all-clients.component';


@NgModule({
  declarations: [
    AddClientComponent,
    AllClientsComponent
  ],
  imports: [
    CommonModule,
    ClientsRoutingModule
  ]
})
export class ClientsModule { }
