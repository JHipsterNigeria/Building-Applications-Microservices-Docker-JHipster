import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';

@Component({
  selector: 'jhi-transaction-request-detail',
  templateUrl: './transaction-request-detail.component.html',
})
export class TransactionRequestDetailComponent implements OnInit {
  transactionRequest: ITransactionRequest | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactionRequest }) => (this.transactionRequest = transactionRequest));
  }

  previousState(): void {
    window.history.back();
  }
}
