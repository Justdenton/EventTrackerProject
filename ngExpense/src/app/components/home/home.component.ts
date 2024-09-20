import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../../services/expense.service';
import { Expense } from '../../models/expense';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  expenses: Expense[] = [];

  constructor(
    private expenseService: ExpenseService
  ){}

  ngOnInit(): void {
    this.reloadExpenses();
  }

  reloadExpenses(): void {
    this.expenseService.index().subscribe({
      next: (expenseList) => {
        this.expenses = expenseList;
      },
      error: (fail) => {
        console.error('error retrieving expense list');
        console.error(fail);
      }
    });
  }


}
