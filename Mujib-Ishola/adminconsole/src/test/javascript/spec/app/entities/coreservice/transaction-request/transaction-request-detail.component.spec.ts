import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminconsoleTestModule } from '../../../../test.module';
import { TransactionRequestDetailComponent } from 'app/entities/coreservice/transaction-request/transaction-request-detail.component';
import { TransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';

describe('Component Tests', () => {
  describe('TransactionRequest Management Detail Component', () => {
    let comp: TransactionRequestDetailComponent;
    let fixture: ComponentFixture<TransactionRequestDetailComponent>;
    const route = ({ data: of({ transactionRequest: new TransactionRequest(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AdminconsoleTestModule],
        declarations: [TransactionRequestDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TransactionRequestDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TransactionRequestDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load transactionRequest on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.transactionRequest).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
