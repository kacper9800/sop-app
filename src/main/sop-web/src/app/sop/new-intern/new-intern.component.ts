import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-intern',
  templateUrl: './new-intern.component.html',
  styleUrls: ['./new-intern.component.css']
})
export class NewInternComponent implements OnInit {

  newInternForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.newInternForm = this.fb.group({
      name: new FormControl({value: null, disabled: false}, Validators.required),
      lastname: new FormControl({value: null, disabled: false}, Validators.required),
      phone: new FormControl({value: null, disabled: false}, Validators.required),
      email: new FormControl({
        value: null,
        disabled: false
      }, Validators.compose([Validators.required, Validators.email])),
      birthDate: new FormControl({value: null, disabled: false}, Validators.required),
      genderId: new FormControl({value: null, disabled: false}, Validators.required),
      voivoId: new FormControl({value: null, disabled: false}, Validators.required),
      disctrictId: new FormControl({value: null, disabled: false}, Validators.required),
      professionalTitleId: new FormControl({value: null, disabled: false}, Validators.required),
      titleOfMasters: new FormControl({value: null, disabled: false}, Validators.required),
      graduatedSchool: new FormControl({value: null, disabled: false}, Validators.required),
      yearSchoolEnd: new FormControl({value: null, disabled: false}, Validators.required),
      diplomaNumber: new FormControl({value: null, disabled: false}, Validators.required),
      diplomaIssueDate: new FormControl({value: null, disabled: false}, Validators.required),
      postGraduateEducationId: new FormControl({value: null, disabled: false}, Validators.required),
      issuingCertificate: new FormControl({value: null, disabled: false}),
      academicDegreeId: new FormControl({value: null, disabled: false}),
      awardingAuthorityId: new FormControl({value: null, disabled: false}),
      academicTitleId: new FormControl({value: null, disabled: false}),
      authorityGrantingId: new FormControl({value: null, disabled: false}),
      dateOfIssue: new FormControl({value: null, disabled: false}),
      trainingAgreement: new FormControl({value: null, disabled: false}),
      offersAgreement: new FormControl({value: null, disabled: false}),
      agreeRodo: new FormControl({value: null, disabled: false}, Validators.required),
      agreeInfo: new FormControl({value: null, disabled: false}, Validators.required),
      question: new FormControl({value: null, disabled: false}, Validators.required),
      answer: new FormControl({value: null, disabled: false}, Validators.required)
    });
  }

  templateFormSubmit(value: any) {
    
  }
}
