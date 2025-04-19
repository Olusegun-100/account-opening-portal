
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';

import { PersonalInformationComponent } from "../components/repeated-request/01-personal-information/personal-information.component";
import { AccountSpecificationComponent } from "../components/repeated-request/02-account-specification/account-specification.component";
import { UploadDocumentComponent } from "../components/repeated-request/03-upload-document/upload-document.component";
import { ReviewInformationComponent } from "../components/repeated-request/04-review-information/review-information.component";

const routes: Routes = [
  { path: 'personal-information/:id', component: PersonalInformationComponent },
  { path: 'account-specification', component: AccountSpecificationComponent },
  { path: 'upload-document', component: UploadDocumentComponent },
  { path: 'review-information', component: ReviewInformationComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class RepeatedRequestRoutingModule { }
