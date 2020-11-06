export interface IActivationKey {
  id?: number;
  value?: string;
  startExpirationDate?: Date;
  endExpirationDate?: Date;
  createdById?: number;
  createdByName?: string;
  directionId?: number;
  directionName?: string;
  collegeId?: number;
  collegeName?: string;
  facultyId?: number;
  facultyName?: string;
  instituteId?: number;
  instituteName?: string;
  departmentId?: number;
  departmentName?: string;
  companyId?: number;
  companyName?: string;
  numberOfUses?: number;
  active?: boolean;
  deleted?: boolean;
}

export class ActivationKey implements IActivationKey {
  id?: number;
  value?: string;
  startExpirationDate?: Date;
  endExpirationDate?: Date;
  createdById?: number;
  createdByName?: string;
  directionId?: number;
  directionName?: string;
  collegeId?: number;
  collegeName?: string;
  facultyId?: number;
  facultyName?: string;
  instituteId?: number;
  instituteName?: string;
  departmentId?: number;
  departmentName?: string;
  companyId?: number;
  companyName?: string;
  numberOfUses?: number;
  active?: boolean;
  deleted?: boolean;
}

