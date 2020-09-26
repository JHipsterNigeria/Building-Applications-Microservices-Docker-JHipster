import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITransactionRequest, TransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';
import { TransactionRequestService } from './transaction-request.service';
import { TransactionRequestComponent } from './transaction-request.component';
import { TransactionRequestDetailComponent } from './transaction-request-detail.component';
import { TransactionRequestUpdateComponent } from './transaction-request-update.component';

@Injectable({ providedIn: 'root' })
export class TransactionRequestResolve implements Resolve<ITransactionRequest> {
  constructor(private service: TransactionRequestService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITransactionRequest> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((transactionRequest: HttpResponse<TransactionRequest>) => {
          if (transactionRequest.body) {
            return of(transactionRequest.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TransactionRequest());
  }
}

export const transactionRequestRoute: Routes = [
  {
    path: '',
    component: TransactionRequestComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'adminconsoleApp.coreserviceTransactionRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TransactionRequestDetailComponent,
    resolve: {
      transactionRequest: TransactionRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'adminconsoleApp.coreserviceTransactionRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TransactionRequestUpdateComponent,
    resolve: {
      transactionRequest: TransactionRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'adminconsoleApp.coreserviceTransactionRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TransactionRequestUpdateComponent,
    resolve: {
      transactionRequest: TransactionRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'adminconsoleApp.coreserviceTransactionRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
