import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CoreModule} from '../core/core.module';

import {TemplateBigComponent} from './components/template-big/template-big.component';
import {TemplateMediumComponent} from './components/template-medium/template-medium.component';
import {TemplateSmallComponent} from './components/template-small/template-small.component';


@NgModule({
  declarations: [
    TemplateBigComponent,
    TemplateMediumComponent,
    TemplateSmallComponent
  ],
  imports: [
    CommonModule,
    CoreModule
  ],
  exports: [
    TemplateSmallComponent,
    TemplateMediumComponent,
    TemplateBigComponent
  ]
})
export class DashboardModule {
}
