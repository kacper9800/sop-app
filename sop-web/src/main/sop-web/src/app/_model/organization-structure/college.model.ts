export interface ICollege {
  id: number;
  name: string;
  active: boolean;
  removed: boolean;
}

export class College implements ICollege {
  id: number;
  name: string;
  active: boolean;
  removed: boolean;
}

