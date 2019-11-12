package moneybook;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CalendarMethod {
	private String[] calHeader = { "일", "월", "화", "수", "목", "금", "토" };
	ArrayList<Integer> calArr = new ArrayList<>();
	Calendar cal = Calendar.getInstance();
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
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 1);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		for (int i = 0; i < lastDay; i++) {
			calArr.add(i + 1);
		}

		return calArr;
	}

	public ArrayList<Integer> getlastMonth() {
		cal.set(Calendar.DATE, 1);
		startDay_yoil = cal.get(Calendar.DAY_OF_WEEK);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		int yoil = startDay_yoil - 1;
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
		cal.getlastMonth();
		cal.getThisMonth();
		cal.conSol();
		String header[] = cal.getCalHeader();
		ArrayList<Integer> row = cal.calArr;
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
	//	JTable table = new JTable(header,row);
		
	//	GridLayout gridLayout = new GridLayout(cal.calArr.size()/7,7);
	//	frame.setLayout(gridLayout);
		frame.setVisible(true);
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setSize(800, 800);
		
		for (int i = 0; i < cal.calArr.size(); i++) {
			JButton btn = new JButton(Integer.toString(cal.calArr.get(i))+" ");
			
			btn.setSize(100, 100);

			if (i % 7 == 0) {
				btn.setBackground(Color.RED);
			} else if (i % 7 == 6) {
				btn.setBackground(Color.RED);
			}
			panel.add(btn);
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
