import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternDialogBasicDataComponent } from './intern-dialog-basic-data.component';

describe('InternDialogBasicDataComponent', () => {
  let component: InternDialogBasicDataComponent;
  let fixture: ComponentFixture<InternDialogBasicDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternDialogBasicDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InternDialogBasicDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
