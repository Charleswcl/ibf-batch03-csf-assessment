import { Component, OnInit, inject } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { PendingOrders } from '../models';
import { PizzaService } from '../pizza.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit{

  email = ''
  orders$!: Observable<PendingOrders[]>
  pendingOrders: PendingOrders [] =[]
  
  pizzaSvc = inject(PizzaService)
  activatedRoute = inject(ActivatedRoute)

  ngOnInit(): void {

  this.email = this.activatedRoute.snapshot.params['email']
  this.orders$ = this.pizzaSvc.getOrders(this.email)
      
  }
}
