export interface IActivationKey {
  value: string;
  startExpirationDate: Date;
  endExpirationDate: Date;
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
  companyId: number;
  companyName: string;
  remainingUses: number;
  numberOfUses: number;
  active: boolean;
}

export class ActivationKey implements IActivationKey {
  value: string;
  startExpirationDate: Date;
  endExpirationDate: Date;
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
  companyId: number;
  companyName: string;
  remainingUses: number;
  numberOfUses: number;
  active: boolean;
  deleted: boolean;
}

