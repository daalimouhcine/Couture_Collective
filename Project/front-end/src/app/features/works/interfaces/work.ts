export class work {
    tailorId: number = 0;
    clientId: number = 0;
    title: String = '';
    description: String = '';
    type: String = '';
    keywords: String = '';
    deadline: Date = new Date();
    price: number = 0;
    showPrice: boolean = true;
    show_to_public: boolean = true;
    visibility_code?: String = '';
}