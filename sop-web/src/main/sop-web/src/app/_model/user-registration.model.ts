export interface IUserRegistration {
  token?: string;
  firstName?: string;
  lastName?: string;
  birthDate?: Date;
  email: string;
  sex: string;
  password: string;
}

export class UserRegistration implements IUserRegistration {
  token?: string;
  firstName?: string;
  lastName?: string;
  birthDate?: Date;
  email: string;
  sex: string;
  password: string;
}

