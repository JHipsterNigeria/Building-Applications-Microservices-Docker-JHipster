import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVirtualAccount, VirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';
import { VirtualAccountService } from './virtual-account.service';
import { IProfile } from 'app/shared/model/coreservice/profile.model';
import { ProfileService } from 'app/entities/coreservice/profile/profile.service';
import { ITransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';
import { TransactionRequestService } from 'app/entities/coreservice/transaction-request/transaction-request.service';

type SelectableEntity = IProfile | ITransactionRequest;

@Component({
  selector: 'jhi-virtual-account-update',
  templateUrl: './virtual-account-update.component.html',
})
export class VirtualAccountUpdateComponent implements OnInit {
  isSaving = false;
  profiles: IProfile[] = [];
  transactionrequests: ITransactionRequest[] = [];

  editForm = this.fb.group({
    id: [],
    customerId: [null, [Validators.required]],
    extCustomerId: [null, [Validators.required]],
    currency: [null, [Validators.required]],
    accountNumber: [null, [Validators.required]],
    availableBalance: [],
    holdBalance: [],
    minimumBalance: [],
    accountType: [null, [Validators.required]],
    status: [null, [Validators.required]],
    accountHolderId: [],
    transactionRequestId: [],
  });

  constructor(
    protected virtualAccountService: VirtualAccountService,
    protected profileService: ProfileService,
    protected transactionRequestService: TransactionRequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ virtualAccount }) => {
      this.updateForm(virtualAccount);

      this.profileService.query().subscribe((res: HttpResponse<IProfile[]>) => (this.profiles = res.body || []));

      this.transactionRequestService
        .query()
        .subscribe((res: HttpResponse<ITransactionRequest[]>) => (this.transactionrequests = res.body || []));
    });
  }

  updateForm(virtualAccount: IVirtualAccount): void {
    this.editForm.patchValue({
      id: virtualAccount.id,
      customerId: virtualAccount.customerId,
      extCustomerId: virtualAccount.extCustomerId,
      currency: virtualAccount.currency,
      accountNumber: virtualAccount.accountNumber,
      availableBalance: virtualAccount.availableBalance,
      holdBalance: virtualAccount.holdBalance,
      minimumBalance: virtualAccount.minimumBalance,
      accountType: virtualAccount.accountType,
      status: virtualAccount.status,
      accountHolderId: virtualAccount.accountHolderId,
      transactionRequestId: virtualAccount.transactionRequestId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const virtualAccount = this.createFromForm();
    if (virtualAccount.id !== undefined) {
      this.subscribeToSaveResponse(this.virtualAccountService.update(virtualAccount));
    } else {
      this.subscribeToSaveResponse(this.virtualAccountService.create(virtualAccount));
    }
  }

  private createFromForm(): IVirtualAccount {
    return {
      ...new VirtualAccount(),
      id: this.editForm.get(['id'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
      extCustomerId: this.editForm.get(['extCustomerId'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      accountNumber: this.editForm.get(['accountNumber'])!.value,
      availableBalance: this.editForm.get(['availableBalance'])!.value,
      holdBalance: this.editForm.get(['holdBalance'])!.value,
      minimumBalance: this.editForm.get(['minimumBalance'])!.value,
      accountType: this.editForm.get(['accountType'])!.value,
      status: this.editForm.get(['status'])!.value,
      accountHolderId: this.editForm.get(['accountHolderId'])!.value,
      transactionRequestId: this.editForm.get(['transactionRequestId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVirtualAccount>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
