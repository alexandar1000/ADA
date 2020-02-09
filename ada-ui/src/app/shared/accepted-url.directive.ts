import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, AbstractControl, ValidatorFn } from '@angular/forms';

@Directive({
  selector: '[appAcceptedUrl]',
  providers: [{provide: NG_VALIDATORS, useExisting: AcceptedUrlDirective, multi: true}]
})
export class AcceptedUrlDirective {
  @Input('appAcceptedUrl') acceptedUrl: string;

  
  validate(control: AbstractControl): {[key: string]: any} | null {
    return this.acceptedUrl ? acceptedUrlValidator(new RegExp(this.acceptedUrl, 'i'))(control)
                              : null;
  }
}

export function acceptedUrlValidator(urlRe: RegExp): ValidatorFn {
  return (control: AbstractControl): {[key: string]: any} | null => {
    const accepted = urlRe.test(control.value);
    return accepted ? null : {'forbiddenUrl': {value: control.value}};
  };
}