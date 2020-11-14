import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogDocumentsComponent } from './add-edit-dialog-documents.component';

describe('AddEditDialogDocumentsComponent', () => {
  let component: AddEditDialogDocumentsComponent;
  let fixture: ComponentFixture<AddEditDialogDocumentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDialogDocumentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
