import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'profile',
        loadChildren: () => import('./coreservice/profile/profile.module').then(m => m.CoreserviceProfileModule),
      },
      {
        path: 'vehicle',
        loadChildren: () => import('./coreservice/vehicle/vehicle.module').then(m => m.CoreserviceVehicleModule),
      },
      {
        path: 'transaction-request',
        loadChildren: () =>
          import('./coreservice/transaction-request/transaction-request.module').then(m => m.CoreserviceTransactionRequestModule),
      },
      {
        path: 'virtual-account',
        loadChildren: () => import('./coreservice/virtual-account/virtual-account.module').then(m => m.CoreserviceVirtualAccountModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AdminconsoleEntityModule {}
