import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternDialogDocumentsComponent } from './intern-dialog-documents.component';

describe('InternDialogDocumentsComponent', () => {
  let component: InternDialogDocumentsComponent;
  let fixture: ComponentFixture<InternDialogDocumentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternDialogDocumentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InternDialogDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
