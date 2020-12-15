import {RequestStatus} from "../_enums/request-status.enum";

export interface IRequest {
  id: number;
  name: string;
  description: string;
  createDate: Date;
  nip: string;
  companyDescription: string;
  positionDescription: string;
  // status
  actualRequestStatus: string;
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
  createDate: Date;
  nip: string;
  companyDescription: string;
  positionDescription: string;
  // status
  actualRequestStatus: string;
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

