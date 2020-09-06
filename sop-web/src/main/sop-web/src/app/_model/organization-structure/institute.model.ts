import {Department} from './department.model';

export interface IInstitute {
  instituteId: number;
  instituteName: string;
  departments: Department[];
}

export class Institute implements IInstitute {
  instituteId: number;
  instituteName: string;
  departments: Department[];
}

