

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SuccessComponent } from '../components/shared/success/success.component';
import { SharedRoutingModule } from '../routes/shared-routing.module';
import { SideImageComponent } from '../components/shared/side-image/side-image.component';
import { DynamicFormFieldComponent } from '../components/shared/dynamic-form-field/dynamic-form-field.component';

import { SafePipe } from '../safe.pipe';

@NgModule({
  declarations: [
      SideImageComponent,
      SuccessComponent,
      DynamicFormFieldComponent,
      SafePipe
    ],
    imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      SharedRoutingModule,
    ],
    exports: [
    SideImageComponent,
    SuccessComponent,
    DynamicFormFieldComponent,
    SafePipe
  ],
  providers: [],
  schemas: []
})
export class SharedModule { }