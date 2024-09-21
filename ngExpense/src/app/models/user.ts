export class User {
  id: number;
  username: string;
  email: string;
  active: boolean;

  constructor(
    id: number = 0,
    username: string = '',
    email: string = '',
    active: boolean = true
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.active = active;
  }
}
