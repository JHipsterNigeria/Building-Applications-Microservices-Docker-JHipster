import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';

type EntityResponseType = HttpResponse<ITransactionRequest>;
type EntityArrayResponseType = HttpResponse<ITransactionRequest[]>;

@Injectable({ providedIn: 'root' })
export class TransactionRequestService {
  public resourceUrl = SERVER_API_URL + 'services/coreservice/api/transaction-requests';

  constructor(protected http: HttpClient) {}

  create(transactionRequest: ITransactionRequest): Observable<EntityResponseType> {
    return this.http.post<ITransactionRequest>(this.resourceUrl, transactionRequest, { observe: 'response' });
  }

  update(transactionRequest: ITransactionRequest): Observable<EntityResponseType> {
    return this.http.put<ITransactionRequest>(this.resourceUrl, transactionRequest, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITransactionRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITransactionRequest[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
