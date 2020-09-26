import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminconsoleSharedModule } from 'app/shared/shared.module';
import { VirtualAccountComponent } from './virtual-account.component';
import { VirtualAccountDetailComponent } from './virtual-account-detail.component';
import { VirtualAccountUpdateComponent } from './virtual-account-update.component';
import { VirtualAccountDeleteDialogComponent } from './virtual-account-delete-dialog.component';
import { virtualAccountRoute } from './virtual-account.route';

@NgModule({
  imports: [AdminconsoleSharedModule, RouterModule.forChild(virtualAccountRoute)],
  declarations: [
    VirtualAccountComponent,
    VirtualAccountDetailComponent,
    VirtualAccountUpdateComponent,
    VirtualAccountDeleteDialogComponent,
  ],
  entryComponents: [VirtualAccountDeleteDialogComponent],
})
export class CoreserviceVirtualAccountModule {}
