export interface ILocation {
  id: number;
  name: string;
  description: string;

  address: string;
  floor: number;
  room: number;

  collegeId: number;
  collegeName: string;
}

export class Location implements ILocation {
  id: number;
  name: string;
  description: string;

  address: string;
  floor: number;
  room: number;
  capacity: number;

  collegeId: number;
  collegeName: string;
}

