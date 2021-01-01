import {LogbookPost} from "./logbook-post.model";

export interface ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
  active: boolean;
  removed: boolean;
  posts: LogbookPost[];
}

export class Logbook implements ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
  active: boolean;
  removed: boolean;
  posts: LogbookPost[];
}

