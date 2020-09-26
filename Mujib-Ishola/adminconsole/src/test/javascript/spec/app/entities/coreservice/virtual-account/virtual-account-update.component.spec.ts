import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AdminconsoleTestModule } from '../../../../test.module';
import { VirtualAccountUpdateComponent } from 'app/entities/coreservice/virtual-account/virtual-account-update.component';
import { VirtualAccountService } from 'app/entities/coreservice/virtual-account/virtual-account.service';
import { VirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';

describe('Component Tests', () => {
  describe('VirtualAccount Management Update Component', () => {
    let comp: VirtualAccountUpdateComponent;
    let fixture: ComponentFixture<VirtualAccountUpdateComponent>;
    let service: VirtualAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AdminconsoleTestModule],
        declarations: [VirtualAccountUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VirtualAccountUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VirtualAccountUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VirtualAccountService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VirtualAccount(123);
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
        const entity = new VirtualAccount();
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
