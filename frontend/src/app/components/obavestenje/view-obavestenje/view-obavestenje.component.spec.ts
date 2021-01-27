import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewObavestenjeComponent } from './view-obavestenje.component';

describe('ViewObavestenjeComponent', () => {
  let component: ViewObavestenjeComponent;
  let fixture: ComponentFixture<ViewObavestenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewObavestenjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewObavestenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
