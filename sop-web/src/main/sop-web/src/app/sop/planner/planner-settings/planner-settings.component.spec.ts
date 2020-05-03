import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannerSettingsComponent } from './planner-settings.component';

describe('PlannerSettingsComponent', () => {
  let component: PlannerSettingsComponent;
  let fixture: ComponentFixture<PlannerSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlannerSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlannerSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
