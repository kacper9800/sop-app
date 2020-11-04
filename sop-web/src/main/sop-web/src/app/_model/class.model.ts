export interface IClass {
  id?: number;
  name?: string;
  description?: string;
  startYear?: Date;
  endYear?: Date;
  tutorId?: number;
  tutorName?: string;
  amountOfStudents?: number;
}

export class Class implements IClass {
  id?: number;
  name?: string;
  description?: string;
  startYear?: Date;
  endYear?: Date;
  tutorId?: number;
  tutorName?: string;
  amountOfStudents?: number;
}

