import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllIzvestajiComponent } from './all-izvestaji.component';

describe('AllIzvestajiComponent', () => {
  let component: AllIzvestajiComponent;
  let fixture: ComponentFixture<AllIzvestajiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllIzvestajiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllIzvestajiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
