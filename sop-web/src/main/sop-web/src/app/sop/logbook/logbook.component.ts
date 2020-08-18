import {Component, OnInit} from '@angular/core';
import {SelectItem} from 'primeng/api';
import {Logbook} from '../../_model/logbook.model';

@Component({
  selector: 'app-logbook',
  templateUrl: './logbook.component.html',
  styleUrls: ['./logbook.component.css']
})

export class LogbookComponent implements OnInit {

  public logbooks: Logbook[];

  // products1: Product[];
  //
  // products2: Product[];

  statuses: SelectItem[];
  products2: any;

  // clonedProducts: { [s: string]: Product; } = {};

  // constructor(private productService: ProductService, private messageService: MessageService) {
  // }

  ngOnInit() {
    //   this.productService.getProductsSmall().then(data => this.products1 = data);
    //   this.productService.getProductsSmall().then(data => this.products2 = data);
    //
    //   this.statuses = [{label: 'In Stock', value: 'INSTOCK'}, {
    //     label: 'Low Stock',
    //     value: 'LOWSTOCK'
    //   }, {label: 'Out of Stock', value: 'OUTOFSTOCK'}];
    // }
    //
    // onRowEditInit(product: Product) {
    //   this.clonedProducts[product.id] = {...product};
    // }
    //
    // onRowEditSave(product: Product) {
    //   if (product.price > 0) {
    //     delete this.clonedProducts[product.id];
    //     this.messageService.add({
    //       severity: 'success',
    //       summary: 'Success',
    //       detail: 'Product is updated'
    //     });
    //   } else {
    //     this.messageService.add({severity: 'error', summary: 'Error', detail: 'Invalid Price'});
    //   }
    // }
    //
    // onRowEditCancel(product: Product, index: number) {
    //   this.products2[index] = this.clonedProducts[product.id];
    //   delete this.products2[product.id];
    // }

  }

  onRowEditInit(product) {

  }

  onRowEditSave(product) {

  }

  onRowEditCancel(product, ri) {

  }

}

