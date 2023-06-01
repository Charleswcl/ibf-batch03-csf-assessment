import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { PendingOrders, pizzaOrder } from "./models";
import { Observable, firstValueFrom } from "rxjs";

@Injectable()
export class PizzaService {

  

  http = inject(HttpClient)

  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(order: pizzaOrder): Promise<pizzaOrder> {

    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')

    const orderDetails = {
      name: order.name,
      email: order.email,
      sauce: order.sauce,
      size: order.size,
      thickCrust: order.base == 'thick' ? true : false,
      toppings: order.toppings,
      comments: order.comments
    }
    return firstValueFrom(
      this.http.post<pizzaOrder>(`/api/order`, orderDetails, { headers: headers })
    )
  }

  // TODO: Task 5
  // You may add any parameters and return any type from getOrders() method
  // Do not change the method name
  getOrders(email: string): Observable<PendingOrders[]> {
    
     return this.http.get<PendingOrders[]>(`/api/orders/${email}`)
    
  }

  // TODO: Task 7
  // You may add any parameters and return any type from delivered() method
  // Do not change the method name
  delivered() {
  }


}