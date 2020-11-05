import { Component, OnInit, ViewChild } from '@angular/core';
import { FullCalendar} from 'primeng/fullcalendar';
import timeGridPlugin from '@fullcalendar/timegrid';
import plLocale from '@fullcalendar/core/locales/pl';
import { PlannerService } from '../../_services/planner.service';
import { TokenStorageService } from '../../_services/auth/token-storage.service';
import { Event, IEvent } from '../../_model/event.model';

// import enLocale from '@fullcalendar/core/locales/en';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  public events: any;
  public calOptions: any;

  @ViewChild('calendar', { static: true })
  public calendarComponent: FullCalendar;

  constructor(private plannerService: PlannerService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit() {

    this.calOptions = {
      plugins: [timeGridPlugin],
      defaultView: 'timeGridDay',
      defaultDate: new Date(),
      header: {
          left: 'prev next',
          center: 'title',
          right: 'today timeGridDay timeGridWeek',
      },
      buttonIcons: {
        prev: 'left-single-arrow',
        next: 'right-single-arrow',
      },
      locales: [plLocale],
      locale: 'pl',
      buttonText: {
        // today: this.translateService.instant('calendar.today'),
        // month: this.translateService.instant('calendar.monthView'),
        // week: this.translateService.instant('calendar.weekView'),
        // day: this.translateService.instant('calendar.dayView'),
        // list: this.translateService.instant('calendar.list')
      },
      height: 700,
      nowIndicator: true,
      now: new Date(),
      firstDay: 1,

      dateClick: (e) => {

      },
      datesRender: (e) => {
        // this.loadData();
      },

      eventRender: (info) => {
        if (info.event.extendedProps.description) {

          info.el.innerHTML = info.el.innerHTML.replace('$ICON', '<em class=\'' +
            (info.event.extendedProps.confirmed === true ? 'calendarApprovedStatusIcon' :
              (info.event.extendedProps.confirmed === false ? 'calendarRejectedStatusIcon' :
                'calendarWaitingStatusIcon')) + '\'></em>');

          const title = this.generateToolTip(info.event);

          // const tooltip = new Tooltip(info.el, {
          //   title: title, // this.generateToolTip(info.event),
          //   placement: 'top',
          //   trigger: 'hover',
          //   container: 'body',
          //   html: true
          // });
        }
      },

      eventMouseEnter: (e) => {


      }
    };
  }

  private generateToolTip(event: any): string {
    const formater = 'dd-MM-yyyy';
    let content = '';
    content += 'hello';
    // content += this.translate('teamName') + event.extendedProps.teamName + '<br/>';
    // content += this.translate('activityName') + event.extendedProps.description + '<br/>';
    //
    // content += this.translate('startDate') + formatDate(event.start, formater, 'en-GB') + '<br/>';
    // if (event.end) {
    //   let endDate = new Date(event.end);
    //   endDate = new Date(endDate.getFullYear(), endDate.getMonth(), endDate.getDate() - 1);
    //   content += this.translate('stopDate') + formatDate(endDate, formater, 'en-GB') + '<br/>';
    // }

    return content;
  }

  public loadEvents() {
    const user = this.tokenStorageService.getUser();
    this.plannerService.getAllEventsForUser(user.id).subscribe(
      (res) => this.onSuccessLoadEvents(res),
      () => this.onErrorLoadEvents()
    );
  }

  private onSuccessLoadEvents(res) {
    this.convertEventsToCalendarView(res);
  }

  private onErrorLoadEvents() {

  }

  private convertEventsToCalendarView(events: Event[]) {
    events.forEach(event => {
      this.events = [...this.events, {
        id: event.id,
        title: event.name,
        description: event.description,
        start: event.startDate,
        end: event.stopDate,
        location: event.location,
        instructor: event.instructorId
      }];
    });
  }
}
