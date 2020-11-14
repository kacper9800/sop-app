export interface ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
  active: boolean;
  removed: boolean;
}

export class Logbook implements ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
  active: boolean;
  removed: boolean;
}

