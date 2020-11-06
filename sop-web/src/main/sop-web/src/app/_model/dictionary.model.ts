export interface IDictionary {
  id?: number;
  name?: string;
  value?: string;
  label?: string;
  active?: boolean;
  removed?: boolean;
}

export class Dictionary implements IDictionary {
  id?: number;
  name?: string;
  value?: string;
  label?: string;
  active?: boolean;
  removed?: boolean;
}

