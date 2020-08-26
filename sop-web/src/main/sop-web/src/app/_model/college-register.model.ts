export interface ICollegeRegister {
  collegeId: number;
  email: string;
  password: string;
}

export class CollegeRegister implements ICollegeRegister {
  collegeId: number;
  email: string;
  password: string;
}

