import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { UserLoginRequest } from 'src/app/models/userLoginRequest';
import { UserService } from 'src/app/services/user.service';

@Component({ 
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm: FormGroup;

    loading = false; //pomocna promenljiva za iskljucivanje dugmeta sto se okrece
    submitted = false; //pomocna promenljiva za ispaljivanje gresaka
    
    error = '';
    
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService: UserService
                ) { }


    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            email:    ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });

        
    }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;

        const user = new UserLoginRequest(this.loginForm.value.email, this.loginForm.value.password );
        
        
        this.userService.login(user)
            .pipe(first())
            .subscribe(() => {
                
                this.redirectToHomePage();

              },
                error => { 
                    //alert(error);
                    this.error = error;
                    this.loading = false;
                });
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    redirectToHomePage(){
        if(this.userService.isNormalUser()){
            this.submitted=false;
            alert("srecno si ulogovan kuuumeee!!");
            //this.router.navigate(['/patient/profile']);
        }
        if(this.userService.isPatient()){
            this.router.navigate(['/patient']);
        }
    }
}
