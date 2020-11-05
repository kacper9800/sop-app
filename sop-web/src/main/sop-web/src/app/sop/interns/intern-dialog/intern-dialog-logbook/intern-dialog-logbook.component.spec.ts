import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternDialogLogbookComponent } from './intern-dialog-logbook.component';

describe('InternDialogLogbookComponent', () => {
  let component: InternDialogLogbookComponent;
  let fixture: ComponentFixture<InternDialogLogbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternDialogLogbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InternDialogLogbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
