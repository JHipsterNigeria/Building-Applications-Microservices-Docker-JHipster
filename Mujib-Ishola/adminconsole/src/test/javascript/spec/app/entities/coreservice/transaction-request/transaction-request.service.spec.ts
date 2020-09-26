import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TransactionRequestService } from 'app/entities/coreservice/transaction-request/transaction-request.service';
import { ITransactionRequest, TransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';
import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';

describe('Service Tests', () => {
  describe('TransactionRequest Service', () => {
    let injector: TestBed;
    let service: TransactionRequestService;
    let httpMock: HttpTestingController;
    let elemDefault: ITransactionRequest;
    let expectedResult: ITransactionRequest | ITransactionRequest[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TransactionRequestService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TransactionRequest(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        TransactionType.WALLET_TOPUP,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TransactionRequest', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TransactionRequest()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TransactionRequest', () => {
        const returnedFromService = Object.assign(
          {
            amount: 1,
            channel: 'BBBBBB',
            currency: 'BBBBBB',
            sourceAccount: 'BBBBBB',
            sourceAccountBankCode: 'BBBBBB',
            sourceAccountName: 'BBBBBB',
            sourceNarration: 'BBBBBB',
            destinationAccount: 'BBBBBB',
            destinationAccountBankCode: 'BBBBBB',
            destinationAccountName: 'BBBBBB',
            destinationNarration: 'BBBBBB',
            statusWebHook: 'BBBBBB',
            transactionRef: 'BBBBBB',
            responseCode: 'BBBBBB',
            responseMessage: 'BBBBBB',
            transactionType: 'BBBBBB',
            schemeOwnerId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TransactionRequest', () => {
        const returnedFromService = Object.assign(
          {
            amount: 1,
            channel: 'BBBBBB',
            currency: 'BBBBBB',
            sourceAccount: 'BBBBBB',
            sourceAccountBankCode: 'BBBBBB',
            sourceAccountName: 'BBBBBB',
            sourceNarration: 'BBBBBB',
            destinationAccount: 'BBBBBB',
            destinationAccountBankCode: 'BBBBBB',
            destinationAccountName: 'BBBBBB',
            destinationNarration: 'BBBBBB',
            statusWebHook: 'BBBBBB',
            transactionRef: 'BBBBBB',
            responseCode: 'BBBBBB',
            responseMessage: 'BBBBBB',
            transactionType: 'BBBBBB',
            schemeOwnerId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TransactionRequest', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
