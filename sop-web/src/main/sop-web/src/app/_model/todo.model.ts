export class TodoModel {
  id: number;
  title = '';
  completed = false;

  constructor(values = {}) {
    Object.assign(this, values);
  }


}
