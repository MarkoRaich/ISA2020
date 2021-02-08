import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UserNewPass } from 'src/app/models/userNewPass';
import { UserService } from 'src/app/services/user.service';
import { MustMatch } from 'src/app/validators/must-match.validator';

@Component({
  selector: 'app-user-change-password',
  templateUrl: './user-change-password.component.html',
  styleUrls: ['./user-change-password.component.css']
})
export class UserChangePasswordComponent implements OnInit {
  
    changePasswordForm: FormGroup;

    constructor(private userService: UserService, 
                private toastr: ToastrService,
                private formBuilder: FormBuilder) { }

    ngOnInit() {
      this.changePasswordForm = this.formBuilder.group({
        userEmail: new FormControl('', [Validators.required, Validators.email]),
        userOldPassword: new FormControl('', [Validators.required, ]),
        newPassword: new FormControl('', [Validators.required, ]),
        repeatedPassword: new FormControl('', [Validators.required,])
      }, {
        validator: MustMatch('newPassword', 'repeatedPassword')
      });

    }

    changePassword() {
      if (this.changePasswordForm.invalid) {
        this.toastr.error("Unesite ispravne podatke.", 'Izmena lozinke');
        return;
      }
      if (this.changePasswordForm.value.newPassword !== this.changePasswordForm.value.repeatedPassword) {
        this.toastr.error("Nova i ponovljena lozinka morajiu biti iste", 'Izmena lozinke');
        return;
      }
      const user = new UserNewPass(this.changePasswordForm.value.userEmail, this.changePasswordForm.value.userOldPassword, this.changePasswordForm.value.newPassword);

      this.userService.changePassword(user).subscribe(
        () => {
          this.changePasswordForm.reset();
          this.toastr.success("Uspešno promenjena lozinka. Prijavite se sa novom lozinkom.", 'Izmena lozinke');
          this.userService.logout();
        },
        () => {
          this.toastr.error("Stara lozinka je neispravna. Pokušajte ponovo.", 'Izmena lozinke');
        }
      );
    }


}
