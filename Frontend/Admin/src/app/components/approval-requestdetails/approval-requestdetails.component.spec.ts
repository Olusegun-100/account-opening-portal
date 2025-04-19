import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalRequestdetailsComponent } from './approval-requestdetails.component';

describe('ApprovalRequestdetailsComponent', () => {
  let component: ApprovalRequestdetailsComponent;
  let fixture: ComponentFixture<ApprovalRequestdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalRequestdetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovalRequestdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
