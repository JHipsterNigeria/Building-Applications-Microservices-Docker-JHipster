import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminconsoleTestModule } from '../../../../test.module';
import { VirtualAccountDetailComponent } from 'app/entities/coreservice/virtual-account/virtual-account-detail.component';
import { VirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';

describe('Component Tests', () => {
  describe('VirtualAccount Management Detail Component', () => {
    let comp: VirtualAccountDetailComponent;
    let fixture: ComponentFixture<VirtualAccountDetailComponent>;
    const route = ({ data: of({ virtualAccount: new VirtualAccount(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AdminconsoleTestModule],
        declarations: [VirtualAccountDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VirtualAccountDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VirtualAccountDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load virtualAccount on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.virtualAccount).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
