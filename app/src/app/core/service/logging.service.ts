import {Injectable} from '@angular/core';

import * as moment from 'moment';

/**
 * Log-levels for this service.
 */
export enum LogLevel {
  All = 0,
  Debug = 1,
  Info = 2,
  Warn = 3,
  Error = 4,
  Fatal = 5,
  Off = 6
}

/**
 * This class provides a simple service
 * for logging system messages.
 */
@Injectable({
  providedIn: 'root'
})
export class LoggingService {

  constructor() {
  }

  private level: LogLevel = LogLevel.All;
  private logWithDate = true;

  /**
   * Format the given parameters to a string.
   * @param params parameters
   */
  private static formatParams(params: any[]): string {
    let ret: string = params.join(',');
    // Is there at least one object in the array?
    if (params.some(p => typeof p === 'object')) {
      ret = '';
      // Build comma-delimited string
      for (const item of params) {
        ret += JSON.stringify(item) + ',';
      }
    }
    return ret;
  }

  /**
   * Get the caller for this method.
   */
  private static getCaller(): string {
    try {
      try {
        // Throw an exception to getting the stacktrace
        // noinspection ExceptionCaughtLocallyJS
        throw new Error('Ignore');
      } catch (e) {
        // Regex for marking relevant lines
        // Pattern 1 *@* - Group 1-4
        // Pattern 2 at word* - Group 5-8
        // Pattern 3 at http* - Group 9-11
        const expression = /(\w+.*)@(.*):(.*):(.*)|at (\w+.\w+) \((.*):(.*):(.*)\)|at (http.*):(.*):(.*)/gm;
        const stacktrace = e.stack;
        const matches = [];

        let match = expression.exec(stacktrace);
        while (match != null) {
          if (match[1]) {
            matches.push(match[1] + ' (' + match[2] + ', ' + match[3] + ', ' + match[4] + ')');
          } else if (match[5]) {
            matches.push(match[5] + ' (' + match[6] + ', ' + match[7] + ', ' + match[8] + ')');
          } else {
            matches.push(match[9] + ' (' + match[10] + ', ' + match[11] + ')');
          }
          match = expression.exec(stacktrace);
        }

        return matches[3];
      }
    } catch (e) {
      return 'unknown';
    }
  }

  debug(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Debug, optionalParams);
  }

  info(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Info, optionalParams);
  }

  warn(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Warn, optionalParams);
  }

  error(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Error, optionalParams);
  }

  fatal(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Fatal, optionalParams);
  }

  log(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.All, optionalParams);
  }

  /**
   * Write to log.
   * @param msg message
   * @param level level
   * @param params additional parameters
   */
  private writeToLog(msg: string, level: LogLevel, params: any[]) {
    if (this.shouldLog(level)) {
      const now: moment.Moment = moment(new Date());
      let value = '';
      const caller = LoggingService.getCaller();

      // Build log string
      if (this.logWithDate) {
        value = now.format() + ' - ';
      }
      value += 'Type: ' + LogLevel[level];
      value += ' - Caller: ' + caller;
      value += ' - Message: ' + msg;
      if (params.length) {
        value += ' - Extra Info: '
          + LoggingService.formatParams(params);
      }

      // Log the value
      if (level === LogLevel.Fatal || level === LogLevel.Error) {
        console.error(value);
      } else if (level === LogLevel.Warn) {
        console.warn(value);
      } else {
        console.log(value);
      }
    }
  }

  /**
   * Should this level logged?
   * @param level level for logging
   */
  private shouldLog(level: LogLevel): boolean {
    return (level >= this.level && level !== LogLevel.Off)
      || this.level === LogLevel.All;
  }

}
