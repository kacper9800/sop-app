export interface IEvent {
  id: number;
  name: string;
  description: string;
  location: string;
  locationId: number;
  hostBy: string;
  hostById: number;
  startDate: Date;
  stopDate: Date;
  allDay: boolean;
  repeat: number;
}

export class Event implements IEvent {
  id: number;
  name: string;
  description: string;
  location: string;
  locationId: number;
  hostBy: string;
  hostById: number;
  startDate: Date;
  stopDate: Date;
  allDay: boolean;
  repeat: number;
}

