import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternDialogComponent } from './intern-dialog.component';

describe('InternDialogComponent', () => {
  let component: InternDialogComponent;
  let fixture: ComponentFixture<InternDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InternDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
