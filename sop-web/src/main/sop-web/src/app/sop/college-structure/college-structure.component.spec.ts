import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CollegeStructureComponent } from './college-structure.component';

describe('CollegeStructureComponent', () => {
  let component: CollegeStructureComponent;
  let fixture: ComponentFixture<CollegeStructureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CollegeStructureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollegeStructureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
