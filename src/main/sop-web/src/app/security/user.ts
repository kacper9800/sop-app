export interface IUser {
  id?: number;
  username?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  phone?: string;
  birthDate?: Date;

}

export class User {
  id?: number;
  username?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  phone?: string;
  birthDate?: Date;

  static fromJson(json: IUser): User {
    const user: User = new User();
    user.id = json.id;
    user.password = json.password;
    user.firstName = json.firstName;
    user.lastName = json.lastName;
    user.email = json.email;
    user.phone = json.phone;
    user.birthDate = json.birthDate;
    return user;
  }
}
