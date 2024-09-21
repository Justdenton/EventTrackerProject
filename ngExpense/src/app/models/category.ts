export class Category {
  id: number;
  name: string;
  description: string;
  enabled: boolean;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    enabled: boolean = true
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.enabled = enabled;
  }
}
