import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UserLoginRequest } from 'src/app/models/userLoginRequest';
import { UserService } from 'src/app/services/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({ 
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm: FormGroup;

    submitted = false; //pomocna promenljiva za ispaljivanje gresaka
    
    error = '';
    
    constructor(
        private formBuilder: FormBuilder,
        private toastr: ToastrService,
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
            this.toastr.error("Molimo unesite ispravno podatke.", 'Login');
            return;
        }


        const user = new UserLoginRequest(this.loginForm.value.email,
                                          this.loginForm.value.password 
                                         );
        
        
        this.userService.login(user).subscribe(
            () => {
                this.toastr.success("Uspešno Ste se ulogovali!", 'Login');
                this.redirectToHomePage();

              },
            (error) => {               
                this.toastr.error("Neispravni podaci. Pokušajte ponovo.", 'Login');
        })
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    redirectToHomePage(){
        if(this.userService.isPharmacyAdmin()){
           this.router.navigate(['/pharmacy-admin/edit-admin-profile']);
        }
        if(this.userService.isPatient()){
            this.router.navigate(['/patient']);
        }
    }
}
