import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../../services/expense.service';
import { CategoryService } from '../../services/category.service';
import { PaymentMethodService } from '../../services/payment-method.service';
import { RecurringTransactionService } from '../../services/recurring-transaction.service';
import { Expense } from '../../models/expense';
import { Category } from '../../models/category';
import { PaymentMethod } from '../../models/payment-method';
import { RecurringTransaction } from '../../models/recurring-transaction';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { HiddenExpensesPipe } from '../../pipes/hidden-expenses.pipe';
import { User } from '../../models/user';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    HiddenExpensesPipe
],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  expenses: Expense[] = [];
  categories: Category[] = [];
  paymentMethods: PaymentMethod[] = [];
  newExpense: Expense;
  includeHiddenExpenses: boolean = false;
  users: User[] = [];

  constructor(
    private expenseService: ExpenseService,
    private categoryService: CategoryService,
    private paymentMethodService: PaymentMethodService,
    // private recurringTransactionService: RecurringTransactionService
    private userService: UserService
  ) {
    this.newExpense = new Expense();
    this.newExpense.paymentMethod = new PaymentMethod();
    this.newExpense.user = new User();
  }

  ngOnInit(): void {
    this.reloadExpenses();
    this.loadCategories();
    this.loadPaymentMethods();
    this.loadUsers();
  }

  reloadExpenses(): void {
    this.expenseService.index().subscribe({
      next: (expenseList) => {
        this.expenses = expenseList.map(expense => {
          if (!expense.paymentMethod) {
            expense.paymentMethod = new PaymentMethod();
          }
          return expense;
        });
      },
      error: (fail) => {
        console.error('Error retrieving expense list', fail);
      }
    });
  }

  loadCategories(): void {
    this.categoryService.index().subscribe({
      next: (categoryList) => {
        this.categories = categoryList;
      },
      error: (fail) => {
        console.error('Error retrieving category list', fail);
      }
    });
  }

  loadPaymentMethods(): void {
    this.paymentMethodService.index().subscribe({
      next: (paymentMethodList) => {
        this.paymentMethods = paymentMethodList;
      },
      error: (fail) => {
        console.error('Error retrieving payment methods', fail);
      }
    });
  }

  loadUsers(): void {
    this.userService.index().subscribe({
      next: (userList) => {
        this.users = userList;
      },
      error: (fail) => {
        console.error('Error retrieving payment methods', fail);
      }
    });
  }

  addExpense(expense: Expense): void {
    this.expenseService.create(expense).subscribe({
      next: () => {
        this.reloadExpenses();
        this.newExpense = new Expense();
        this.newExpense.paymentMethod = new PaymentMethod();
        this.newExpense.user = new User();
      },
      error: (err) => {
        console.error('Error adding expense', err);
      }
    });
  }

  updateExpense(expense: Expense): void {
    this.expenseService.update(expense).subscribe({
      next: () => {
        this.reloadExpenses();
      },
      error: (err) => {
        console.error('Error updating expense', err);
      }
    });
  }

  deleteExpense(expenseId: number): void {
    this.expenseService.destroy(expenseId).subscribe({
      next: () => {
        this.reloadExpenses();
      },
      error: (err) => {
        console.error('Error deleting expense', err);
      }
    });
  }

  toggleHiddenExpenses(): void {
    this.includeHiddenExpenses = !this.includeHiddenExpenses;
  }
}
