import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminconsoleSharedModule } from 'app/shared/shared.module';
import { TransactionRequestComponent } from './transaction-request.component';
import { TransactionRequestDetailComponent } from './transaction-request-detail.component';
import { TransactionRequestUpdateComponent } from './transaction-request-update.component';
import { TransactionRequestDeleteDialogComponent } from './transaction-request-delete-dialog.component';
import { transactionRequestRoute } from './transaction-request.route';

@NgModule({
  imports: [AdminconsoleSharedModule, RouterModule.forChild(transactionRequestRoute)],
  declarations: [
    TransactionRequestComponent,
    TransactionRequestDetailComponent,
    TransactionRequestUpdateComponent,
    TransactionRequestDeleteDialogComponent,
  ],
  entryComponents: [TransactionRequestDeleteDialogComponent],
})
export class CoreserviceTransactionRequestModule {}
