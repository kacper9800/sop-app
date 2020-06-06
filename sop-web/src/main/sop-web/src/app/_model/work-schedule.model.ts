export interface IWorkSchedule {
  id: number;
  name: string;
  description: string;

  startDate: Date;
  stopDate: Date;

  duration: number;

  breaks: number;

  startHour: Date;
  stopHour: Date;

  eventsId: number[];
  usersId: number[];
  locationsId: number[];

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


  allDay: boolean;
  repeat: number;

}


export class WorkSchedule implements IWorkSchedule {
  id: number;
  name: string;
  description: string;

  startDate: Date;
  stopDate: Date;

  duration: number;

  breaks: number;

  startHour: Date;
  stopHour: Date;

  eventsId: number[];
  usersId: number[];
  locationsId: number[];

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


  allDay: boolean;
  repeat: number;

}
