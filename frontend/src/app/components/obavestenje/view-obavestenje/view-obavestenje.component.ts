import { Component, OnInit } from '@angular/core';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';

@Component({
  selector: 'app-view-obavestenje',
  templateUrl: './view-obavestenje.component.html',
  styleUrls: ['./view-obavestenje.component.sass'],
})
export class ViewObavestenjeComponent implements OnInit {
  constructor(public obavestenjeService: ObavestenjeService) {}

  ngOnInit(): void {}

  getObavestenje(id: string) {
    this.obavestenjeService.getObavestenje(id).subscribe((response) => {
      console.log(response);
    });
  }
}
