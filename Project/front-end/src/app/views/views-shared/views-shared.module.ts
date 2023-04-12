import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WorksRoutingModule } from 'src/app/features/works/works-routing.module';



@NgModule({
  declarations: [
    FooterComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    WorksRoutingModule
  ],
  exports: [
    FooterComponent,
    NavbarComponent
  ]
})
export class ViewsSharedModule { }
