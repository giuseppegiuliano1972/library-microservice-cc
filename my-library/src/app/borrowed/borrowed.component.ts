import { Component, OnInit } from '@angular/core';

import { LendingService } from '../services/lending.service';
import { Lending } from '../models/lending';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-borrowed',
  templateUrl: './borrowed.component.html',
  styleUrls: ['./borrowed.component.css']
})
export class BorrowedComponent implements OnInit {
  
  lending!: Lending[];
  
  form!: FormGroup;
  selectedItems: Lending[]  = [];
  

  constructor (private lendingService: LendingService, private fb: FormBuilder) {
   
  }
  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.array([])
    })
    this.lendingService.getListaPrestati().subscribe(response => {
        this.lending = response;
    })
  }
  
  onCheck(event: Lending){
     
    if (!this.selectedItems.includes(event)) {
      this.selectedItems.push(event);
    } else {
      var index = this.selectedItems.indexOf(event);
      if (index > -1) {
        this.selectedItems.splice(index, 1);
      }
    }

    console.log(this.selectedItems);
  }

  saveBook(){
    console.log(this.selectedItems);
    var formData: any = new FormData();
     const lendingItem: any  = [];
    
    this.selectedItems.forEach(element => {
      lendingItem.LEND_ID = element.LEND_ID;
      lendingItem.BOOK_ID= element.BOOK_ID;
      console.log("element:", element);
      this.lendingService.putReturnBook(lendingItem).subscribe(res => console.log(res));
    });
    
  }

}
