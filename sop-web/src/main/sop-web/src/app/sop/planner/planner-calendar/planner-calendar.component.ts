import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import plLocale from '@fullcalendar/core/locales/pl';
import { PlannerService } from '../../../_services/planner.service';
import { Event } from '../../../_model/event.model';
import { TranslateService } from '@ngx-translate/core';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';
import resourceTimeGridPlugin from '@fullcalendar/resource-timegrid';
import { FullCalendarComponent } from '@fullcalendar/angular';


@Component({
  selector: 'app-planner-calendar',
  templateUrl: './planner-calendar.component.html',
  styleUrls: ['./planner-calendar.component.css']
})
export class PlannerCalendarComponent implements OnInit {
  public events: any;
  public calOptions: any;
  private blockUI: boolean;
  eventsModel: any;
  public showWeekendButtonLabel: string;

  constructor(private plannerService: PlannerService,
              private translateService: TranslateService) {
  }

  calendarPlugins = [dayGridPlugin, interactionPlugin, timeGridPlugin, resourceTimeGridPlugin];

  @ViewChild('fullcalendar') fullcalendar: FullCalendarComponent;
  @ViewChild('external') external: ElementRef;
  public showWeekend = false;

  ngOnInit() {
    this.loadEvents();
    this.showWeekendButtonLabel = 'Pokaż weekend';
    this.calOptions = {
      editable: true,
      defaultView: 'timeGridMonth',
      defaultDate: new Date(),
      header: {
        left: ' prev,next myCustomButton',
        center: 'title',
        right: 'today timeGridDay timeGridWeek dayGridMonth',
      },
      buttonIcons: {
        prev: 'left-single-arrow',
        next: 'right-single-arrow',
        prevYear: 'left-double-arrow',
        nextYear: 'right-double-arrow'
      },
      customButtons: {
        myCustomButton: {
          text: this.showWeekendButtonLabel,
          click: () => {
            this.showWeekend = !this.showWeekend;
            if (this.showWeekend) {
              this.showWeekendButtonLabel = 'Schowaj widok weekendu';
            } else {
              this.showWeekendButtonLabel = 'Pokaż widok weekendu';
            }
          }
        }
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


    // tslint:disable-next-line:no-unused-expression
    new Draggable(this.external.nativeElement, {
      itemSelector: '.fc-event',
      eventData(eventEl) {
        return {
          title: eventEl.innerText
        };
      }
    });
  }

  eventClick(model) {
    console.log(model);
  }

  eventDragStop(model) {
    console.log(model);
  }

  dateClick(model) {
    console.log(model);
  }

  updateHeader() {
    this.calOptions.header = {
      left: 'prev,next myCustomButton',
      center: 'title',
      right: ''
    };
  }

  updateEvents() {
    this.eventsModel = [{
      title: 'Updaten Event',
      start: this.yearMonth + '-08',
      end: this.yearMonth + '-10'
    }];
  }

  get yearMonth(): string {
    const dateObj = new Date();
    return dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);
  }

  dayRender(ev) {
    ev.el.addEventListener('dblclick', () => {
      alert('double click!');
    });
  }


  private loadEvents() {
    this.plannerService.getAllEvents().subscribe(
      (res) => this.onSuccessLoadEvents(res),
      () => this.onErrorLoadEvents()
    );
  }

  private onSuccessLoadEvents(res) {
    console.log(res);
    this.convertEventsToCalendarView(res);
    this.blockUI = false;
  }

  private onErrorLoadEvents() {
    this.blockUI = false;
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


  private convertEventsToCalendarView(events: Event[]) {
    console.log(events);
    events.forEach(event => {
      this.events = [...this.events, {
        title: event.name,
        date: '2020-05-22',
      }
      ];
    });
    console.log(this.events);
  }

}
