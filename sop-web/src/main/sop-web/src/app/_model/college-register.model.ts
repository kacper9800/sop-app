export interface ICollegeRegister {
  token: string;
  collegeId: number;
  email: string;
  password: string;
}

export class CollegeRegister implements ICollegeRegister {
  token: string;
  collegeId: number;
  email: string;
  password: string;
}

