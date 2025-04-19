import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

  const routes: Routes = [
    {
      path: '',
      loadChildren: () => import('./modules/initial-request.module').then(m => m.InitialRequestModule)
    },
    {
    // path: 'repeated',
    path: 'r',
    loadChildren: () => import('./modules/repeated-request.module').then(m => m.RepeatedRequestModule)
    },
    {
      //   path: 'share',
      path: 's',
      loadChildren: () => import('./modules/shared.module').then(m => m.SharedModule)
    },
    {
      path: '**', redirectTo: '/'
    },
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
