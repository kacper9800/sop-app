import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerRequestDialogRequestsComponent } from './answer-request-dialog-requests.component';

describe('AnswerRequestDialogRequestsComponent', () => {
  let component: AnswerRequestDialogRequestsComponent;
  let fixture: ComponentFixture<AnswerRequestDialogRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnswerRequestDialogRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerRequestDialogRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
