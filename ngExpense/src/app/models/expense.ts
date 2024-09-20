export class Expense {
  id: number;
  amount: number;
  description: string;
  enabled: boolean;
  createTime: string | undefined;
  updateTime: string | undefined;

  // user:
  // category:
  // paymentMethod:
  // recurringTransaction:

  constructor(
    id: number = 0,
    amount: number = 0,
    description: string = '',
    enabled: boolean = true,
    createTime: string = '',
    updateTime: string = ''
  ) {
    this.id = id;
    this.amount = amount;
    this.description = description;
    this.enabled = enabled;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
