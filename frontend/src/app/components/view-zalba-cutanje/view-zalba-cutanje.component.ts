import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
// import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje.service';
// import { ZalbaCutanjeXonomyService } from '../xonomy/zalba-cutanje-xonomy.service';

declare const Xonomy: any;

@Component({
  selector: 'app-view-zalba-cutanje',
  templateUrl: './view-zalba-cutanje.component.html',
  styleUrls: ['./view-zalba-cutanje.component.sass'],
})
export class ViewZalbaCutanjeComponent implements OnInit {
  constructor(
    // private xonomyService: ZalbaCutanjeXonomyService,
    // private zalbacutanjeService: ZalbaCutanjeService,
    private route: ActivatedRoute
  ) {}

  @ViewChild('zalbaView', { static: false }) zalbaView: any;

  id: string = '';
  zalba: any;

  ngOnInit(): void {}
}
