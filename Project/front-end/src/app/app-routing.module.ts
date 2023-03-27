import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuestGuard } from './core/guards/guest.guard';
import { AuthGuard } from './core/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./views/home/home.module').then((m) => m.HomeModule),
      },
    ],
  },
  {
    path: 'auth',
    canActivate: [GuestGuard],
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./features/auth/auth.module').then((m) => m.AuthModule),
      },
    ],
  },
  {
    path: 'clients',
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./features/clients/clients.module').then(
            (m) => m.ClientsModule
          ),
      },
    ],
  },
  {
    path: 'works',
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./features/works/works.module').then((m) => m.WorksModule),
      },
    ],
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
