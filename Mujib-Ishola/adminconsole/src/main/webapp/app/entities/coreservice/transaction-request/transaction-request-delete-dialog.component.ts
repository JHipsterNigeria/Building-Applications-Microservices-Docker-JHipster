import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';
import { TransactionRequestService } from './transaction-request.service';

@Component({
  templateUrl: './transaction-request-delete-dialog.component.html',
})
export class TransactionRequestDeleteDialogComponent {
  transactionRequest?: ITransactionRequest;

  constructor(
    protected transactionRequestService: TransactionRequestService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.transactionRequestService.delete(id).subscribe(() => {
      this.eventManager.broadcast('transactionRequestListModification');
      this.activeModal.close();
    });
  }
}
