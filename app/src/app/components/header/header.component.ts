import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';

import {LoggingService} from '../../services/logging.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  /**
   * Title of the current page
   */
  title: string;

  constructor(private router: Router, private logger: LoggingService) {
  }

  ngOnInit() {
    this.logger.debug('Init event handing of current route');
    this.router.events.subscribe(e => {
      if (e instanceof NavigationEnd) {
        const event: NavigationEnd = e;
        this.router.config.filter(element => {
          const path = '/' + element.path;
          if (path === event.urlAfterRedirects && element.data) {
            this.logger.debug('Found router config for ' + path + ': title ' + element.data.title);
            this.title = element.data.title;
          }
        });
      }
    });
  }

}
