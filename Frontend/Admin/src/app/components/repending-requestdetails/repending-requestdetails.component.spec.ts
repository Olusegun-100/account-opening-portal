import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RependingRequestdetailsComponent } from './repending-requestdetails.component';

describe('RependingRequestdetailsComponent', () => {
  let component: RependingRequestdetailsComponent;
  let fixture: ComponentFixture<RependingRequestdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RependingRequestdetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RependingRequestdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
