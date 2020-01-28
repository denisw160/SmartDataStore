import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {LoggingService} from '../../services/logging.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

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
