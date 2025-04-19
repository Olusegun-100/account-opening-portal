import { Component, ElementRef, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-upload-document',
  templateUrl: './upload-document.component.html',
  styleUrls: ['./upload-document.component.css']
})
export class UploadDocumentComponent {

  constructor(
    private domSanitizer: DomSanitizer, 
    private storage: StorageService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if(this.storage.documents.length > 0) {
      this.documentArray = this.storage.documents;
    }
  }  
  
  // details: any = [];
  // progress_bar: boolean = false;
  imageLogo: any = this.storage.imageLogo;
  getFile: any = null;
  getFileName: any = null;
  imageInfo: any = "";
  image: any = null;
  documentType: any = null;
  documentArray: any = [];
  documentUploadRequests: any = [];
  docInfo: any = {};
  base64imageName: any = '';
  base64image: any = '';
  @ViewChild('myInput')
  myInputVariable!: ElementRef;
  idCounter: any = 1;


  onFileSelected(event: any) {
    this.getFile = event.target.files[0]
    // console.log(this.getFile);
    this.getFileName = event.target.files[0].name;
    // console.log(this.getFileName);
    const reader = new FileReader();
    reader.onload = (e: any) => {
      const file = new Image();
      file.src = e.target.result;
      this.image = file.src || null
      // console.log(file.src);
      // console.log(file);
    };
    reader.onerror = function (error) {
      // console.log('Error: ', error); //@todo: Logo to a server somewhere?
    };
    reader.readAsDataURL(this.getFile);
  }
  
  showDocumentName: boolean=  false;
  otherDocument: any = null;
  
  add() {
    if(this.otherDocument !== null){
      let xx = JSON.parse(JSON.stringify(this.otherDocument.trim()))
      this.otherDocument = xx;
    }

    if(this.getFileName.split('.').pop() != 'pdf') {
      Swal.fire({
        html: 'Only PDF files can be uploaded',
        icon: 'warning',
        width: '500px',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });
    } else {
      if (this.documentType == null) {
        Swal.fire({
          html: '<strong>PLEASE SELECT A DOCUMENT TYPE!</strong>',
          icon: 'warning',
          width: '500px',
          confirmButtonColor: '#3085d6',
          confirmButtonText: 'OK'
        })
      }
      
      else {
        let id = "docId"
        let name = "docName"
        let file = "imageInfo"
        let imageValue: any = {
          name: this.getFileName,
          file: this.image
        }
        let documentType = JSON.parse(JSON.stringify(this.documentType));
        // if(documentType.text == 'Others'){
        //   documentType.text = this.otherDocument;
        // }
        this.docInfo = { [file]: imageValue, [name]: documentType.text//, [id]: documentType.value 
      }
  
  
        if (this.docInfo.imageInfo.file === null) {
          Swal.fire({
            html: '<strong>NO FILE HAS BEEN SELECTED FOR UPLOAD(S)!</strong>',
            icon: 'warning',
            width: '500px',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'OK'
          })
        }
        else {
          if(this.documentArray.length !== 0) {
            let docInfoArray: any = [];
            docInfoArray.push(this.docInfo)
            let matchDocument = this.documentArray.filter((i: any) => (i.docName == docInfoArray[0].docName));
            // let matchOthersDocument = this.documentArray.filter((i: any) => (i.docName == this.otherDocument));
            let matchFile = this.documentArray.filter((i: any) => (i.imageInfo.file == docInfoArray[0].imageInfo.file));
  
            if (matchDocument.length) {
              Swal.fire({
                html: '<strong> PLEASE SELECT A DIFFERENT DOCUMENT TYPE!</strong>',
                icon: 'warning',
                width: '500px',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'OK'
              })
            }
            else{
              if (matchFile.length) {
                Swal.fire({
                  html: '<strong> PLEASE SELECT A DIFFERENT FILE!</strong>',
                  icon: 'warning',
                  width: '500px',
                  confirmButtonColor: '#3085d6',
                  confirmButtonText: 'OK'
                  })
                }
                else {
                  let newDocuments = this.documentArray.filter((o1: any) => !docInfoArray.some((o2: any) => o1.docName === o2.docName))
                  let xx = newDocuments.slice(-1);
                  let yy = xx[0].id;
                  this.idCounter = yy + 1;
                  this.docInfo.id = this.idCounter++;
                  this.documentArray.push(this.docInfo)
                  // console.log(this.documentArray);
                  this.otherDocument = null;
                  this.showDocumentName = false;
                  this.documentType = null;
                  this.docInfo = null;
                  docInfoArray = null;
                  this.image = null;
                  this.reset();
                }
              }
            }
          if(this.documentArray.length === 0) {
            this.docInfo.id = this.idCounter++;
            this.documentArray.push(this.docInfo)
            // console.log(this.documentArray);
            this.otherDocument = null;
            this.documentType = null;
            this.docInfo = null;
            this.image = null;
            this.showDocumentName = false;
            this.reset();
          }
        }
      }
    }
  }

  //RESET "UPLOAD FILE" INPUT FIELD
  reset() {
    // console.log(this.myInputVariable.nativeElement.files);
    this.myInputVariable.nativeElement.value = '';
    // console.log(this.myInputVariable.nativeElement.files);
  }
  
  download(info: any) {
    // console.log(info);
    this.base64image = this.domSanitizer.bypassSecurityTrustUrl(
      info.imageInfo.file
      );
      this.base64imageName = info.imageInfo.name;
  }

  next () {
    if(this.documentArray.length < 1) {
      // alert('not allowed')
      Swal.fire({
        html: 'No document was uploaded',
        icon: 'warning',
        width: '500px',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });
    } else {
      this.storage.documents = this.documentArray;
      // sessionStorage.setItem('documents', JSON.stringify(this.documentArray));
      this.router.navigate(['/review-information']);
    }
  }

  deleteRow(event: any, info: any) {
    event.preventDefault()
    Swal.fire({ 
      title: 'Are you sure?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        let index = this.documentArray.findIndex((i: any) => i.id === info.id)
        if (index > -1) {
          this.documentArray.splice(index, 1)
        };
        Swal.fire(
          'Deleted!',
          '',
          'success'
          )
      }
    })
  }

  view(info: any) {
    // console.log(info);    
    this.display = 'block';
    this.pdfSrc = info.imageInfo.file;
    // console.log(this.pdfSrc);
    
  }
  
  close(){
    this.display = 'none';
  }

  pdfSrc: any = '';

  display: any = 'none';

  
  documents: any[] = [
    {id: 1, text: "Utility"},
    {id: 2, text: "Voters"},
    {id: 3, text: "NIN Document"},
    {id: 4, text: "International Passport"},
    {id: 5, text: "Drivers License"},    
    {id: 6, text: "Tax Identification Number(TIN)"},    
    {id: 7, text: "Employer ID"},
    {id: 8, text: "Reference Form"},
  ];
}
