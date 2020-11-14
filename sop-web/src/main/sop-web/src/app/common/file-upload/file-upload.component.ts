import {Component, ElementRef, HostListener, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  @Input()
  public progress;
  // tslint:disable-next-line:ban-types
  public onChange: Function;
  public file: File | null = null;

  @HostListener('change', ['$event.target.files']) emitFiles(event: FileList) {
    const file = event && event.item(0);
    this.onChange(file);
    this.file = file;
  }

  constructor(private host: ElementRef<HTMLInputElement>) {
  }

  ngOnInit(): void {
  }


  writeValue(value: null) {
    // clear file input
    this.host.nativeElement.value = '';
    this.file = null;
  }

  // tslint:disable-next-line:ban-types
  registerOnChange(fn: Function) {
    this.onChange = fn;
  }

  // tslint:disable-next-line:ban-types
  registerOnTouched(fn: Function) {
  }

}

export function requiredFileType(type: string) {
  // tslint:disable-next-line:only-arrow-functions
  return function(control: FormControl) {
    const file = control.value;
    if (file) {
      const extension = file.name.split('.')[1].toLowerCase();
      if (type.toLowerCase() !== extension.toLowerCase()) {
        return {
          requiredFileType: true
        };
      }

      return null;
    }

    return null;
  };
}
