import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogActivitiesComponent } from './add-edit-dialog-activities.component';

describe('AddEditDialogActivitiesComponent', () => {
  let component: AddEditDialogActivitiesComponent;
  let fixture: ComponentFixture<AddEditDialogActivitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditDialogActivitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogActivitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
