import { FormGroup } from '@angular/forms';

export function MustMatch(controlName: string, matchingControlName: string) {
  return (formGroup: FormGroup) => {

    if (formGroup.controls[matchingControlName].errors && !formGroup.controls[matchingControlName].errors.mustMatch) {
      return;
    }

    if (formGroup.controls[controlName].value !== formGroup.controls[matchingControlName].value) {
      formGroup.controls[matchingControlName].setErrors({ mustMatch: true });
    } else {
      formGroup.controls[matchingControlName].setErrors(null);
    }
  };
}
