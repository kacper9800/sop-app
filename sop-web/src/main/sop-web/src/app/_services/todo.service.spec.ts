import { TestBed } from '@angular/core/testing';

import { TodoService } from './todo.service';
import { inject } from '@angular/core';

describe('TodoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TodoService = TestBed.get(TodoService);
    expect(service).toBeTruthy();
  });
});
