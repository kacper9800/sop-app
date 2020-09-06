import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogCollegeStructureComponent } from './add-edit-dialog-college-structure.component';

describe('AddEditDialogCollegeStructureComponent', () => {
  let component: AddEditDialogCollegeStructureComponent;
  let fixture: ComponentFixture<AddEditDialogCollegeStructureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditDialogCollegeStructureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogCollegeStructureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
