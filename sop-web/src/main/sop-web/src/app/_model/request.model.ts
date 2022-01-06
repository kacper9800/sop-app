import {RequestStatus} from '../_enums/request-status.enum';

export interface IRequest {
  id: number;
  name: string;
  description: string;
  requestTypeName: string;
  collegeId: number;
  instituteId: number;
  instituteName: string;
  internId: number;
  internName: string;

  // Intern data
  position: string;
  positionDescription: string;
  amountOfHours: number;
  responsibilities: string;

  // Company data
  nip: string;
  companyName: string;
  companyDescription: string;
  departmentName: string;

  // Agreements
  infoAgreement: boolean;
  processingAgreement: boolean;

  // Receiver
  moderatorId: number;
  moderatorName: string;

  adminId: number;
  adminName: string;

  practiceSuperviserName: string;
  practiceSuperviserLastName: string;
  practiceSuperviserPhone: string;
  practiceSuperviserEmail: string;

  // Status
  actualRequestStatus: RequestStatus;
  sendRequestDate: Date;
  sendRequestStatus: RequestStatus;

  moderatorDecisionDate: Date;
  moderatorDecisionStatus: RequestStatus;
  moderatorDecisionFeedback: string;

  adminDecisionDate: Date;
  adminDecisionStatus: RequestStatus;
  adminDecisionFeedback: string;
  active: boolean;
  removed: boolean;
}

export class Request implements IRequest {
  id: number;
  name: string;
  description: string;
  requestTypeName: string;
  collegeId: number;
  instituteId: number;
  instituteName: string;
  internId: number;
  internName: string;

  // Intern data
  position: string;
  positionDescription: string;
  amountOfHours: number;
  responsibilities: string;

  // Company data
  nip: string;
  companyName: string;
  companyDescription: string;
  departmentName: string;

  // Agreements
  infoAgreement: boolean;
  processingAgreement: boolean;

  // Receiver
  moderatorId: number;
  moderatorName: string;

  adminId: number;
  adminName: string;

  practiceSuperviserName: string;
  practiceSuperviserLastName: string;
  practiceSuperviserPhone: string;
  practiceSuperviserEmail: string;


  // Status
  actualRequestStatus: RequestStatus;
  sendRequestDate: Date;
  sendRequestStatus: RequestStatus;

  moderatorDecisionDate: Date;
  moderatorDecisionStatus: RequestStatus;
  moderatorDecisionFeedback: string;

  adminDecisionDate: Date;
  adminDecisionStatus: RequestStatus;
  adminDecisionFeedback: string;
  active: boolean;
  removed: boolean;
}

