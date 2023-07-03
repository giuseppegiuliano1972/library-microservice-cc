import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LendingComponent } from './lending/lending.component';
import { BorrowedComponent } from './borrowed/borrowed.component';
import { SearchComponent } from './search/search.component';
import { BookdetailComponent } from './bookdetail/bookdetail.component';
import { BookDetailReturnComponent } from './book-detail-return/book-detail-return.component';
import { MemberComponent } from './member/member.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'lend', component: SearchComponent },
  { path: 'list-borrowed', component: BorrowedComponent },
  { path: 'book-detail/:id', component: BookdetailComponent},
  { path: 'book-detail-return/:id', component: BookDetailReturnComponent},
  { path: 'add-member', component: MemberComponent},
  { path: 'add-app-user', component: SignupComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' }
 
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
