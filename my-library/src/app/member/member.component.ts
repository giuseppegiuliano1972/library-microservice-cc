import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { first } from 'rxjs/operators';
import { MemberService } from '../services/member.service';
import { Member } from '../models/member';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {


  memberForm!: FormGroup;
  card_id!: any;

  constructor (private formBuilder: FormBuilder, private memberService: MemberService) {
  

  }

  ngOnInit(): void {
    this.memberForm = this.formBuilder.group({
      name: '',
      surname: '',
      address: '',
      email: '',
      codfiscale: ''
    });
  }

  get f() { return this.memberForm.controls; }

  onSubmit(){
    const member: Member = {
      NOME: this.f['name'].value,
      MEMBER_ID: 0,
      COGNOME: this.f['surname'].value,
      INDIRIZZO: this.f['address'].value,
      DATE_CARD: new Date(),
      EXPIRATION_DATE: new Date(),
      COD_FISCALE: this.f['codfiscale'].value,
      CARD_ID: 0,
      USERID: '',
      PASSWORD: '',
      EMAIL: this.f['email'].value,
      TOT_BOOK_BORROWED: 0
    }

    this.memberService.addMember(member)
          .pipe(first())
          .subscribe(
              data => {
                console.log("ret member:", data);
                this.card_id = data;
              },
              error => {
                 
              });
  }

}
