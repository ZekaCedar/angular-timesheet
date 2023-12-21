import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, map } from 'rxjs';
import { Timesheet } from '../common/timesheet';

@Injectable({
  providedIn: 'root',
})
export class TimesheetService {
  
  private baseUrl = 'http://localhost:8080/api/1.0/timesheet';

  private createUrl = this.baseUrl + '/create';
  
  // private timesheetCreateSource = new BehaviorSubject<Timesheet | null>(null);
  // private timesheetUpdateSource = new BehaviorSubject<Timesheet | null>(null);
  // private timesheetDeleteSource = new Subject<number>(); 

  // timesheetCreated = this.timesheetCreateSource.asObservable();

  constructor(private httpClient: HttpClient) {}

  createTimesheet(timesheet: Timesheet): Observable<any> {
    return this.httpClient.post<Timesheet>(this.createUrl, timesheet);
  }

  // notifyTimesheetCreation(timesheet: Timesheet) {
  //   console.log("data notified after create: " + timesheet);
  //   this.timesheetCreateSource.next(timesheet);
  // }

  updateTimesheet(theId: number, timesheet: Timesheet): Observable<any> {
    const editUrl = this.baseUrl + `/edit/${theId}`;
    return this.httpClient.post<Timesheet>(editUrl, timesheet);
  }

  deleteTimesheet(theId: number): Observable<any> {
    const deleteUrl = `${this.baseUrl}/delete/${theId}`;
    return this.httpClient.delete(deleteUrl);
  }

  getTimesheetById(theId: number): Observable<Timesheet> {
    const getByIdUrl = `${this.baseUrl}/${theId}`;
    return this.httpClient.get<Timesheet>(getByIdUrl);
  }

  getTimesheetList(
    theTaskName: string | undefined | null
  ): Observable<Timesheet[]> {
    // @TODO: need to build url based on taskName
    const searchUrl = `${this.baseUrl}/search/findByTaskNameContaining?taskName=${theTaskName}`;

    return this.getTimesheet(searchUrl);
  }

  searchTimesheet(theKeyword: string): Observable<Timesheet[]> {
    // @TODO: need to build url based on taskName
    const searchUrl = `${this.baseUrl}/search/findByTaskNameContaining?taskName=${theKeyword}`;

    return this.getTimesheet(searchUrl);
  }

  /*  refactoring section */
  private getTimesheet(searchUrl: string): Observable<Timesheet[]> {
    return this.httpClient
      .get<GetResponseTimesheet>(searchUrl)
      .pipe(map((response) => response._embedded.timesheet));
  }
}

interface GetResponseTimesheet {
  _embedded: {
    timesheet: Timesheet[];
  };
}
