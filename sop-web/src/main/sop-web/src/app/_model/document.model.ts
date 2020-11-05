export interface IDocument {
  id?: number;
  name?: string;
  description?: string;
  size?: number;
  data?: string;
  createDate?: Date;
  editDate?: Date;
  statusId?: number;
  statusName?: string;
}

export class Document implements IDocument {
  id?: number;
  name?: string;
  description?: string;
  size?: number;
  data?: string;
  createDate?: Date;
  editDate?: Date;
  statusId?: number;
  statusName?: string;
}

