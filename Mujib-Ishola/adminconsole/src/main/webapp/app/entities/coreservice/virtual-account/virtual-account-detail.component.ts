import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';

@Component({
  selector: 'jhi-virtual-account-detail',
  templateUrl: './virtual-account-detail.component.html',
})
export class VirtualAccountDetailComponent implements OnInit {
  virtualAccount: IVirtualAccount | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ virtualAccount }) => (this.virtualAccount = virtualAccount));
  }

  previousState(): void {
    window.history.back();
  }
}
