import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannerLocationsComponent } from './planner-locations.component';

describe('PlannerLocationsComponent', () => {
  let component: PlannerLocationsComponent;
  let fixture: ComponentFixture<PlannerLocationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlannerLocationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlannerLocationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
