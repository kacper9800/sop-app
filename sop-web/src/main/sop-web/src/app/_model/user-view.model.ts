export interface IUserView {
  id?: number;
  name?: string;
  lastName?: string;
  description?: string;
  duration?: number;

  location?: string;
  locationId?: number;

  instructor?: string;
  instructorId?: number;

  userId?: number;
  userName?: string;
  departmentId?: number;
  departmentName?: string;
  instituteId?: number;
  instituteName?: string;
  facultyId?: number;
  facultyName?: string;
  collegeId?: number;
  collegeName?: string;

  startDate?: Date;
  stopDate?: Date;

  allDay?: boolean;
  repeat?: number;

}

export class UserView implements IUserView {
  id?: number;
  name?: string;
  lastName?: string;
  description?: string;
  duration?: number;

  location?: string;
  locationId?: number;

  instructor?: string;
  instructorId?: number;

  userId?: number;
  userName?: string;
  departmentId?: number;
  departmentName?: string;
  instituteId?: number;
  instituteName?: string;
  facultyId?: number;
  facultyName?: string;
  collegeId?: number;
  collegeName?: string;

  startDate?: Date;
  stopDate?: Date;

  allDay?: boolean;
  repeat?: number;
}

