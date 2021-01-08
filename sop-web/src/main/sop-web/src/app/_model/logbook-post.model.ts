export interface ILogbookPost {
  id: number;
  description: string;
  date: Date;
  amountOfHours: number;
  isEditable: boolean;
  logbookId: number;
}

export class LogbookPost implements ILogbookPost {
  id: number;
  description: string;
  date: Date;
  amountOfHours: number;
  isEditable: boolean;
  logbookId: number;
}

