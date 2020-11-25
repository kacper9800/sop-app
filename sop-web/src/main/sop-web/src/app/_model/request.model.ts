export interface IRequest {
  id: number;
  name: string;
  description: string;
  createDate: Date;
  nip: string;
  companyDescription: string;
  positionDescription: string;
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
  active: boolean;
  removed: boolean;
}

