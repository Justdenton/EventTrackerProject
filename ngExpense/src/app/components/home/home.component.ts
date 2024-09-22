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
import { SelectedUsernamePipe } from '../../pipes/selected-username.pipe';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    HiddenExpensesPipe,
    SelectedUsernamePipe
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
  selectedUserId: number | null = null;
  // totalSum: number = 0;

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
    // this.calculateSum();
  }

  onUserSelected(userId: number): void {
    console.log('Selected user ID: ', userId);
    this.selectedUserId = userId;
    this.reloadExpenses();
  }

  reloadExpenses(): void {
    if (!this.selectedUserId) {
      this.expenses = [];
      return;
    }
    this.expenseService.getExpensesByUserId(this.selectedUserId).subscribe({
      next: (expenseList) => {
        this.expenses = expenseList.length ? expenseList : [];
      },
      error: (fail) => {
        console.error('Error retrieving expense list for user', fail);
        this.expenses = [];
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
    if (this.selectedUserId) {
      expense.user.id = this.selectedUserId;
      this.expenseService.create(expense).subscribe({
        next: () => {
          this.reloadExpenses();
          this.newExpense = new Expense();
          this.newExpense.paymentMethod = new PaymentMethod();
          this.newExpense.user = { ...expense.user };
        },
        error: (err) => {
          console.error('Error adding expense', err);
        }
      });
    } else {
      alert('Please select a username before adding an expense.');
    }
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

  // calculateSum(): void {
  //   const filteredExpenses = this.expenses.filter(expense => this.includeHiddenExpenses || expense.enabled);
  //   this.totalSum = filteredExpenses.reduce((sum, expense) => sum + expense.amount, 0);
  //   // this.reloadExpenses();
  // }
  get totalSum(): number {
    return this.expenses
      .filter(expense => this.includeHiddenExpenses || expense.enabled)
      .reduce((sum, expense) => sum + (expense.amount || 0), 0);
  }

}
