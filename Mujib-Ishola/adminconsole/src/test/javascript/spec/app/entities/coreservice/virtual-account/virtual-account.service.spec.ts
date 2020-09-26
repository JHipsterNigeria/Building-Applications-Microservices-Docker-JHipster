import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { VirtualAccountService } from 'app/entities/coreservice/virtual-account/virtual-account.service';
import { IVirtualAccount, VirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';
import { ProfileType } from 'app/shared/model/enumerations/profile-type.model';
import { AccountStatus } from 'app/shared/model/enumerations/account-status.model';

describe('Service Tests', () => {
  describe('VirtualAccount Service', () => {
    let injector: TestBed;
    let service: VirtualAccountService;
    let httpMock: HttpTestingController;
    let elemDefault: IVirtualAccount;
    let expectedResult: IVirtualAccount | IVirtualAccount[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VirtualAccountService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new VirtualAccount(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, ProfileType.PERSONAL, AccountStatus.NEW);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a VirtualAccount', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new VirtualAccount()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a VirtualAccount', () => {
        const returnedFromService = Object.assign(
          {
            customerId: 'BBBBBB',
            extCustomerId: 'BBBBBB',
            currency: 'BBBBBB',
            accountNumber: 'BBBBBB',
            availableBalance: 1,
            holdBalance: 1,
            minimumBalance: 1,
            accountType: 'BBBBBB',
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of VirtualAccount', () => {
        const returnedFromService = Object.assign(
          {
            customerId: 'BBBBBB',
            extCustomerId: 'BBBBBB',
            currency: 'BBBBBB',
            accountNumber: 'BBBBBB',
            availableBalance: 1,
            holdBalance: 1,
            minimumBalance: 1,
            accountType: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should delete a VirtualAccount', () => {
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
