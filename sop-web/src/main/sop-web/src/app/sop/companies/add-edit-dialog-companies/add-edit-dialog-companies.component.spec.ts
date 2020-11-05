import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {AddEditDialogCompaniesComponent} from './add-edit-dialog-companies.component';


describe('AddEditCompaniesComponent', () => {
  let component: AddEditDialogCompaniesComponent;
  let fixture: ComponentFixture<AddEditDialogCompaniesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditDialogCompaniesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDialogCompaniesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
