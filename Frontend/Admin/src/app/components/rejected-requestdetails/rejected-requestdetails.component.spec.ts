import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectedRequestdetailsComponent } from './rejected-requestdetails.component';

describe('RejectedRequestdetailsComponent', () => {
  let component: RejectedRequestdetailsComponent;
  let fixture: ComponentFixture<RejectedRequestdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RejectedRequestdetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RejectedRequestdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
