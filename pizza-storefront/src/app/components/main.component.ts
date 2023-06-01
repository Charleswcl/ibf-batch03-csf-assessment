import { Component, OnInit, inject } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PIZZA_TOPPINGS: string[] = [
  'chicken', 'seafood', 'beef', 'vegetables',
  'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  pizzaSize = SIZES[0]

  fb = inject(FormBuilder)
  router = inject(Router)

  pizzaForm!: FormGroup
  toppingsArray!: FormArray

  ngOnInit(): void {

    this.pizzaForm = this.createOrderForm()

  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  onSelectToppings(o: any) {
    this.toppingsArray = this.pizzaForm.get('toppings') as FormArray
    if (o.target.checked) {
      this.toppingsArray.push(new FormControl (this.createToppings))
    } else {
      let i: number = 0
      this.toppingsArray.controls.forEach(item => {
        if (item.value == this.createToppings) {
          this.toppingsArray.removeAt(i)
          return
        }
        i++
      })
    }
  }

  private createOrderForm() {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      size: this.fb.control<number>(0, [Validators.required]),
      base: this.fb.control<string>('', [Validators.required]),
      sauce: this.fb.control<string>('', [Validators.required]),
      toppings: this.fb.array([], [Validators.required, Validators.min(1)]),
      comments: this.fb.control<string>('')
    })
  }

  private createToppings() {
    return this.fb.group({
      value: this.fb.control('', [Validators.required])
    })
  }

}

