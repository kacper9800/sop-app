export interface ILogbookPost {
  id: number;
  name: string;
  tasks: string;
  date: Date;
}

export class LogbookPost implements ILogbookPost {
  id: number;
  name: string;
  tasks: string;
  date: Date;
}

