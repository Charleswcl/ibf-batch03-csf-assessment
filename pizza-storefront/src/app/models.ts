export interface pizzaOrder {

    orderId: string
    name: string
    email: string
    sauce: string
    size: number
    base: string
    toppings: string[]
    comments: string
}

export interface PendingOrders{

    orderId: string
    date: Date
    total: number
    name: string
    email: string
}