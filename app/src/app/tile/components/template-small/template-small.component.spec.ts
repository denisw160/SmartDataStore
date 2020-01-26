import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TemplateSmallComponent} from './template-small.component';

describe('TemplateSmallComponent', () => {
  let component: TemplateSmallComponent;
  let fixture: ComponentFixture<TemplateSmallComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TemplateSmallComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemplateSmallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
