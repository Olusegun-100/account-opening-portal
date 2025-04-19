import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.modules';

import { AppComponent } from './app.component';
import { IndexComponent } from './components/index/index.component';
import { LoginComponent } from './components/login/login.component';
import { PendingRequestComponent } from './components/pending-request/pending-request.component';
import { PendingRequestdetailsComponent } from './components/pending-requestdetails/pending-requestdetails.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { HeaderComponent } from './shared/header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { ApprovalRequestComponent } from './components/approval-request/approval-request.component';
import { ApprovalRequestdetailsComponent } from './components/approval-requestdetails/approval-requestdetails.component';
import { RejectedRequestComponent } from './components/rejected-request/rejected-request.component';
import { RejectedRequestdetailsComponent } from './components/rejected-requestdetails/rejected-requestdetails.component';
import { PendingApprovedrequestsComponent } from './components/pending-approvedrequests/pending-approvedrequests.component';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { RependingRequestComponent } from './components/repending-request/repending-request.component';
import { RependingRequestdetailsComponent } from './components/repending-requestdetails/repending-requestdetails.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { SafePipe } from './directives/safe.pipe';

@NgModule({
  declarations: [
    AppComponent,
    SafePipe,
    IndexComponent,
    LoginComponent,
    PendingRequestComponent,
    PendingRequestdetailsComponent,
    SidebarComponent,
    HeaderComponent,
    ApprovalRequestComponent,
    ApprovalRequestdetailsComponent,
    RejectedRequestComponent,
    RejectedRequestdetailsComponent,
    PendingApprovedrequestsComponent,
    RependingRequestComponent,
    RependingRequestdetailsComponent
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxUiLoaderModule,
    Ng2SearchPipeModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
