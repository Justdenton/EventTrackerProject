import { Category } from "./category";
import { PaymentMethod } from "./payment-method";
import { RecurringTransaction } from "./recurring-transaction";
import { User } from "./user";

export class Expense {
  id: number;
  amount: number;
  description: string;
  enabled: boolean;
  createTime: string | undefined;
  updateTime: string | undefined;

  user: User;
  category: Category;
  paymentMethod: PaymentMethod;
  recurringTransaction: RecurringTransaction | null;

  constructor(
    id: number = 0,
    amount: number = 0,
    description: string = '',
    enabled: boolean = true,
    createTime: string = '',
    updateTime: string = '',

    user: User = new User(),
    category: Category = new Category(),
    paymentMethod: PaymentMethod = new PaymentMethod(),
    recurringTransaction: RecurringTransaction | null = null
  ) {
    this.id = id;
    this.amount = amount;
    this.description = description;
    this.enabled = enabled;
    this.createTime = createTime;
    this.updateTime = updateTime;

    this.user = user;
    this.category = category;
    this.paymentMethod = paymentMethod;
    this.recurringTransaction = recurringTransaction;
  }
}
