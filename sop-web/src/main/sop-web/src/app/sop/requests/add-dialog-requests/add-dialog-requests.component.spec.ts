import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDialogRequestsComponent } from './add-dialog-requests.component';

describe('AddDialogRequestsComponent', () => {
  let component: AddDialogRequestsComponent;
  let fixture: ComponentFixture<AddDialogRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDialogRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDialogRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
