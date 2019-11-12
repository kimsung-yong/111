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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 */
public class CalendarDialog extends JDialog {


	private static final long serialVersionUID = 1L;
	
	public static final int OK_OPTION = JOptionPane.OK_OPTION;
	public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;
	public static final int CLOSED_OPTION = JOptionPane.CLOSED_OPTION;
	
	private int closingValue = CLOSED_OPTION;
	
	private final CalendarPanel contentPanel = new CalendarPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CalendarDialog dialog = new CalendarDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CalendarDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setFocusCycleRoot(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						closingValue = OK_OPTION;
						close();
					}
				});
				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						closingValue = CANCEL_OPTION;
						close();
					}
				});
			}
		}
	}
	
	public int getClosingValue(){
		return closingValue;
	}
	
	private void close(){
		switch(getDefaultCloseOperation()){
		
		case DISPOSE_ON_CLOSE:
			dispose();
			break;
		
		case HIDE_ON_CLOSE:
			setVisible(false);
			break;
			
		case DO_NOTHING_ON_CLOSE:
			break;
		
		}
		
	}

	public Date getSelectedDate() {
		return contentPanel.getSelectedDate();
	}

	public void setSelectedDate(Date date) {
		contentPanel.setSelectedDate(date);
	}

	public int getFirstDayOfWeek() {
		return contentPanel.getFirstDayOfWeek();
	}

	public void setFirstDayOfWeek(int firstDayOfWeek) {
		contentPanel.setFirstDayOfWeek(firstDayOfWeek);
	}

	public Color getTodayBackgroundColor() {
		return contentPanel.getTodayBackgroundColor();
	}

	public void setTodayBackgroundColor(Color backbround) {
		contentPanel.setTodayBackgroundColor(backbround);
	}

	public Color getTodayForegroundColor() {
		return contentPanel.getTodayForegroundColor();
	}

	public void setTodayForegroundColor(Color foreground) {
		contentPanel.setTodayForegroundColor(foreground);
	}

	public Color getTodayBorderColor() {
		return contentPanel.getTodayBorderColor();
	}

	public void setTodayBorderColor(Color borderColor) {
		contentPanel.setTodayBorderColor(borderColor);
	}

	public Color getNotInMonthBackground() {
		return contentPanel.getNotInMonthBackground();
	}

	public void setNotInMonthBackground(Color background) {
		contentPanel.setNotInMonthBackground(background);
	}

	public Color getNotInMonthForeground() {
		return contentPanel.getNotInMonthForeground();
	}

	public void setNotInMonthForeground(Color foreground) {
		contentPanel.setNotInMonthForeground(foreground);
	}

	public Color getNormalDayBackground() {
		return contentPanel.getNormalDayBackground();
	}

	public void setNormalDayBackground(Color background) {
		contentPanel.setNormalDayBackground(background);
	}

	public Color getNormalDayForeground() {
		return contentPanel.getNormalDayForeground();
	}

	public void setNormalDayForeground(Color foreground) {
		contentPanel.setNormalDayForeground(foreground);
	}

	public Color getSelectedDayBackground() {
		return contentPanel.getSelectedDayBackground();
	}

	public void setSelectedDayBackground(Color background) {
		contentPanel.setSelectedDayBackground(background);
	}

	public Color getSelectedDayForeground() {
		return contentPanel.getSelectedDayForeground();
	}

	public void setSelectedDayForeground(Color foreground) {
		contentPanel.setSelectedDayForeground(foreground);
	}

	public Color getWeekEndBackground() {
		return contentPanel.getWeekEndBackground();
	}

	public void setWeekEndBackground(Color background) {
		contentPanel.setWeekEndBackground(background);
	}

	public Color getWeekEndForeground() {
		return contentPanel.getWeekEndForeground();
	}

	public void setWeekEndForeground(Color foreground) {
		contentPanel.setWeekEndForeground(foreground);
	}


	
	
	

}
