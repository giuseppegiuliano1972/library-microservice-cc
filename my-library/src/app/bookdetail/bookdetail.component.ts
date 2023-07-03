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
import { Router } from '@angular/router';

@Component({
  selector: 'app-bookdetail',
  templateUrl: './bookdetail.component.html',
  styleUrls: ['./bookdetail.component.css']
})
export class BookdetailComponent implements OnInit{

  book!: Book;
  lendDate!: Date;
  memberCtrl= new FormControl<string | Member>('');
  members!:  Observable<Member[]>;
  membersArr: Member[] = [];
  borrowForm: FormGroup;
  idBook!: string;
 

  data: any;

  constructor (private bookService: BookService, private activateRoute: ActivatedRoute,private dateAdapter: DateAdapter<Date>, 
                    private fb: FormBuilder, private memberService: MemberService, 
                    private lendingService: LendingService,
                    private router: Router ) {
    this.dateAdapter.setLocale('it-IT'); 
        
    this.borrowForm = this.fb.group({
      lendDate: new FormControl(new Date(), [Validators.required]),
      //memberCtrl: new FormControl(new Member(),[Validators.required] )
    })

    this.members = this.memberCtrl.valueChanges
    .pipe(
      startWith(''),
      map(value => {
          const name = typeof value === 'string' ? value : value?.COGNOME;
          return name ? this._filter(name as string) : this.membersArr.slice();
        }),
     );
  }

  ngOnInit(): void {
    const id = this.activateRoute.snapshot.paramMap.get('id');
    this.idBook = id!;
    this.bookService.getBookById(id).subscribe(response => {
      this.book = response;
    })

    this.memberService.getListaMember().subscribe(response => {
      console.log(response);
      this.membersArr = response;
    })


  }

  borrowBook(){

    console.log(this.borrowForm.get('lendDate')!.value);
    
    const member: Member =  this.memberCtrl.value as Member;
    console.log("memb:" , member.MEMBER_ID);

    console.log("book id:" ,  this.idBook);
    
    var lendData: Lending = {
      BOOK_ID: Number(this.idBook),
      DUE_RETURN_DATE: this.borrowForm.get('lendDate')!.value,
      COD_FISCALE : member.COD_FISCALE,
      ID_MEMBER : member.MEMBER_ID,
    COGNOME_MEMBER: member.COGNOME,
    DATE_LENDING: new Date(),
    ID_CARD: member.CARD_ID,
    LEND_ID:0,
    NOME_MEMBER:member.NOME,
    TITOLO: '',
    USERID: member.USERID,
    RETURN_DATE: new Date(),
     STATUS: ''
     };
    
    
    console.log("lendData:" ,  lendData);

      this.lendingService.postLibro(lendData).subscribe((data: any) => {
        this.data = data;
        console.log("RET: ", this.data);
        this.router.navigate(['/lend']);
      });
    }


  private _filter(name: string): Member[] {
    const filterValue = name.toLowerCase();

    return this.membersArr.filter(member => member.COGNOME.toLowerCase().includes(filterValue));
  }

  displayFn(member: Member): string {
    
    return member && member.COGNOME ? member.COGNOME : '';
  }
}
