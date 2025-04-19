import { Component, ElementRef, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-upload-document',
  templateUrl: './upload-document.component.html',
  styleUrls: ['./upload-document.component.css']
})
export class UploadDocumentRpComponent {

  constructor(
    private domSanitizer: DomSanitizer, 
    private storage: StorageService,
    private router: Router,
    private api: ApiService
  ) { }

  downloadAccountOpeningMandateForm() {
    let link = document.createElement("a");
    link.download = "Account-Opening-Mandate";
    link.href = this.storage.accountMandate;
    link.click();
  }
  
  downloadPrincipalOfficerForm() {
    let link = document.createElement("a");
    link.download = "Principal-Officer";
    link.href = this.storage.principalOfficer;
    link.click();
  }
  
  downloadReferenceForm() {
    let link = document.createElement("a");
    link.download = "Reference-Form";
    link.href = this.storage.referenceForm;
    link.click();
  }

  customerId: any = "";
  routeTo: any = "";
  rependingData: any = [];
  account_services: any = [];

  ngOnInit(): void {
    
    if(sessionStorage.getItem('customer') !== null) {
      this.routeTo = sessionStorage.getItem('customer');
      this.customerId = sessionStorage.getItem('customer');
    };
    
    if(Object.keys(this.storage.rependingData).length < 1) {
      this.router.navigate(['/r/account-specification/' + this.routeTo]);
    } else {
      
      this.rependingData = this.storage.rependingData;
      // console.log(this.rependingData);

      if(this.rependingData.sectcode !== null) {
        this.rependingData.sectorName = (this.storage.getSectorInfo(this.rependingData.sectcode))[0].description;
      };
      if(this.rependingData.businesscode !== null) {
        this.rependingData.businessName = (this.storage.getBusinessTypeInfo(this.rependingData.businesscode))[0].decription;
      };
      if(this.rependingData.incomeBrackcode !== null) {
        this.rependingData.incomeName = (this.storage.getIncomeBracketInfo(this.rependingData.incomeBrackcode))[0].incomeBracketdescription;
      };

      //====================

      this.documentArray = this.rependingData.document
  //================    
  
      const storedValues = sessionStorage.getItem('r-account_services');
      if (storedValues) {
        const selectedValues = storedValues.split(',');
        this.account_services = selectedValues;
      }
  
      if(this.storage.documents.length > 0) {
        this.documentArray = this.storage.documents;
      }
      
      if(this.rependingData.accttypes !== null) {
        let selected_accountType = this.rependingData.accttypes;
        // console.log(selected_accountType);
  
        this.accountType = this.storage.getAccountTypeInfo(selected_accountType);
        // console.log(this.accountType);   
        
      };
  
  
  
      const storedAccountData = this.rependingData.accountheld;
      if (storedAccountData) {
        this.account_held_form = storedAccountData;
        // console.log(this.account_held_form);        
      }
  
      const storedContactData = this.rependingData.contact;
      if (storedContactData) {
        this.contact_person_form = storedContactData;
        // console.log(this.contact_person_form);
        
      }
  
      const storedSignatoryData = this.rependingData.signatories;
      if (storedSignatoryData) {
        this.signatories_form = storedSignatoryData;
        // console.log(this.signatories_form);
      }

    }
  }  
  
