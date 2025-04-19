import { Component } from '@angular/core';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'side-image',
  templateUrl: './side-image.component.html',
  styleUrls: ['./side-image.component.css']
})
export class SideImageComponent {

  constructor(
    private storage: StorageService,
  ) { }

  imageV1: any = this.storage.imageV1;
  imageV2: any = this.storage.imageV2;
  imageV3: any = this.storage.imageV3;
  imageV4: any = this.storage.imageV4;
  imageV5: any = this.storage.imageV5;
}
