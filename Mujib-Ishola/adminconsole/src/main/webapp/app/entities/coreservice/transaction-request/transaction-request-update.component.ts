import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITransactionRequest, TransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';
import { TransactionRequestService } from './transaction-request.service';

@Component({
  selector: 'jhi-transaction-request-update',
  templateUrl: './transaction-request-update.component.html',
})
export class TransactionRequestUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    amount: [null, [Validators.required]],
    channel: [null, [Validators.required]],
    currency: [null, [Validators.required]],
    sourceAccount: [null, [Validators.required]],
    sourceAccountBankCode: [null, [Validators.required]],
    sourceAccountName: [],
    sourceNarration: [null, [Validators.required]],
    destinationAccount: [null, [Validators.required]],
    destinationAccountBankCode: [null, [Validators.required]],
    destinationAccountName: [],
    destinationNarration: [null, [Validators.required]],
    statusWebHook: [],
    transactionRef: [],
    responseCode: [],
    responseMessage: [],
    transactionType: [null, [Validators.required]],
    schemeOwnerId: [null, [Validators.required]],
  });

  constructor(
    protected transactionRequestService: TransactionRequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactionRequest }) => {
      this.updateForm(transactionRequest);
    });
  }

  updateForm(transactionRequest: ITransactionRequest): void {
    this.editForm.patchValue({
      id: transactionRequest.id,
      amount: transactionRequest.amount,
      channel: transactionRequest.channel,
      currency: transactionRequest.currency,
      sourceAccount: transactionRequest.sourceAccount,
      sourceAccountBankCode: transactionRequest.sourceAccountBankCode,
      sourceAccountName: transactionRequest.sourceAccountName,
      sourceNarration: transactionRequest.sourceNarration,
      destinationAccount: transactionRequest.destinationAccount,
      destinationAccountBankCode: transactionRequest.destinationAccountBankCode,
      destinationAccountName: transactionRequest.destinationAccountName,
      destinationNarration: transactionRequest.destinationNarration,
      statusWebHook: transactionRequest.statusWebHook,
      transactionRef: transactionRequest.transactionRef,
      responseCode: transactionRequest.responseCode,
      responseMessage: transactionRequest.responseMessage,
      transactionType: transactionRequest.transactionType,
      schemeOwnerId: transactionRequest.schemeOwnerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transactionRequest = this.createFromForm();
    if (transactionRequest.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionRequestService.update(transactionRequest));
    } else {
      this.subscribeToSaveResponse(this.transactionRequestService.create(transactionRequest));
    }
  }

  private createFromForm(): ITransactionRequest {
    return {
      ...new TransactionRequest(),
      id: this.editForm.get(['id'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      channel: this.editForm.get(['channel'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      sourceAccount: this.editForm.get(['sourceAccount'])!.value,
      sourceAccountBankCode: this.editForm.get(['sourceAccountBankCode'])!.value,
      sourceAccountName: this.editForm.get(['sourceAccountName'])!.value,
      sourceNarration: this.editForm.get(['sourceNarration'])!.value,
      destinationAccount: this.editForm.get(['destinationAccount'])!.value,
      destinationAccountBankCode: this.editForm.get(['destinationAccountBankCode'])!.value,
      destinationAccountName: this.editForm.get(['destinationAccountName'])!.value,
      destinationNarration: this.editForm.get(['destinationNarration'])!.value,
      statusWebHook: this.editForm.get(['statusWebHook'])!.value,
      transactionRef: this.editForm.get(['transactionRef'])!.value,
      responseCode: this.editForm.get(['responseCode'])!.value,
      responseMessage: this.editForm.get(['responseMessage'])!.value,
      transactionType: this.editForm.get(['transactionType'])!.value,
      schemeOwnerId: this.editForm.get(['schemeOwnerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionRequest>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
