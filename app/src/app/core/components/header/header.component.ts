import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {LoggingService} from '../../service/logging.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

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
