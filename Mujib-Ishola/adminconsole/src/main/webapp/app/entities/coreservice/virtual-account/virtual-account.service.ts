import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';

type EntityResponseType = HttpResponse<IVirtualAccount>;
type EntityArrayResponseType = HttpResponse<IVirtualAccount[]>;

@Injectable({ providedIn: 'root' })
export class VirtualAccountService {
  public resourceUrl = SERVER_API_URL + 'services/coreservice/api/virtual-accounts';

  constructor(protected http: HttpClient) {}

  create(virtualAccount: IVirtualAccount): Observable<EntityResponseType> {
    return this.http.post<IVirtualAccount>(this.resourceUrl, virtualAccount, { observe: 'response' });
  }

  update(virtualAccount: IVirtualAccount): Observable<EntityResponseType> {
    return this.http.put<IVirtualAccount>(this.resourceUrl, virtualAccount, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVirtualAccount>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVirtualAccount[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
