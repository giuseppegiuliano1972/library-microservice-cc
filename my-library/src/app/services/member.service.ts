import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Member } from '../models/member';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StorageService } from './storage.service';

//const URL_API = 'http://localhost:8082/member/';
const URL_API = environment.memberApi;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MemberService {
   


  constructor(private http: HttpClient, private storageService: StorageService) { }

  
  getListaMember(): Observable<Member[]> {

    return this.http.get<Member[]>(URL_API + 'lista/utenti');
  }

  addMember(member: Member): Observable<Member>{

    const body = JSON.stringify(member);
      
    return this.http.post<Member>(
      URL_API + 'addmember',
      body,
      httpOptions
    ).pipe(
      map(user => {
        console.log("Member: ", user);
        return user;
      }
      ));
  }
}
