import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewZahtevComponent } from './new-zahtev.component';

describe('NewZahtevComponent', () => {
  let component: NewZahtevComponent;
  let fixture: ComponentFixture<NewZahtevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewZahtevComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewZahtevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
