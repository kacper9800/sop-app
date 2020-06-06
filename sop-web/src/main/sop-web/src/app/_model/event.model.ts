export interface IEvent {
  id: number;
  name: string;
  description: string;
  duration: number;

  location: string;
  locationId: number;

  instructor: string;
  instructorId: number;

  userId: number;
  userName: string;
  departmentId: number;
  departmentName: string;
  instituteId: number;
  instituteName: string;
  facultyId: number;
  facultyName: string;
  collegeId: number;
  collegeName: string;

  startDate: Date;
  stopDate: Date;

  allDay: boolean;
  repeat: number;

}

export class Event implements IEvent {
  id: number;
  name: string;
  description: string;
  duration: number;
  durationConverted: string;

  location: string;
  locationId: number;

  instructor: string;
  instructorName: string;
  instructorId: number;

  userId: number;
  userName: string;
  departmentId: number;
  departmentName: string;
  instituteId: number;
  instituteName: string;
  facultyId: number;
  facultyName: string;
  collegeId: number;
  collegeName: string;

  startDate: Date;
  stopDate: Date;

  allDay: boolean;
  repeat: number;
}

