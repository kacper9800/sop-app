import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDialogRequestsComponent } from './view-dialog-requests.component';

describe('ViewDialogRequestsComponent', () => {
  let component: ViewDialogRequestsComponent;
  let fixture: ComponentFixture<ViewDialogRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDialogRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDialogRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
