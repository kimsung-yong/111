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

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * A panel that displays a calendar as a 6 rows x 7 columns grid.
 * 
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 */
public class CalendarDetailedView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * The view's model
	 */
	private CalendarModel model;
	/**
	 * The event listener for calendar events
	 */
	private final CalendarListener calendarListener;
	/**
	 * The index of the label corresponding to the current selected day
	 */
	private int currentIdx;
	/**
	 * An integer representing the first day of week of this calendar view, ie
	 * the day that will be displayed in the first column
	 */
	private int firstDayOfWeek = Calendar.MONDAY;
	/**
	 * An array containing all labels that represent a day of the month.
	 */
	private JLabel[] days;
	/**
	 * An array containing all labels that represent a day of the week.
	 */
	private JLabel[] headers;
	/**
	 * The number of rows of the calendar
	 */
	private int rows = 6;
	/**
	 * The number of columns of the calendar
	 */
	private int columns = 7;
	
	/**
	 * The background color of today (Note: if today is selected, then the background selected color will be applied)
	 */
	private Color col_bkg_today = new Color(240, 255, 255);
	/**
	 * The foreground color of today (Note: if today is selected, then the foreground selected color will be applied)
	 */
	private Color col_frg_today = Color.BLACK;
	/**
	 * The border color of today
	 */
	private Color col_brd_today = new Color(150, 230, 255);
	/**
	 * The background color of days that are not in the current month
	 */
	private Color col_bkg_notInMonth = Color.WHITE;
	/**
	 * The foreground color of days that are not in the current month
	 */
	private Color col_frg_notInMonth = Color.GRAY;
	/**
	 * The background color of unselected days of the same month as current
	 */
	private Color col_bkg_normalDay = Color.WHITE;
	/**
	 * The foreground color of unselected days of the same month as current
	 */
	private Color col_frg_normalDay = Color.BLACK;
	/**
	 * The background color of the selected day
	 */
	private Color col_bkg_selectedDay = new Color(0, 60, 255);
	/**
	 * The foreground color of the selected day 
	 */
	private Color col_frg_selectedDay = Color.WHITE;
	/**
	 * The background color of a week end day (Note: if today is selected, then the background selected color will be applied)
	 */
	private Color col_bkg_weekEnd = Color.LIGHT_GRAY;
	/**
	 * The foreground color of a week end day (Note: if today is selected, then the foreground selected color will be applied)
	 */
	private Color col_frg_weekEnd = Color.BLACK;

	/**
	 * A JLabel that can keep is position in memory
	 *
	 */
	private class CustomLabel extends JLabel {

		private static final long serialVersionUID = CalendarDetailedView.serialVersionUID;

		private final int position;

		private CustomLabel(int idx) {
			position = idx;
		}

		public int getPosition() {
			return position;
		}

	}

	public CalendarDetailedView() {
		this(new DefaultCalendarModel());
	}

	/**
	 * Create the panel.
	 */
	public CalendarDetailedView(CalendarModel calendarModel) {

		model = calendarModel;
		calendarListener = new CalendarListener() {

			public void CalendarEventOccurs(CalendarEvent ce) {
				applyDate();

			}
		};
		model.addListener(calendarListener);

		setLayout(new GridLayout(rows + 1, columns, 0, 0));

		days = new JLabel[rows * columns];
		JLabel label;

		Calendar c = GregorianCalendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE");
		headers = new JLabel[7];
		for (int i = 0; i < columns; i++) {
			label = new JLabel();
			label.setOpaque(true);
			label.setBackground(Color.GRAY);
			c.set(Calendar.DAY_OF_WEEK, i);
			label.setText(sdf.format(c.getTime()));
			label.setHorizontalAlignment(JLabel.CENTER);
			add(label);
			headers[i] = label;
		}

		for (int i = 0; i < rows * columns; i++) {
			label = new CustomLabel(i);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setOpaque(true);

			label.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					CustomLabel cl = (CustomLabel) e.getSource();
					changeIdx(cl.getPosition());

				}
			});
			add(label);
			days[i] = label;
		}

		addKeyListener(new KeyAdapter() {


			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					if (currentIdx > 0) {
						changeIdx(currentIdx - 1);
					}
					break;

				case KeyEvent.VK_RIGHT:
					if (currentIdx < (rows * columns) - 1) {
						changeIdx(currentIdx + 1);
					}
					break;

				case KeyEvent.VK_UP:
					// if(currentIdx/7>=0){
					changeIdx(currentIdx - 7);
					// }
					break;

				case KeyEvent.VK_DOWN:
					// if(currentIdx%7<rows){
					changeIdx(currentIdx + 7);
					// }
					break;

				}

			}
		});

		setFocusable(true);
		applyDate();

	}

	public void setDate(Date date) {
		model.setSelectedDate(date);
	}

	private void changeIdx(int idx) {

		model.addDay(idx - currentIdx);

	}

	private void applyDate() {
		JLabel label;
		int max;
		int maxCurrent;
		int day;
		int month;
		int offset;
		Calendar today;
		Calendar selected;
		Calendar calendar;

		calendar = GregorianCalendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("EEE");

		for (int i = 0; i < columns; i++) {
			label = headers[i];
			calendar.set(Calendar.DAY_OF_WEEK, i + firstDayOfWeek % 7);
			label.setText(sdf.format(calendar.getTime()));

		}

		Date currentDate = model.getSelectedDate();
		today = GregorianCalendar.getInstance();
		selected = GregorianCalendar.getInstance();
		selected.setTime(currentDate);
		calendar.setTime(currentDate);
		offset = 7 - firstDayOfWeek;
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		offset += calendar.get(Calendar.DAY_OF_WEEK);
		offset %= 7;
		if (offset == 0) {
			offset = 7;
		}
		month = calendar.get(Calendar.MONTH);
		maxCurrent = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (month == 0) {
			calendar.roll(Calendar.YEAR, -1);
		}
		calendar.roll(Calendar.MONTH, -1);
		max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		day = max - (offset - 1);

		currentIdx = -1;
		for (int i = 0; i < rows * columns; i++) {

			if (day > max) {
				day = 1;
				max = maxCurrent;
				if (calendar.get(Calendar.MONTH) == calendar
						.getActualMaximum(Calendar.MONTH)) {
					calendar.roll(Calendar.YEAR, 1);
				}
				calendar.roll(Calendar.MONTH, 1);
				if (calendar.get(Calendar.MONTH) == month) {
					calendar.setTime(currentDate);
				}

			}

			label = days[i];
			label.setText(Integer.toString(day));

			if (calendar.get(Calendar.MONTH) == month) {
				label.setBackground(col_bkg_normalDay);
				label.setForeground(col_frg_normalDay);

				if (day == calendar.get(Calendar.DAY_OF_MONTH)) {
					label.setBackground(col_bkg_selectedDay);
					label.setForeground(col_frg_selectedDay);
					currentIdx = i;
				}

			} else {
				label.setBackground(col_bkg_notInMonth);
				label.setForeground(col_frg_notInMonth);
			}

			if (i != currentIdx
					&& (i % 7 == (8 - firstDayOfWeek) % 7 || i % 7 == (8 - firstDayOfWeek - 1) % 7)) {
				label.setBackground(col_bkg_weekEnd);
				label.setForeground(col_frg_weekEnd);
			}

			if (calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH)
					&& day == today.get(Calendar.DAY_OF_MONTH)) {
				label.setBorder(new LineBorder(col_brd_today));

				if (selected.get(Calendar.MONTH) != today.get(Calendar.MONTH)
						|| selected.get(Calendar.DAY_OF_MONTH) != today
								.get(Calendar.DAY_OF_MONTH)) {
					label.setBackground(col_bkg_today);
					label.setForeground(col_frg_today);
				}

			} else {
				label.setBorder(null);
			}

			day++;
		}
		calendar.setTime(currentDate);
	}
	
	public CalendarModel getModel() {
		return model;
	}

	public void setModel(CalendarModel model) {
		if(this.model != null){
			this.model.removeListener(calendarListener);
		}
		this.model = model;
		this.model.addListener(calendarListener);
		applyDate();
	}

	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	public void setFirstDayOfWeek(int firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
		applyDate();
	}

	public Color getTodayBackgroundColor() {
		return col_bkg_today;
	}

	public void setTodayBackgroundColor(Color backbround) {
		this.col_bkg_today = backbround;
	}

	public Color getTodayForegroundColor() {
		return col_frg_today;
	}

	public void setTodayForegroundColor(Color foreground) {
		this.col_frg_today = foreground;
	}

	public Color getTodayBorderColor() {
		return col_brd_today;
	}

	public void setTodayBorderColor(Color borderColor) {
		this.col_brd_today = borderColor;
	}

	public Color getNotInMonthBackground() {
		return col_bkg_notInMonth;
	}

	public void setNotInMonthBackground(Color background) {
		this.col_bkg_notInMonth = background;
	}

	public Color getNotInMonthForeground() {
		return col_frg_notInMonth;
	}

	public void setNotInMonthForeground(Color foreground) {
		this.col_frg_notInMonth = foreground;
	}

	public Color getNormalDayBackground() {
		return col_bkg_normalDay;
	}

	public void setNormalDayBackground(Color background) {
		this.col_bkg_normalDay = background;
	}

	public Color getNormalDayForeground() {
		return col_frg_normalDay;
	}

	public void setNormalDayForeground(Color foreground) {
		this.col_frg_normalDay = foreground;
	}

	public Color getSelectedDayBackground() {
		return col_bkg_selectedDay;
	}

	public void setSelectedDayBackground(Color background) {
		this.col_bkg_selectedDay = background;
	}

	public Color getSelectedDayForeground() {
		return col_frg_selectedDay;
	}

	public void setSelectedDayForeground(Color foreground) {
		this.col_frg_selectedDay = foreground;
	}

	public Color getWeekEndBackground() {
		return col_bkg_weekEnd;
	}

	public void setWeekEndBackground(Color background) {
		this.col_bkg_weekEnd = background;
	}

	public Color getWeekEndForeground() {
		return col_frg_weekEnd;
	}

	public void setWeekEndForeground(Color foreground) {
		this.col_frg_weekEnd = foreground;
	}

	
}
