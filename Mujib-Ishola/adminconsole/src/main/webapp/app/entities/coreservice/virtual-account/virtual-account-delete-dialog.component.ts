import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';
import { VirtualAccountService } from './virtual-account.service';

@Component({
  templateUrl: './virtual-account-delete-dialog.component.html',
})
export class VirtualAccountDeleteDialogComponent {
  virtualAccount?: IVirtualAccount;

  constructor(
    protected virtualAccountService: VirtualAccountService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.virtualAccountService.delete(id).subscribe(() => {
      this.eventManager.broadcast('virtualAccountListModification');
      this.activeModal.close();
    });
  }
}
