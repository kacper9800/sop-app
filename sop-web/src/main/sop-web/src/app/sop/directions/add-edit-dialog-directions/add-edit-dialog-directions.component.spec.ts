import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogDirectionsComponent } from './add-edit-dialog-directions.component';

describe('AddEditDialogDirectionsComponent', () => {
  let component: AddEditDialogDirectionsComponent;
  let fixture: ComponentFixture<AddEditDialogDirectionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDialogDirectionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogDirectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
