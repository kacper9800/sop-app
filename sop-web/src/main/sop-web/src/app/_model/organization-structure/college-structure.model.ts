import {Faculty} from './faculty.model';

export interface ICollegeStructure {
  id: number;
  collegeName: string;
  faculties: Faculty[];
}

export class CollegeStructure implements ICollegeStructure {
  id: number;
  collegeName: string;
  faculties: Faculty[] = [];
}

