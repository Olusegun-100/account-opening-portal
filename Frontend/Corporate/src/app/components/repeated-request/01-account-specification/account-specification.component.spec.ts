import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountSpecificationComponent } from './account-specification.component';

describe('AccountSpecificationComponent', () => {
  let component: AccountSpecificationComponent;
  let fixture: ComponentFixture<AccountSpecificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountSpecificationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountSpecificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
