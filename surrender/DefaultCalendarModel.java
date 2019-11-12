/*
Copyright (C) 2015  Arnould GUIDAT

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation; version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.beaftech.surrender;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.event.EventListenerList;



/**
 * 
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 */
public class DefaultCalendarModel implements CalendarModel{
	
	private EventListenerList listeners;
	
	private Calendar calendar;
	
	public DefaultCalendarModel(){
		listeners = new EventListenerList();
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
	}

	public Date getSelectedDate() {
		return calendar.getTime();
	}

	public void setSelectedDate(Date date) {
		calendar.setTime(date);
		fireCalendarEvent();
	}
	
	public void addDay(int value) {
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		int day = currentDay + value;
		if(day<calendar.getActualMinimum(Calendar.DAY_OF_MONTH)){
			addMonth(-1, false);
			day += calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		else if(day>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
			day -= calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			addMonth(1, false);
			
		}
		calendar.set(Calendar.DAY_OF_MONTH, day);
		fireCalendarEvent();
	}
	
	private void addMonth(int value, boolean fireEvent){
		int currentMonth = calendar.get(Calendar.MONTH);
		int month = currentMonth + value;
		if(month<calendar.getActualMinimum(Calendar.MONTH)){
			addYear(-1, fireEvent);		
		}
		else if(month>calendar.getActualMaximum(Calendar.MONTH)){
			addYear(1, fireEvent);
			
		}
		calendar.roll(Calendar.MONTH, value);
		
		
		if(fireEvent){
			fireCalendarEvent();
		}
			
	}

	public void addMonth(int value) {
		addMonth(value, true);
		
	}

	private void addYear(int value, boolean fireEvent) {
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) + value);
		if(fireEvent){
			fireCalendarEvent();
		}
		
	}
	
	public void addYear(int value) {
		addYear(value, true);
		
	}

	private void fireCalendarEvent(){
		for(CalendarListener l : listeners.getListeners(CalendarListener.class)){
			l.CalendarEventOccurs(new CalendarEvent(this));
		}
	}

	public void addListener(CalendarListener l) {
		listeners.add(CalendarListener.class, l);

	}

	public void removeListener(CalendarListener l) {
		listeners.remove(CalendarListener.class, l);

	}

	
	
	

}
