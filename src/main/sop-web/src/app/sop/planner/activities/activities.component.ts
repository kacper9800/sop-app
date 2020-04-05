import { Component, OnInit, ViewChild } from '@angular/core';
import { FullCalendar } from 'primeng';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import plLocale from '@fullcalendar/core/locales/pl';
// import enLocale from '@fullcalendar/core/locales/en';
@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {
  events: any;
  calOptions: any;
  @ViewChild('calendar', {static: true}) calendarComponent: FullCalendar;

  constructor() {
  }

  ngOnInit() {

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
}
