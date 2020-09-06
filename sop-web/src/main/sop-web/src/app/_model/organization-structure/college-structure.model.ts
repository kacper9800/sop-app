import {Faculty} from './faculty.model';

export interface ICollegeStructure {
  collegeId: number;
  collegeName: string;
  faculties: Faculty[];
}

export class CollegeStructure implements ICollegeStructure {
  collegeId: number;
  collegeName: string;
  faculties: Faculty[];
}

