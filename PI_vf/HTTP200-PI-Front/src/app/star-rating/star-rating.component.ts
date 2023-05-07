import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-star-rating',
  templateUrl: './star-rating.component.html',
  styleUrls: ['./star-rating.component.css']
})
export class StarRatingComponent {
  rating!: number;
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number = 0;
  constructor() { }
  ngOnInit() {
    this.selectedValue = this.rating;
  }
  countStar(star: number) {
    this.selectedValue = star;
    console.log('Value of star', star);
  }
}
