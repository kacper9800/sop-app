export interface IUser {
  id?: number;
  userName?: string;
  firstName?: string;
  lastName?: string;
  birthDate?: Date;
  activated?: boolean;

}

export class User implements IUser {

  constructor(id?: number, userName?: string, firstName?: string, lastName?: string, birthDate?: Date, activated?: boolean) {
    this.id = id;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.activated = activated;
  }

  id?: number;
  userName?: string;
  firstName?: string;
  lastName?: string;
  birthDate?: Date;
  activated?: boolean;
}
