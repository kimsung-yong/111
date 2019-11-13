package moneybook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CalendarMethod {
	private String[] calHeader = { "일", "월", "화", "수", "목", "금", "토" };
	ArrayList<Integer> calArr = new ArrayList<>();
	Calendar cal = Calendar.getInstance(); // 전달
	Calendar cal1 = Calendar.getInstance(); // 이번달
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DATE);
	int lastDay = cal.getActualMaximum(Calendar.DATE); // 해당 달의 마지막날짜
	int startDay_yoil = cal.get(Calendar.DAY_OF_WEEK); // 해당 일의 요일 기본적으로 현재 날짜의 요일

	public String[] getCalHeader() {
		return calHeader;
	}

	public void setCalHeader(String[] calHeader) {
		this.calHeader = calHeader;
	}

	public ArrayList<Integer> getThisMonth() {
 //     cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.DATE, 1);
		System.out.println("이번달"+cal1.get(Calendar.MONTH));
		lastDay = cal1.getActualMaximum(Calendar.DATE);
		for (int i = 0; i < lastDay+1; i++) {
			calArr.add(i + 1);
		}

		return calArr;
	}
		
	public ArrayList<Integer> getlastMonth() {
		cal.set(Calendar.DATE, 1);
		
		startDay_yoil = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("스타트데이"+startDay_yoil);
		cal.add(Calendar.MONTH, -1);
		System.out.println("라스트데이"+lastDay+"저번달"+cal.get(Calendar.MONTH));
		lastDay = cal.getActualMaximum(Calendar.DATE);
		int yoil = startDay_yoil-1;
		
		for (int i = 0; i < yoil; i++) {
			calArr.add(0, lastDay);
			lastDay--;
		}

		return calArr;

	}

	public void conSol() {
		for (int i = 0; i < calArr.size(); i++) {
			System.out.print(calArr.get(i) + ".");

			if (i % 7 == 6) {
				System.out.println();
			}
		}
	}

}

public class CalendarMain extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		CalendarMethod cal = new CalendarMethod();
		cal.getThisMonth();
		cal.getlastMonth();
		
		cal.conSol();
		String header[] = cal.getCalHeader();
	//	ArrayList<Integer> row = cal.calArr;
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
	//	JTable table = new JTable(header,row);
		
	//	GridLayout gridLayout = new GridLayout(cal.calArr.size()/7,7);
	//	frame.setLayout(gridLayout);
	//	frame.getContentPane().setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.setSize(640, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		
		for(int i = 0; i<header.length;i++) {
			JButton btn = new JButton(header[i]);
			btn.setPreferredSize(new Dimension(75,75));
			if (i % 7 == 0) {
				btn.setForeground(Color.RED);
			} else if (i % 7 == 6) {
				btn.setForeground(Color.RED);
			}
			panel.add(btn);
		}

		for (int i = 0; i < cal.calArr.size(); i++) {
			
			JButton btn1 = new JButton(Integer.toString(cal.calArr.get(i)));
			
			
			btn1.setPreferredSize(new Dimension(75,75));
	//		btn.setSize(100, 100);

			if (i % 7 == 0) {
				btn1.setForeground(Color.RED);
			} else if (i % 7 == 6) {
				btn1.setForeground(Color.RED);
			}
			panel.add(btn1,BorderLayout.WEST);
		} 
	/*	for (int i = 0; i < cal.calArr.size(); i++) {
			JButton btn = new JButton(Integer.toString(cal.calArr.get(i)));
			btn.setSize(200, 200);
			if (i % 7 == 0) {
				btn.setBackground(Color.RED);
			} else if (i % 7 == 6) {
				btn.setBackground(Color.RED);
			}
			panel.add(btn);
		} */

	}
}
