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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A view that allows to navigation from month to month
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 */
public class CalendarMonthView extends JPanel {


	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private CalendarModel model;
	private SimpleDateFormat sdf;
	
	public CalendarMonthView(){
		this(new DefaultCalendarModel());
	}

	/**
	 * Create the panel.
	 */
	public CalendarMonthView(CalendarModel calendarModel) {
		
		model = calendarModel;
		sdf = new SimpleDateFormat("MMMMMMMMMMMMMMM YYYY");
		model.addListener(new CalendarListener() {
			
			public void CalendarEventOccurs(CalendarEvent ce) {
				
				applyDate();
				
			}
		});
		
		JButton b_prevMonth = new JButton("<");
		b_prevMonth.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				model.addMonth(-1);
				
			}
		});
		
		JButton b_prevYear = new JButton("<<");
		b_prevYear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				model.addYear(-1);
				
			}
		});
		add(b_prevYear);
		add(b_prevMonth);
		
		textField = new JTextField();
		textField.setEditable(false);
		add(textField);
		textField.setColumns(10);
		
		JButton b_nextMonth = new JButton(">");
		b_nextMonth.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				model.addMonth(1);
				
			}
		});
		add(b_nextMonth);
		
		JButton b_nextYear = new JButton(">>");
		b_nextYear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				model.addYear(1);
				
				
			}
		});

		add(b_nextYear);
		
		applyDate();

	}
	
	private void applyDate(){
		textField.setText(sdf.format(model.getSelectedDate()));
	}

}
