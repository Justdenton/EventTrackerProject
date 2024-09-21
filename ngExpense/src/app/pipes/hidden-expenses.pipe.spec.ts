import { HiddenExpensesPipe } from './hidden-expenses.pipe';

describe('HiddenExpensesPipe', () => {
  it('create an instance', () => {
    const pipe = new HiddenExpensesPipe();
    expect(pipe).toBeTruthy();
  });
});
