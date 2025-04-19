import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyBvnComponent } from './verify-bvn.component';

describe('VerifyBvnComponent', () => {
  let component: VerifyBvnComponent;
  let fixture: ComponentFixture<VerifyBvnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifyBvnComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerifyBvnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
