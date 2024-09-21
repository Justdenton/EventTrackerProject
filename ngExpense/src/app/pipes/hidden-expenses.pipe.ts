import { Pipe, PipeTransform } from '@angular/core';
import { Expense } from '../models/expense';

@Pipe({
  name: 'hiddenExpenses',
  standalone: true
})
export class HiddenExpensesPipe implements PipeTransform {

  transform(expenses: Expense[], includeHiddenExpenses: boolean): Expense[] {
    if (includeHiddenExpenses) {
      return expenses;
    }
    return expenses.filter(expense => expense.enabled);
  }

}
