import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DateAdapter } from '@angular/material/core';

import { Book } from '../models/book';
import { BookService } from '../services/book.service';
import { MemberService } from '../services/member.service';
import { LendingService } from '../services/lending.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Member } from '../models/member';
import { Lending } from '../models/lending';
import { Observable, startWith, map } from 'rxjs';
import {Router} from '@angular/router';


@Component({
  selector: 'app-book-detail-return',
  templateUrl: './book-detail-return.component.html',
  styleUrls: ['./book-detail-return.component.css']
})
export class BookDetailReturnComponent implements OnInit {
  book!: Book;
  lendInfo!: Lending;
  returnDate!: Date;
  memberCtrl= new FormControl<string | Member>('');
  members!:  Observable<Member[]>;
  membersArr: Member[] = [];
  borrowForm: FormGroup;
  idBook!: string;
 

  data: any;

  constructor (private bookService: BookService, private activateRoute: ActivatedRoute,
          private dateAdapter: DateAdapter<Date>, 
          private fb: FormBuilder, private memberService: MemberService,
          private lendingService: LendingService,
          private router: Router ) {
      this.dateAdapter.setLocale('it-IT'); 

      this.borrowForm = this.fb.group({
        returnDate: new FormControl(new Date(), [Validators.required]),
      //memberCtrl: new FormControl(new Member(),[Validators.required] )
      })
  }

  ngOnInit(): void {
    const id = this.activateRoute.snapshot.paramMap.get('id');
    this.idBook = id!;
    this.bookService.getBookById(id).subscribe(response => {
      this.book = response;
    })

    this.lendingService.gePrestitoByIdBook(id).subscribe(response => {
      this.lendInfo = response;
    })
  }

  returnBook(){

    console.log(this.borrowForm.get('returnDate')!.value);
    
    console.log("book id:" ,  this.idBook);
    
    var lendData: Lending = {
      BOOK_ID: Number(this.idBook),
      DUE_RETURN_DATE:  this.lendInfo.DUE_RETURN_DATE,
      COD_FISCALE : this.lendInfo.COD_FISCALE,
      ID_MEMBER : this.lendInfo.ID_MEMBER,
      COGNOME_MEMBER: this.lendInfo.COGNOME_MEMBER,
      DATE_LENDING: this.lendInfo.DATE_LENDING,
      ID_CARD: this.lendInfo.ID_CARD,
      LEND_ID: this.lendInfo.LEND_ID,
      NOME_MEMBER: this.lendInfo.NOME_MEMBER,
      TITOLO: this.lendInfo.TITOLO,
      USERID: this.lendInfo.USERID,
      RETURN_DATE: this.borrowForm.get('returnDate')!.value,
      STATUS: ''
     };
    
    
     console.log("lendData:" ,  lendData);

     this.lendingService.putReturnBook(lendData).subscribe((data: any) => {
       this.data = data;
       console.log("RET: ", this.data);
       this.router.navigate(['/lend']);
     });
    }

}
