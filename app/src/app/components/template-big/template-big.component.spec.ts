import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TemplateBigComponent} from './template-big.component';

describe('TemplateBigComponent', () => {
  let component: TemplateBigComponent;
  let fixture: ComponentFixture<TemplateBigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TemplateBigComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemplateBigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
