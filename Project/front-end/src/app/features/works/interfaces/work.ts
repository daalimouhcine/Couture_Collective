import { CurrentUser } from '../../users/interfaces/users';

export class Work {
  id?: number = 0;
  tailorId: number = 0;
  clientId: number = 0;
  title: String = '';
  description: String = '';
  type: String = '';
  keywords: String = '';
  deadline: Date = new Date();
  price: number = 0;
  show_price: boolean = true;
  show_to_public: boolean = true;
  visibility_code?: String = '';
  is_completed?: boolean = false;
  is_paid?: boolean = false;
  createdAt: Date = new Date();
  client?: CurrentUser = new CurrentUser();
}
