import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannerActivitiesComponent } from './planner-activities.component';

describe('PlannerActivitiesComponent', () => {
  let component: PlannerActivitiesComponent;
  let fixture: ComponentFixture<PlannerActivitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlannerActivitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlannerActivitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
