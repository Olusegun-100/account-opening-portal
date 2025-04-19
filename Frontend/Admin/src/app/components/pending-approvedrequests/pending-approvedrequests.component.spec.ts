import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingApprovedrequestsComponent } from './pending-approvedrequests.component';

describe('PendingApprovedrequestsComponent', () => {
  let component: PendingApprovedrequestsComponent;
  let fixture: ComponentFixture<PendingApprovedrequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingApprovedrequestsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PendingApprovedrequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
