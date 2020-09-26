import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AdminconsoleTestModule } from '../../../../test.module';
import { TransactionRequestUpdateComponent } from 'app/entities/coreservice/transaction-request/transaction-request-update.component';
import { TransactionRequestService } from 'app/entities/coreservice/transaction-request/transaction-request.service';
import { TransactionRequest } from 'app/shared/model/coreservice/transaction-request.model';

describe('Component Tests', () => {
  describe('TransactionRequest Management Update Component', () => {
    let comp: TransactionRequestUpdateComponent;
    let fixture: ComponentFixture<TransactionRequestUpdateComponent>;
    let service: TransactionRequestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AdminconsoleTestModule],
        declarations: [TransactionRequestUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TransactionRequestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TransactionRequestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransactionRequestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TransactionRequest(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TransactionRequest();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
