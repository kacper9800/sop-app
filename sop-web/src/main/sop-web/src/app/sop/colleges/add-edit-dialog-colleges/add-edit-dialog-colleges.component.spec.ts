import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDialogCollegesComponent } from './add-edit-dialog-colleges.component';

describe('AddEditDialogCollegesComponent', () => {
  let component: AddEditDialogCollegesComponent;
  let fixture: ComponentFixture<AddEditDialogCollegesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditDialogCollegesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogCollegesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
