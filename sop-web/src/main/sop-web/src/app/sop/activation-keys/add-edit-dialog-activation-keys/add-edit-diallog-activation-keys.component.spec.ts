import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogActivationKeysComponent } from './activation-keys-dialog.component';

describe('ActivationKeysDialogComponent', () => {
  let component: AddEditDialogActivationKeysComponent;
  let fixture: ComponentFixture<AddEditDialogActivationKeysComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditDialogActivationKeysComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogActivationKeysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
