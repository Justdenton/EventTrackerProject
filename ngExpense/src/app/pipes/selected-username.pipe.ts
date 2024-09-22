import { Pipe, PipeTransform } from '@angular/core';
import { Expense } from '../models/expense';

@Pipe({
  name: 'selectedUsername',
  standalone: true
})
export class SelectedUsernamePipe implements PipeTransform {
  transform(expenses: Expense[], userId: number): Expense[] {
    if (!userId) {
      return expenses;
    }
    return expenses.filter(expense => expense.user.id === userId);
  }
}
