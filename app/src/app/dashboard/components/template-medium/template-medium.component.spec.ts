import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TemplateMediumComponent} from './template-medium.component';

describe('TemplateMediumComponent', () => {
  let component: TemplateMediumComponent;
  let fixture: ComponentFixture<TemplateMediumComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TemplateMediumComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemplateMediumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
