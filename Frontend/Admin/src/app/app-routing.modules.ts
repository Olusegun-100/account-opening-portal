import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ApprovalRequestComponent } from "./components/approval-request/approval-request.component";
import { ApprovalRequestdetailsComponent } from "./components/approval-requestdetails/approval-requestdetails.component";
import { IndexComponent } from "./components/index/index.component";
import { LoginComponent } from "./components/login/login.component";
import { PendingApprovedrequestsComponent } from "./components/pending-approvedrequests/pending-approvedrequests.component";
import { PendingRequestComponent } from "./components/pending-request/pending-request.component";
import { PendingRequestdetailsComponent } from "./components/pending-requestdetails/pending-requestdetails.component";
import { RejectedRequestComponent } from "./components/rejected-request/rejected-request.component";
import { RejectedRequestdetailsComponent } from "./components/rejected-requestdetails/rejected-requestdetails.component";
import { RependingRequestComponent } from "./components/repending-request/repending-request.component";
import { RependingRequestdetailsComponent } from "./components/repending-requestdetails/repending-requestdetails.component";
import { AuthGuard } from "./services/auth.guard";

const routes: Routes = [
    {
        path: '',
        component: IndexComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'pending-request',
        component: PendingRequestComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'pending-approvingrequests',
        component: PendingApprovedrequestsComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'pending-requestdetails',
        component: PendingRequestdetailsComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'approval-request',
        component: ApprovalRequestComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'approval-requestdetails',
        component: ApprovalRequestdetailsComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'rejected-request',
        component: RejectedRequestComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'rejected-requestdetails',
        component: RejectedRequestdetailsComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'repending-request',
        component: RependingRequestComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'repending-requestdetails',
        component: RependingRequestdetailsComponent,
        canActivate: [AuthGuard]
    },
]
@NgModule({
    imports: [RouterModule.forRoot(routes,
         { scrollPositionRestoration: 'enabled' })
],
    exports: [RouterModule]
})

export class AppRoutingModule {}