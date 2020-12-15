import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogLogbooksComponent } from './add-edit-dialog-logbooks.component';

describe('AddEditDialogLogbooksComponent', () => {
  let component: AddEditDialogLogbooksComponent;
  let fixture: ComponentFixture<AddEditDialogLogbooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDialogLogbooksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogLogbooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
