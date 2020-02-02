import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivationKeysDialogComponent } from './activation-keys-dialog.component';

describe('ActivationKeysDialogComponent', () => {
  let component: ActivationKeysDialogComponent;
  let fixture: ComponentFixture<ActivationKeysDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivationKeysDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivationKeysDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
