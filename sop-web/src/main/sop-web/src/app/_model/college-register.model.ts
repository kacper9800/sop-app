export interface ICollegeRegister {
  activationKey: string;
  collegeId: number;
  email: string;
  password: string;
}

export class CollegeRegister implements ICollegeRegister {
  activationKey: string;
  collegeId: number;
  email: string;
  password: string;
}

