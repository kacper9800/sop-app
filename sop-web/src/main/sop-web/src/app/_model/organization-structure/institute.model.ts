import {Department} from './department.model';

export interface IInstitute {
  id?: number;
  name?: string;
  departments?: Department[];
}

export class Institute implements IInstitute {
  id?: number;
  name?: string;
  departments?: Department[];
}

