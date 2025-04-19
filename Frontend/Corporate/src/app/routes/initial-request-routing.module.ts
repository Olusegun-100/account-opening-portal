
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';

import { VerifyBvnComponent } from "../components/initial-request/01-verify-bvn/verify-bvn.component";
import { AccountSpecificationComponent } from "../components/initial-request/02-account-specification/account-specification.component";
import { UploadDocumentComponent } from "../components/initial-request/04-upload-document/upload-document.component";
// import { ReviewInformationComponent } from "../components/initial-request/05-review-information/review-information.component";
import { AccountSignatoryComponent } from "../components/initial-request/03-account-signatory/account-signatory.component";

const routes: Routes = [
  { path: '', redirectTo: 'verify-bvn', pathMatch: 'full'},
  { path: 'verify-bvn', component: VerifyBvnComponent },
  { path: 'account-specification', component: AccountSpecificationComponent },
  { path: 'account-signatory', component: AccountSignatoryComponent },
  { path: 'upload-document', component: UploadDocumentComponent },
  // { path: 'review-information', component: ReviewInformationComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class InitialRequestRoutingModule { }
