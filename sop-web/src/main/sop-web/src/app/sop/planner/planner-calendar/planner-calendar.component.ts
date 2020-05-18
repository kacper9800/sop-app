import { Component, OnInit, ViewChild } from '@angular/core';
import { FullCalendar } from 'primeng';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import plLocale from '@fullcalendar/core/locales/pl';
import { PlannerService } from '../../../_services/planner.service';
import { Event } from '../../../_model/event.model';

@Component({
  selector: 'app-planner-calendar',
  templateUrl: './planner-calendar.component.html',
  styleUrls: ['./planner-calendar.component.css']
})
export class PlannerCalendarComponent implements OnInit {

  @ViewChild('calendar', {static: true})
  public calendarComponent: FullCalendar;


  public events: any;
  public calOptions: any;
  public showNewActivityDialog: boolean;
  private blockUI: boolean;


  constructor(private plannerService: PlannerService) {
  }

  ngOnInit() {
    this.loadEvents();

    this.calOptions = {
      plugins: [dayGridPlugin, timeGridPlugin],
      defaultView: 'timeGridDay',
      defaultDate: new Date(),
      header: {
        //   left: 'prevYear prev next nextYear ',
        //   center: 'title',
        right: 'today timeGridDay timeGridWeek dayGridMonth',
      },
      buttonIcons: {
        // prev: 'left-single-arrow',
        // next: 'right-single-arrow',
        // prevYear: 'left-double-arrow',
        // nextYear: 'right-double-arrow'
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
      height: 600,
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
    events.forEach(event => {
      this.events = [...this.events, {
        id: event.id,
        // 'title': 'Team: ' + ele.teamName + '\nPerson: ' + ele.personName + ' $ICON',
        start: new Date(event.startDate),
        // "end": new Date(ele.stopDate),
        end: new Date(event.stopDate),
        allDay: true,
        description: event.description,
      }
      ];
    });
  }

}
