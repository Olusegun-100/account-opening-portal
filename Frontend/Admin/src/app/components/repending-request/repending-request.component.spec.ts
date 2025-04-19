import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RependingRequestComponent } from './repending-request.component';

describe('RependingRequestComponent', () => {
  let component: RependingRequestComponent;
  let fixture: ComponentFixture<RependingRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RependingRequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RependingRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
