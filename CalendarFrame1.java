package moneybook.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalendarFrame1 extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Calendar cal;
	int year,month,day,lastDay,yoil;
	String header[] = {"일","월","화","수","목","금","토"};
	
	JPanel topPan = new JPanel(); // 상단
	JPanel centerPan = new JPanel(); // 센터
	
	JPanel headerPan = new JPanel(); // 센터에 추가될거
	JPanel datePan = new JPanel(); // 센터에 추가될거
	
/*	ImageIcon leftimg = new ImageIcon(".\\src\\moneybook\\calendar\\IMG\\좌살표.png");
	ImageIcon rightimg = new ImageIcon(".\\src\\moneybook\\calendar\\IMG\\우살표.png"); */
	
	JButton leftBtn = new JButton("◀");
	JButton rightBtn = new JButton("▶");
	
	JLabel yearlabel = new JLabel("년");
	JLabel monthlabel = new JLabel("월");
	
	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
	
	JComboBox<Integer> monthCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
	
	CalendarFrame1(){
		super("달력프레임");
		super.setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 자원 해제후 종료
		
/*		yearlabel.setFont(new Font("", Font.BOLD, 22));
		monthlabel.setFont(new Font("", Font.BOLD, 22));
		leftBtn.setPreferredSize(new Dimension(50,50));
		rightBtn.setPreferredSize(new Dimension(50,50));
		yearCombo.setPreferredSize(new Dimension(60, 50));
		monthCombo.setPreferredSize(new Dimension(60, 50));
*/		
		topPan.add(leftBtn);
		topPan.add(yearCombo);
		topPan.add(yearlabel);
		topPan.add(monthCombo);
		topPan.add(monthlabel);
		topPan.add(rightBtn);
		
		cal = Calendar.getInstance(); // 현재 날짜기준 데이터 받아옴
		
		year = cal.get(Calendar.YEAR);
		
		month = cal.get(Calendar.MONTH) + 1;
		
		for(int i = year - 100; i <year +50; i++) {
			yearModel.addElement(i);
		}
		
		yearCombo.setModel(yearModel);
		
		yearCombo.setSelectedItem(year);
		
		for(int i = 1; i <13; i++) {
			monthModel.addElement(i);
		}
		
		monthCombo.setModel(monthModel);
		
		monthCombo.setSelectedItem(month);
		headerPan.setLayout(new GridLayout(1,7));
		
		centerPan.add(headerPan);
		for(int i = 0; i < header.length; i++) {
			JLabel headerLa = new JLabel(header[i],JLabel.CENTER);
			headerLa.setPreferredSize(new Dimension(48,30));
			headerLa.setFont(new Font("", Font.BOLD, 20));
			headerPan.add(headerLa);
			
			if(i == 0 || i == 6) {
				headerLa.setForeground(Color.RED);
			}
		}
		headerPan.setBackground(Color.WHITE);
		centerPan.add(datePan);
		datePan.setLayout(new GridLayout(0,7));
		
		printDate(year,month);
		
		super.add(centerPan,"Center");
		super.add(topPan,"North");
		super.setVisible(true);
		super.setSize(400, 300);
		super.setLocationRelativeTo(null);
		
		leftBtn.addActionListener(this);
		rightBtn.addActionListener(this);
		yearCombo.addActionListener(this);
		monthCombo.addActionListener(this);
	}
	
	
	public void printDate(int y,int m) {
		year = y;
		month = m -1;
		
		cal.set(year, month, 1);
		
		yoil = cal.get(Calendar.DAY_OF_WEEK);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		
		
		// 빈화면
		for(int i = 1; i < yoil; i++) {
			JLabel label = new JLabel(" ");
			datePan.add(label);
		}
		
		for(int i = 1; i < lastDay+1; i++) {
			JButton btn = new JButton(Integer.toString(i));
			cal.set(year, month, i);
			int yoil = cal.get(Calendar.DAY_OF_WEEK);
			if(yoil == 7 || yoil == 1) {
				btn.setForeground(Color.red);
				datePan.add(btn);
			}
			datePan.add(btn);
		}
		
	}
	
	public void startDate() {
		datePan.setVisible(false);
		
		datePan.removeAll();
		
		printDate((Integer)yearCombo.getSelectedItem(),(Integer)monthCombo.getSelectedItem());
		
		datePan.setVisible(true);
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =  e.getSource();
		int yy = (int) yearCombo.getSelectedItem();
		int mm = (int) monthCombo.getSelectedItem();
		if(obj instanceof JButton){
			
		
		if(obj.equals(leftBtn)) {
			if(mm == 1) {
				yy--;
				mm = 12;
			}else {
				mm--;
			}
		}
		else if(obj.equals(rightBtn)) {
			if(mm == 12) {
				yy++;
				mm = 1;
			}else {
				mm++;
			}
		}
		
		yearCombo.setSelectedItem(yy);
		monthCombo.setSelectedItem(mm);
		
		}
		if(obj instanceof JComboBox) {
			startDate();
		}
		
	}
	
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	

	public static void main(String[] args) {
		new CalendarFrame1();
	}

	

	
}
