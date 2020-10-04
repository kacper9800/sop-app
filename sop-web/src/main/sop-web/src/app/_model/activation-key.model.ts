export interface IActivationKey {
  value: string;
  expirationDateStart: Date;
  expirationDateEnd: Date;
  createdById: number;
  createdByName: string;
  collegeId: number;
  collegeName: string;
  facultyId: number;
  facultyName: string;
  instituteId: number;
  instituteName: string;
  departmentId: number;
  departmentName: string;
  remainingUses: number;
  numberOfUses: number;
  active: boolean;
}

export class ActivationKey implements IActivationKey {
  value: string;
  expirationDateStart: Date;
  expirationDateEnd: Date;
  createdById: number;
  createdByName: string;
  collegeId: number;
  collegeName: string;
  facultyId: number;
  facultyName: string;
  instituteId: number;
  instituteName: string;
  departmentId: number;
  departmentName: string;
  remainingUses: number;
  numberOfUses: number;
  active: boolean;
}

