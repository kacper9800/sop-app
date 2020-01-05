import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewInternComponent } from './new-intern.component';

describe('NewInternComponent', () => {
  let component: NewInternComponent;
  let fixture: ComponentFixture<NewInternComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewInternComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewInternComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
