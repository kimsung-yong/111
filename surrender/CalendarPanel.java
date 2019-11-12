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

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Date;

/**
 * 
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 *
 */
public class CalendarPanel extends JPanel {


	private static final long serialVersionUID = 1L;
	
	private CalendarModel model;
	private CalendarMonthView calendarMonthView;
	private CalendarDetailedView calendarDetailView;

	/**
	 * Create the panel.
	 */
	public CalendarPanel() {
		
		model = new DefaultCalendarModel();
		
		setLayout(new BorderLayout(0, 0));
		
		calendarMonthView = new CalendarMonthView(model);
		add(calendarMonthView, BorderLayout.NORTH);
		
		calendarDetailView = new CalendarDetailedView(model);
		add(calendarDetailView);

		for(Component c : calendarMonthView.getComponents()){
			c.setFocusable(false);
		}
		
		setFocusTraversalPolicy(new FocusTraversalPolicy() {
			
			@Override
			public Component getLastComponent(Container aContainer) {
				return calendarDetailView;
			}
			
			@Override
			public Component getFirstComponent(Container aContainer) {
				return calendarDetailView;
			}
			
			@Override
			public Component getDefaultComponent(Container aContainer) {
				return calendarDetailView;
			}
			
			@Override
			public Component getComponentBefore(Container aContainer,
					Component aComponent) {
				return calendarDetailView;
			}
			
			@Override
			public Component getComponentAfter(Container aContainer,
					Component aComponent) {
				return calendarDetailView;
			}
		});

		
	}
	

	public Date getSelectedDate() {
		return model.getSelectedDate();
	}

	public void setSelectedDate(Date date) {
		model.setSelectedDate(date);
	}

	public int getFirstDayOfWeek() {
		return calendarDetailView.getFirstDayOfWeek();
	}

	public void setFirstDayOfWeek(int firstDayOfWeek) {
		calendarDetailView.setFirstDayOfWeek(firstDayOfWeek);
	}

	public Color getTodayBackgroundColor() {
		return calendarDetailView.getTodayBackgroundColor();
	}

	public void setTodayBackgroundColor(Color backbround) {
		calendarDetailView.setTodayBackgroundColor(backbround);
	}

	public Color getTodayForegroundColor() {
		return calendarDetailView.getTodayForegroundColor();
	}

	public void setTodayForegroundColor(Color foreground) {
		calendarDetailView.setTodayForegroundColor(foreground);
	}

	public Color getTodayBorderColor() {
		return calendarDetailView.getTodayBorderColor();
	}

	public void setTodayBorderColor(Color borderColor) {
		calendarDetailView.setTodayBorderColor(borderColor);
	}

	public Color getNotInMonthBackground() {
		return calendarDetailView.getNotInMonthBackground();
	}

	public void setNotInMonthBackground(Color background) {
		calendarDetailView.setNotInMonthBackground(background);
	}

	public Color getNotInMonthForeground() {
		return calendarDetailView.getNotInMonthForeground();
	}

	public void setNotInMonthForeground(Color foreground) {
		calendarDetailView.setNotInMonthForeground(foreground);
	}

	public Color getNormalDayBackground() {
		return calendarDetailView.getNormalDayBackground();
	}

	public void setNormalDayBackground(Color background) {
		calendarDetailView.setNormalDayBackground(background);
	}

	public Color getNormalDayForeground() {
		return calendarDetailView.getNormalDayForeground();
	}

	public void setNormalDayForeground(Color foreground) {
		calendarDetailView.setNormalDayForeground(foreground);
	}

	public Color getSelectedDayBackground() {
		return calendarDetailView.getSelectedDayBackground();
	}
	
	public void setSelectedDayBackground(Color background) {
		calendarDetailView.setSelectedDayBackground(background);
	}

	public Color getSelectedDayForeground() {
		return calendarDetailView.getSelectedDayForeground();
	}
	
	public void setSelectedDayForeground(Color foreground) {
		calendarDetailView.setSelectedDayForeground(foreground);
	}

	public Color getWeekEndBackground() {
		return calendarDetailView.getWeekEndBackground();
	}

	public void setWeekEndBackground(Color background) {
		calendarDetailView.setWeekEndBackground(background);
	}

	public Color getWeekEndForeground() {
		return calendarDetailView.getWeekEndForeground();
	}

	public void setWeekEndForeground(Color foreground) {
		calendarDetailView.setWeekEndForeground(foreground);
	}

	

}
