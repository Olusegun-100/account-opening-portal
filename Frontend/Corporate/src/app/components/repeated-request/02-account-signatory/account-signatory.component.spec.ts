import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountSignatoryComponent } from './account-signatory.component';

describe('AccountSignatoryComponent', () => {
  let component: AccountSignatoryComponent;
  let fixture: ComponentFixture<AccountSignatoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountSignatoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountSignatoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
