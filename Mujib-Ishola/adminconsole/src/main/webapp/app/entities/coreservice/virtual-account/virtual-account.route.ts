import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVirtualAccount, VirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';
import { VirtualAccountService } from './virtual-account.service';
import { VirtualAccountComponent } from './virtual-account.component';
import { VirtualAccountDetailComponent } from './virtual-account-detail.component';
import { VirtualAccountUpdateComponent } from './virtual-account-update.component';

@Injectable({ providedIn: 'root' })
export class VirtualAccountResolve implements Resolve<IVirtualAccount> {
  constructor(private service: VirtualAccountService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVirtualAccount> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((virtualAccount: HttpResponse<VirtualAccount>) => {
          if (virtualAccount.body) {
            return of(virtualAccount.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new VirtualAccount());
  }
}

export const virtualAccountRoute: Routes = [
  {
    path: '',
    component: VirtualAccountComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'adminconsoleApp.coreserviceVirtualAccount.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VirtualAccountDetailComponent,
    resolve: {
      virtualAccount: VirtualAccountResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'adminconsoleApp.coreserviceVirtualAccount.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VirtualAccountUpdateComponent,
    resolve: {
      virtualAccount: VirtualAccountResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'adminconsoleApp.coreserviceVirtualAccount.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VirtualAccountUpdateComponent,
    resolve: {
      virtualAccount: VirtualAccountResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'adminconsoleApp.coreserviceVirtualAccount.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
