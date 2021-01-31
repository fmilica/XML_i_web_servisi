import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewObavestenjeComponent } from './new-obavestenje.component';

describe('NewObavestenjeComponent', () => {
  let component: NewObavestenjeComponent;
  let fixture: ComponentFixture<NewObavestenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewObavestenjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewObavestenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
