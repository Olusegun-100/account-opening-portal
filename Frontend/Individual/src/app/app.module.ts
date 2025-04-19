import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';

import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { SharedModule } from './modules/shared.module';
import { InitialRequestModule } from './modules/initial-request.module';
import { AppRoutingModule } from './app-routing.module';
import { RepeatedRequestModule } from './modules/repeated-request.module';
import { MaterialModule } from './material.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    InitialRequestModule,
    RepeatedRequestModule,
    NgxUiLoaderModule,
    SharedModule,
    // MaterialModule,
    // MatProgressBarModule,
    AppRoutingModule,

  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},],
  bootstrap: [AppComponent]
})
export class AppModule { }
