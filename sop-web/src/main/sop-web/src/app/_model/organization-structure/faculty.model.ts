import {Institute} from './institute.model';

export interface IFaculty {
  id: number;
  name: string;
  institutes: Institute[];
}

export class Faculty implements IFaculty {
  id: number;
  name: string;
  institutes: Institute[] = [];
}

