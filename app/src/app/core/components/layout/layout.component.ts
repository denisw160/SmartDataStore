import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {LoggingService} from '../../service/logging.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  constructor(private router: Router, private logger: LoggingService) {
  }

  ngOnInit() {
  }

  /**
   * Navigates to the given link.
   * @param routerLink link
   */
  navigateTo(routerLink: string) {
    this.router.navigateByUrl(routerLink).then(r => this.logger.debug('navigateTo is success: ' + r));
  }

}
