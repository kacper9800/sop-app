import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannerWorkHoursComponent } from './planner-work-hours.component';

describe('PlannerWorkHoursComponent', () => {
  let component: PlannerWorkHoursComponent;
  let fixture: ComponentFixture<PlannerWorkHoursComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlannerWorkHoursComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlannerWorkHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
