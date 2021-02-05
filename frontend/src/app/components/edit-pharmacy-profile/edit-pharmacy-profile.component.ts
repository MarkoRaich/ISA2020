import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { Pharmacy } from 'src/app/models/Pharmacy';
import { PharmacyAdmin } from 'src/app/models/pharmacyAdmin';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-edit-pharmacy-profile',
  templateUrl: './edit-pharmacy-profile.component.html',
  styleUrls: ['./edit-pharmacy-profile.component.css']
})
export class EditPharmacyProfileComponent implements OnInit {

  editPharmacyForm: FormGroup;
  selectedPharmacy: Pharmacy;
  searchAdress: Subscription;

  constructor(private toastr: ToastrService,
              private pharmacyService: PharmacyService) { }

  ngOnInit()  {

    this.editPharmacyForm = new FormGroup({
      name: new FormControl(null, [Validators.required, Validators.maxLength(50)]),
      address: new FormControl(null, [Validators.required]),
      description: new FormControl(null, [Validators.required])
    });

    this.pharmacyService.getPharmacyInWhichPharmacyAdminWorks().subscribe((data: Pharmacy) => {
      this.selectedPharmacy = data;
      this.editPharmacyForm.patchValue(
        {
          'name': this.selectedPharmacy.name,
          'address': this.selectedPharmacy.address,
          'description': this.selectedPharmacy.description
        }
      );
    })

    this.searchAdress = this.pharmacyService.searchAddressPharmacyEmitter.subscribe(
      (pharmacy: Pharmacy) => {
        this.selectedPharmacy.address = pharmacy.address;
        this.editPharmacyForm.patchValue(
          {
            'address': this.selectedPharmacy.address
          }
        );
      }
    );
  }

  addressFocusOut() {
    const pharmacy = new Pharmacy(this.editPharmacyForm.value.name, this.editPharmacyForm.value.address,
      this.editPharmacyForm.value.description, this.selectedPharmacy.id);

    this.pharmacyService.editPharmacyEmitter.next(pharmacy);
  }

  edit() {
    if (this.editPharmacyForm.invalid) {
      this.toastr.error('Unesite ispravne podatke. ', "Profil apoteke");
      return;
    }
    if (!this.selectedPharmacy) {
      this.toastr.error('Odaberite apoteku. ', "Profil apoteke");
      return;
    }
    const pharmacy = new Pharmacy(this.editPharmacyForm.value.name, this.editPharmacyForm.value.address,
      this.editPharmacyForm.value.description, this.selectedPharmacy.id);

    this.pharmacyService.edit(pharmacy).subscribe(
      () => {
        this.toastr.success("Uspešno Ste izmenili podatke apoteke. ", "Profil apoteke");
      },
      () => {
        this.toastr.error('Apoteka sa ovim imenom već postoji. Nazovite je drugačije.  ',
        "Profil apoteke");

      }
    );
  }


}
