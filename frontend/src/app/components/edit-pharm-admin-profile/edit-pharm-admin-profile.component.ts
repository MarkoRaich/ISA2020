import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

import { PharmacyAdmin } from 'src/app/models/pharmacyAdmin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-pharm-admin-profile',
  templateUrl: './edit-pharm-admin-profile.component.html',
  styleUrls: ['./edit-pharm-admin-profile.component.css']
})
export class EditPharmAdminProfileComponent implements OnInit {


  PharmAdminForm : FormGroup;
  loggedPharmacyAdmin : PharmacyAdmin = new PharmacyAdmin(-1,"","","","");


  constructor(private formBuilder : FormBuilder,
              private toastr: ToastrService,
              private pharmacyAdminService : PharmacyAdminService,
              private userService : UserService ) { }

  ngOnInit() {

        this.PharmAdminForm = this.formBuilder.group({
          firstName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
          lastName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
          phoneNumber: new FormControl(null, [Validators.required, Validators.minLength(9), Validators.maxLength(10), Validators.pattern("[0-9]+")]),
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
      this.toastr.error("Unesite ispravne podatke.", 'Moj profil');
      return;
    }

    const admin = new PharmacyAdmin(this.loggedPharmacyAdmin.id,
                                    this.loggedPharmacyAdmin.email,
                                    this.PharmAdminForm.value.firstName,
                                    this.PharmAdminForm.value.lastName,
                                    this.PharmAdminForm.value.phoneNumber
                                    );

    this.pharmacyAdminService.put(admin).subscribe(
      () => {
        this.toastr.success("Uspesno Ste promenili Vase podatke.", 'Moj profil');
      },
      () => {
        this.toastr.error("Greska, pokusajte ponovo.", 'Moj profil');
      }
    );
  }


}
