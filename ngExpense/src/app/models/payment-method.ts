export class PaymentMethod {
  id: number;
  name: string;
  enabled: boolean;

  constructor(
    id: number = 0,
    name: string = '',
    enabled: boolean = true
  ) {
    this.id = id;
    this.name = name;
    this.enabled = enabled;
  }
}
