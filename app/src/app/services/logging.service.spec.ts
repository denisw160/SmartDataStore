import {TestBed} from '@angular/core/testing';

import {LoggingService} from './logging.service';

describe('LoggingService', () => {

  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoggingService = TestBed.get(LoggingService);
    expect(service).toBeTruthy();
  });

  it('should log debug', () => {
    const service: LoggingService = TestBed.get(LoggingService);
    service.debug('Debug');
    expect(true).toBeTruthy();
  });

  it('should log info', () => {
    const service: LoggingService = TestBed.get(LoggingService);
    service.info('Info');
    expect(true).toBeTruthy();
  });

  it('should log warn', () => {
    const service: LoggingService = TestBed.get(LoggingService);
    service.warn('Warn');
    expect(true).toBeTruthy();
  });

  it('should log error', () => {
    const service: LoggingService = TestBed.get(LoggingService);
    service.error('Error');
    expect(true).toBeTruthy();
  });

  it('should log fatal', () => {
    const service: LoggingService = TestBed.get(LoggingService);
    service.fatal('Fatal');
    expect(true).toBeTruthy();
  });

  it('should log all', () => {
    const service: LoggingService = TestBed.get(LoggingService);
    service.log('All');
    expect(true).toBeTruthy();
  });

});
