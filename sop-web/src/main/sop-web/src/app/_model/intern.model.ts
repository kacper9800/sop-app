import {LogbookPost} from './logbook-post.model';

export interface IIntern {
  id: number;
  firstName: string;
  lastName: string;
  birthDate: Date;
  internId: number;
  internName: string;
  instituteId: number;
  instituteName: string;
  collegeId: number;
  collegeName: string;
  // companyId: number;
  // companyName: string;
  // position: string;
  // actualAmountOfHours: number;
  // amountOfHours: number;
  active: boolean;
  removed: boolean;
}

export class Intern implements IIntern {
  id: number;
  firstName: string;
  lastName: string;
  birthDate: Date;
  internId: number;
  internName: string;
  instituteId: number;
  instituteName: string;
  collegeId: number;
  collegeName: string;
  // companyId: number;
  // companyName: string;
  // position: string;
  // actualAmountOfHours: number;
  // amountOfHours: number;
  active: boolean;
  removed: boolean;
}

