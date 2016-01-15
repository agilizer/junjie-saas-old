define(["components/calendar/module","fullcalendar"],function(a){"use strict";a.registerDirective("fullCalendar",["CalendarEvent","$log","$timeout","$modal",function(a,b,c,d){return{restrict:"E",replace:!0,templateUrl:"build/components/calendar/directives/full-calendar.tpl.html",scope:{events:"=events"},link:function(a){function b(){e=c.fullCalendar({editable:!0,draggable:!0,selectable:!0,selectHelper:!0,unselectAuto:!1,disableResizing:!1,droppable:!0,allDaySlot:!0,aspectRatio:2,timeFormat:" ",dragOpacity:.6,dragRevertDuration:0,startParam:"startDate",endParam:"endDate",lazyFetching:!0,disableDragging:!1,monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],monthNamesShort:["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],dayNamesShort:["周日","周一","周二","周三","周四","周五","周六"],allDayText:"全天",header:{left:"title",center:"prev, next, today",right:"month, agendaWeek, agendaDay"},drop:function(a,b){var c=$(this).data("eventObject"),d=$.extend({},c);if(d.start=a,d.allDay=b,$("#calendar").fullCalendar("renderEvent",d,!0),$("#drop-remove").is(":checked")){var e=$(this).scope().$index;$("#external-events").scope().eventsExternal.splice(e,1),$(this).remove()}},select:function(){d.open({templateUrl:"build/components/calendar/views/event-create.tpl.html",controller:"eventCreateCtrl",resolve:{}})},events:function(b,c,d,e){e(a.events)},eventRender:function(a,b){""==!a.description&&b.find(".fc-event-title").append("<br/><span class='ultra-light'>"+a.description+"</span>"),""==!a.icon&&b.find(".fc-event-title").append("<i class='air air-top-right fa "+a.icon+" '></i>")}}),$(".fc-header-right, .fc-header-center",c).hide()}var c=$("#calendar"),e=null;b(),a.$watch("events",function(){c.fullCalendar("refetchEvents")},!0),a.next=function(){$(".fc-button-next",c).click()},a.prev=function(){$(".fc-button-prev",c).click()},a.today=function(){$(".fc-button-today",c).click()},a.changeView=function(a){c.fullCalendar("changeView",a)}}}}])});