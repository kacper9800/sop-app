import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogRequestsComponent } from './add-edit-dialog-requests.component';

describe('AddEditDialogRequestsComponent', () => {
  let component: AddEditDialogRequestsComponent;
  let fixture: ComponentFixture<AddEditDialogRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDialogRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