  // details: any = [];
  branch: any = [];
  // customerInfo: any = [];
  bvnInfo: any = [];
  account_held_form: any = [];
  contact_person_form: any = [];
  signatories_form: any = [];
  accountOfficer: any = [];
  // selected_accountType: any = null;
  agent: any = null;
  accountType: any = [];
  
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
  idCounter: any = 1000;


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
            let matchDocument = this.documentArray.filter((i: any) => (i.doc_type == docInfoArray[0].docName));
            // let matchOthersDocument = this.documentArray.filter((i: any) => (i.docName == this.otherDocument));
            let matchFile = this.documentArray.filter((i: any) => (i.file == docInfoArray[0].imageInfo.file));
  
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
                  // console.log(this.docInfo);
                  let newDoc = {
                    // doc_id: this.idCounter,
                    doc_id: this.storage.create_UUID(),
                    doc_type: this.docInfo.docName,
                    file_name: this.docInfo.imageInfo.name,
                    file: this.docInfo.imageInfo.file
                  }
                  this.documentArray.push(newDoc)
                  // console.log(newDoc);
                  
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
            // console.log(this.docInfo);
            let newDoc = {
              // doc_id: this.idCounter,
              doc_id: this.storage.create_UUID(),
              doc_type: this.docInfo.docName,
              file_name: this.docInfo.imageInfo.name,
              file: this.docInfo.imageInfo.file
            }
            // console.log(newDoc);
            this.documentArray.push(newDoc)
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
      info.file
      );
      this.base64imageName = info.file_name;
  }

  deleteDocument(event: any, info: any) {
    
    event.preventDefault()
    Swal.fire({ 
      title: 'Are you sure?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        let index = this.documentArray.findIndex((i: any) => i.doc_id === info.doc_id)
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
  

  submit(event: any) {
    event.stopPropagation();
    Swal.fire({
      html: 'Are you sure?',
      icon: 'warning',
      customClass: 'swal-wide',
      allowOutsideClick: false,
      allowEscapeKey: false,
      allowEnterKey: false,
      showCancelButton: true,
      cancelButtonColor: '#d33',
      confirmButtonColor: '#b1a154',
      // confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    }).then((result) => {
      if (result.isConfirmed) {

        // console.log(this.rependingData);
        
        let content = {
          "id": this.customerId,
          "account_officer": this.rependingData.officerName,
          "acct_name": this.rependingData.companyname,
          "acct_type": this.accountType[0].prodCode,
          "address_description": "",
          "alt_phonenumber": "",
          "branch_code": this.rependingData.branch_code,
          "bvn": this.rependingData.bvn,
          "contact_addressone": this.rependingData.contactaddressone,
          "contact_addresstwo": this.rependingData.contactaddressttwo,
          "country": "",
          "date": "2022-12-03",
          "date_open": "2022-12-03",
          "dateopen": "2022-12-03",
          "dob": "2022-12-03",
          "email": this.rependingData.email,
          "first_name": "",
          "gender": "",
          "last_name": "",
          "lga": this.rependingData.lga,          
          "marital_status": "",
          "middle_name": "",
          "mother_maidenname": "",
          "nationality": "",
          "permit_expiry_date": "2022-12-03",
          "permit_issue_date": "2022-12-03",          
          "phonenumber": this.rependingData.phonenumber,
          "place_of_birth": "",
          "place_of_permit_issue": "",
          "postal_code": "",
          "rcno": this.rependingData.rcno,
          "religion": "",
          "resident_permit_number": "",
          "residential_address": "",
          "residential_address_lga": "",
          "residential_address_state": "",
          "state": this.rependingData.stateoforigin,
          "state_of_origin": "",
          "timestamp": "2022-12-03",
          "tin": this.rependingData.tin,
          "title": "",
          "status": this.rependingData.status == "initiator-repending" ? "" : this.rependingData.status == "repending" ? "pending" : "",
          "supervisorId": this.rependingData.status == "initiator-repending" ? "" : this.rependingData.status == "repending" ? this.rependingData.supervisorId : "",
          "staffId": this.rependingData.status == "initiator-repending" ? "" : this.rependingData.status == "repending" ? this.rependingData.staffId : "",      

          "busType": this.rependingData.businesscode,
          "occCode":"",
          "employstatname":"",
          "incCBracketname":this.rependingData.incomeName,
          "busTypename":this.rependingData.businessName,
          "occCodename":"",
          "sectCodename": this.rependingData.sectorName,
          "genderName":"",

          "incBrack":this.rependingData.incomeBrackcode,
          "sectorCode": this.rependingData.sectcode,
          "empStat": "",


          "maritalstatusName":"",
          "religionName":"",
          "stateName":"",
          "lgaName":"",
          "nationalityName":"",
          "dateofincorparation": this.rependingData.dateofIncorparation.slice(0, 10),
          "juridificationofincorparation": this.rependingData.justisficationofIncorparation,
          "operatingaddress": this.rependingData.operatingBusiness,
          "website": this.rependingData.website,
          "crm": this.rependingData.crm,
          "specialcontrolunit":this.rependingData.specialunit,
          "iscompanyquoted":this.rependingData.iscompanyquoted,
          "companyquotedname":this.rependingData.companyquotedname,
          "agentname":this.rependingData.agentName,
          "accountservices": this.account_services.join(','),
          "documents": this.documentArray.map((i: any) => ({
            // doc_id: i.doc_id,
            file_name: i.file_name,
            doc_type: i.doc_type,
            file: i.file,
            // general_Id: this.customerId
          })),
          "accountheld":this.account_held_form.map((i: any) => ({ 
            accountName : i.accountName,
            accountNumber: i.accountNumber,
            nameANDaddress: i.nameANDaddress,
            status: i.status
          })),
          "contactperson": this.contact_person_form.map((i: any) => ({
            email: i.email,
            fullname: i.fullname,
            jobtitle: i.jobtitle,
            mobilenumber: i.mobilenumber,
            officeaddress: i.officeaddress
          })),
          "signatories":this.signatories_form.map((i: any) => ({ 
            email: i.email, gender: i.gender, alt_phonenumber: i.alt_phonenumber,
            dob: i.dob.slice(0, 10), first_name: i.first_name, last_name: i.last_name, 
            lga: i.lga, 
            marital_status: i.marital_status, religion: i.religion, resident_permit_number: i.resident_permit_number, 
            permit_expiry_date: i.permit_expiry_date.slice(0, 10), permit_issue_date: i.permit_issue_date.slice(0, 10),
            residential_address: i.residential_address,residential_address_state:i.residential_address_state,
              residential_address_lga: i.residential_address_lga,
            state_of_origin: i.state_of_origin, title: i.title, middle_name: i.middle_name, 
            mother_maidenname: i.mother_maidenname, phonenumber: i.phonenumber, place_of_birth: i.place_of_birth, 
            place_of_permit_issue: i.place_of_permit_issue, nationality: i.nationality,
            nextofkinName: i.nextofkinName, meansofidentification: i.meansofidentification, 
            identificationnumber: i.identificationnumber, tin: i.tin, idissuerdate: i.idissuerdate.slice(0 ,10), 
            idexpirydate: i.idexpirydate.slice(0, 10), occupation: i.occupation,
            jobtitle: i.jobtitle, 
            office: i.office, citizenshipstatus: i.citizenshipstatus, bvn: i.bvn,
            ssn: (i.ssn == 'undefined' || i.ssn == undefined) ? '' : i.ssn, mailingaddress: i.mailingaddress, countryofResidency: (i.countryofResidency == 'undefined' || i.countryofResidency == undefined) ? '' : i.countryofResidency
          }))
      
        }
        
        // console.log(content);        
    
        this.isLoading = true;
        // setTimeout(()=> this.isLoading = false, 5000);
        this.api.re_submitCorp(content).subscribe({
          next: (
            (res: any) => {
              // console.log(res);
              if (res.statuscode === "00") {
                // Swal.fire({
                //   html: `${res.statusmessage.toUpperCase()}`,
                //   // html: `ACCOUNT SUCCESSFULLY CREATED`,
                //   icon: 'success',
                //   customClass: 'swal-wide',
                //   confirmButtonColor: '#005D30',
                //   confirmButtonText: 'OK'
                // }).then(() => {
                // })
                this.isLoading = false;
                sessionStorage.clear();
                this.storage.accountList = null;
                this.storage.documents = [];
                this.router.navigate(["/s/success"]);
              }
              else if(res.statuscode === "96") {
                this.isLoading = false;
                this.storage.status_message_error(res);
              }
              else {
                this.isLoading = false;
                this.storage.global_error();
              }
          }
          ),
          error: (
            err => {
              this.isLoading = false;
              this.storage.global_error();
            }
          )
        });    
      }
    });
  };

  view(info: any) { 
    this.display = 'block';
    this.pdfSrc = info.file;    
  }
  
  close(){
    this.display = 'none';
  }

  pdfSrc: any = '';

  terms: Boolean = false;
  indemnity: Boolean = false;
  isLoading: Boolean = false;

  imageSpinner: any = this.storage.imageSpinner;

  display: any = 'none';

  
  documents: any[] = [
    {id: 1, text: "Account Opening Mandate"},    
    {id: 2, text: "Drivers License"},    
    {id: 3, text: "Employer ID"},
    {id: 4, text: "International Passport"},
    {id: 5, text: "NIN Document"},
    {id: 6, text: "Principal Officer"},
    {id: 7, text: "Reference Form"},
    {id: 8, text: "Tax Identification Number(TIN)"},    
    {id: 9, text: "Utility"},
    {id: 10, text: "Voters"},
  ];
}
