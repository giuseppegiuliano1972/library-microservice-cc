import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { StorageService } from './storage.service';

//const AUTH_API = 'http://localhost:8084/auth/';
const AUTH_API = environment.authApi;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class AuthService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient, private router: Router, private storageService: StorageService) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(sessionStorage.getItem('currentUser')!));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string): Observable<any> {
      console.log("uno:", username);
      return this.http.post<any>(AUTH_API + 'login', {
        "USERID": username,
        "PASSWORD": password
      }, httpOptions);
    }

    register(username: string, email: string, password: string): Observable<any> {
      jsonObject: JSON;
      console.log(username);
      const arrayObj: any = [
       {
        "USERID": username,
        "PASSWORD": password,
        "EMAIL": email
      }];

      const jsonObject = <JSON>arrayObj;
      
      console.log(jsonObject);
      return this.http.post<any>(AUTH_API + 'signup', 
      {
        "USERID": username,
        "PASSWORD": password,
        "EMAIL": email
      }
      , httpOptions);
    }
    
    logout() {
        // remove user from local storage to log user out
        this.storageService.clean();
        this.currentUserSubject.next(new User());
        window.location.reload();

    }
}