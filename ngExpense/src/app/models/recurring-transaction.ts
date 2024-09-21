export class RecurringTransaction {
  id: number;
  startDate: string;
  endDate: string | null;
  recurPeriod: string;
  nextRecurDate: string;
  enabled: boolean;

  constructor(
    id: number = 0,
    startDate: string = '',
    endDate: string | null = null,
    recurPeriod: string = '',
    nextRecurDate: string = '',
    enabled: boolean = true
  ) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.recurPeriod = recurPeriod;
    this.nextRecurDate = nextRecurDate;
    this.enabled = enabled;
  }
}
