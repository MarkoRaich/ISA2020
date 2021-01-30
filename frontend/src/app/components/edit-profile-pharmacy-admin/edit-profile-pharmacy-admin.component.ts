import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { PharmacyAdmin } from 'src/app/models/PharmacyAdmin';

import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-profile-pharmacy-admin',
  templateUrl: './edit-profile-pharmacy-admin.component.html',
  styleUrls: ['./edit-profile-pharmacy-admin.component.css']
})
export class EditProfilePharmacyAdminComponent implements OnInit {


  PharmAdminForm : FormGroup;
  loggedPharmacyAdmin : PharmacyAdmin = new PharmacyAdmin("","","","",-1);


  constructor(private formBuilder : FormBuilder,
              private toastr: ToastrService,
              private pharmacyAdminService : PharmacyAdminService,
              private userService : UserService ) { }

  ngOnInit() {

        this.PharmAdminForm = this.formBuilder.group({
          firstName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
          lastName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
          phoneNumber: new FormControl(null, [Validators.required, Validators.minLength(9), Validators.maxLength(10), Validators.pattern("0[0-9]+")]),
        });

        this.pharmacyAdminService.get(this.userService.getLoggedInUser().id).subscribe(
          (responseData: PharmacyAdmin) => {
            this.loggedPharmacyAdmin = responseData;
            this.PharmAdminForm.patchValue(
              {
                'firstName': this.loggedPharmacyAdmin.firstName,
                'lastName': this.loggedPharmacyAdmin.lastName,
                'phoneNumber': this.loggedPharmacyAdmin.phoneNumber,
              }
            );
          },
          () => {
            this.userService.logout();
          }
        );

  }

  saveChanges() {
    if (this.PharmAdminForm.invalid) {
      this.toastr.error("Unesite ispravne podatke.", 'Edit personal information');
      return;
    }

    const admin = new PharmacyAdmin(this.loggedPharmacyAdmin.email, this.PharmAdminForm.value.firstName, this.PharmAdminForm.value.lastName,
      this.PharmAdminForm.value.phoneNumber, this.loggedPharmacyAdmin.id);

    this.pharmacyAdminService.put(admin).subscribe(
      () => {
        this.toastr.success("Uspesno Ste promenili Vase podatke.", 'Edit personal information');
      },
      () => {
        this.toastr.error("Greska, pokusajte ponovo.", 'Edit personal information');
      }
    );
  }


}
