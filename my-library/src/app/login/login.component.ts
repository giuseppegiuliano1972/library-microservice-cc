import { Input, Component, Output, EventEmitter, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import {Router, ActivatedRoute} from '@angular/router';

import { AuthService } from '../services/auth.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  
  
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private authService: AuthService,
      private storageService: StorageService
      //private alertService: AlertService
  ) {
      // redirect to home if already logged in
      if (this.authService.currentUserValue) { 
          this.router.navigate(['/']);
      }
  }

  ngOnInit() {
      this.loginForm = this.formBuilder.group({
          userid: ['', Validators.required],
          password: ['', Validators.required]
      });

      if (this.storageService.isLoggedIn()) {
        this.isLoggedIn = true;
      }
      // get return url from route parameters or default to '/'
      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.loginForm.invalid) {
          return;
      }

      this.loading = true;
      /*this.authService.login(this.f['userid'].value, this.f['password'].value)
          .pipe(first())
          .subscribe(
              data => {
                console.log("ddd:", data);
                  this.isLoggedIn = true;
                  this.router.navigate([this.returnUrl]);
              },
              error => {
                  //this.alertService.error(error);
                  this.isLoggedIn = false;
                  this.loading = false;
              });*/
      console.log("iiiiii");
      this.authService.login(this.f['userid'].value, this.f['password'].value).subscribe(
                data => {
                  this.storageService.saveToken(data.accessToken);
                  this.storageService.saveUser(data);
          
                  this.isLoginFailed = false;
                  this.isLoggedIn = true;
                  this.roles = this.storageService.getUser().roles;
                  this.reloadPage();
                },
                err => {
                  this.errorMessage = err.error.message || err.statusText;;
                  this.isLoginFailed = true;
                }
              );
  }
 
  reloadPage(): void {
    window.location.reload();
  }
}
