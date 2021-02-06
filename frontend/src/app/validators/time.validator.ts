import { FormGroup } from '@angular/forms';

export function TimeValidator(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {

        if (formGroup.controls[matchingControlName].errors && !formGroup.controls[matchingControlName].errors.mustMatch) {
            return;
        }

        if (formGroup.controls[controlName].value >= formGroup.controls[matchingControlName].value) {
            formGroup.controls[matchingControlName].setErrors({ timeError: true });
        } else {
            formGroup.controls[matchingControlName].setErrors(null);
        }
    };
}
