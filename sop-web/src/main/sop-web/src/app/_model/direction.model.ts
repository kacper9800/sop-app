export interface IDirection {
  id?: number;
  name?: string;
  description?: string;
  startExpirationDate?: Date;
  endExpirationDate?: Date;
  active?: boolean;
  removed?: boolean;
  studyMode?: string;
  facultyName?: string;
  instituteId?: number;
  instituteName?: string;
}

export class Direction implements IDirection {
  id?: number;
  name?: string;
  description?: string;
  startExpirationDate?: Date;
  endExpirationDate?: Date;
  active?: boolean;
  removed?: boolean;
  studyMode?: string;
  facultyName?: string;
  instituteId?: number;
  instituteName?: string;
}

