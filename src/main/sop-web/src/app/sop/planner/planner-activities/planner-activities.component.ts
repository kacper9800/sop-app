import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-planner-activities',
  templateUrl: './planner-activities.component.html',
  styleUrls: ['./planner-activities.component.css']
})
export class PlannerActivitiesComponent implements OnInit {
  options: any;
  // eventsModel: any;
  // @ViewChild('fullcalendar') fullcalendar: FullCalendarComponent;
  // @ViewChild('external') external: ElementRef;
  //
  ngOnInit() {
    //   // Don't use FullcalendarOption interface
    //   this.options = {
    //     editable: true,
    //     customButtons: {
    //       myCustomButton: {
    //         text: 'custom!',
    //         click: function () {
    //           alert('clicked the custom button!');
    //         }
  }

  //     },
  //     theme: 'standart', // default view, may be bootstrap
  //     header: {
  //       left: 'prev,next today myCustomButton',
  //       center: 'title',
  //       right: 'dayGridMonth,timeGridWeek,timeGridDay'
  //     },
  //     columnHeaderHtml: () => {
  //       return '<b>Friday!</b>';
  //     },
  //     locales: [esLocale, frLocale],
  //     locale: 'fr',
  //     // add other plugins
  //     plugins: [dayGridPlugin, interactionPlugin, timeGridPlugin, resourceTimeGridPlugin]
  //   };
  //   new Draggable(this.external.nativeElement, {
  //     itemSelector: '.fc-event',
  //     eventData: function (eventEl) {
  //       return {
  //         title: eventEl.innerText
  //       };
  //     }
  //   });
  // }
  //
  // eventClick(model) {
  //   console.log(model);
  // }
  //
  // eventDragStop(model) {
  //   console.log(model);
  // }
  //
  // dateClick(model) {
  //   console.log(model);
  // }
  //
  // updateHeader() {
  //   this.options.header = {
  //     left: 'prev,next myCustomButton',
  //     center: 'title',
  //     right: ''
  //   };
  // }
  //
  // updateEvents() {
  //   this.eventsModel = [{
  //     title: 'Updaten Event',
  //     start: this.yearMonth + '-08',
  //     end: this.yearMonth + '-10'
  //   }];
  // }
  //
  // get yearMonth(): string {
  //   const dateObj = new Date();
  //   return dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);
  // }
  //
  // dayRender(ev) {
  //   ev.el.addEventListener('dblclick', () => {
  //     alert('double click!');
  //   });
  // }
}
