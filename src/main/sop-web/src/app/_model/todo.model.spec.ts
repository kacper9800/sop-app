import { TodoModel } from './todo.model';

describe('TodoModel', () => {
  it('should create an instance', () => {
    expect(new TodoModel()).toBeTruthy();
  });

  it('should accept values in constructor', () => {
    const todo = new TodoModel({
      title: 'Hello',
      complete: true
    });
    expect(todo.title).toEqual('Hello');
    expect(todo.completed).toEqual(true);
  });

});
