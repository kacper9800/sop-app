export interface ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
}

export class Logbook implements ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
}

