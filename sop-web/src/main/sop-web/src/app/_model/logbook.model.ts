import {LogbookPost} from './logbook-post.model';

export interface ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
  internId: number;
  internName: string;
  instituteId: number;
  instituteName: string;
  collegeId: number;
  collegeName: string;
  active: boolean;
  removed: boolean;
  posts: LogbookPost[];
}

export class Logbook implements ILogbook {
  id: number;
  name: string;
  description: string;
  date: Date;
  internId: number;
  internName: string;
  instituteId: number;
  instituteName: string;
  collegeId: number;
  collegeName: string;
  active: boolean;
  removed: boolean;
  posts: LogbookPost[];
}

