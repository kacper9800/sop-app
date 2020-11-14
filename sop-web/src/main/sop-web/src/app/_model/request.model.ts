export interface IRequest {
  id: number;
  name: string;
  description: string;
  date: Date;
  active: boolean;
  removed: boolean;
}

export class Request implements IRequest {
  id: number;
  name: string;
  description: string;
  date: Date;
  active: boolean;
  removed: boolean;
}

