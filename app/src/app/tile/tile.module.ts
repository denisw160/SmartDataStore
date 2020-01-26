import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MatGridListModule} from '@angular/material/grid-list';

import {CoreModule} from '../core/core.module';

import {TemplateSmallComponent} from './components/template-small/template-small.component';
import {TemplateMediumComponent} from './components/template-medium/template-medium.component';
import {TemplateBigComponent} from './components/template-big/template-big.component';

@NgModule({
  declarations: [
    TemplateSmallComponent,
    TemplateMediumComponent,
    TemplateBigComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    CoreModule
  ],
  exports: [
    TemplateSmallComponent,
    TemplateMediumComponent,
    TemplateBigComponent
  ]
})
export class TileModule {
}
