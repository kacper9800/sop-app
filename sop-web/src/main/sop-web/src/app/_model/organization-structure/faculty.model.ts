import {Institute} from './institute.model';

export interface IFaculty {
  facultyId: number;
  facultyName: string;
  institutes: Institute[];
}

export class Faculty implements IFaculty {
  facultyId: number;
  facultyName: string;
  institutes: Institute[];
}

