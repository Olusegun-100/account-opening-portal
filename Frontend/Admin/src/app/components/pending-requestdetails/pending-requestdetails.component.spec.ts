import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingRequestdetailsComponent } from './pending-requestdetails.component';

describe('PendingRequestdetailsComponent', () => {
  let component: PendingRequestdetailsComponent;
  let fixture: ComponentFixture<PendingRequestdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingRequestdetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PendingRequestdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
