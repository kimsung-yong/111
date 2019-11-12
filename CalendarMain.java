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

public class CalendarMain {
	public static void main(String[] args) {
		CalendarMethod cal = new CalendarMethod();
		cal.getlastMonth();
		cal.getThisMonth();
		cal.conSol();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel label;
		for(int i = 0;i<cal.getlastMonth().size();i++) {
			label = new JLabel(cal.calArr);
			label.setSize(80, 80);
			panel.add(label);
		}
		for(int i =0; i<cal.getThisMonth().size();i++) {
			label = new JLabel(cal.calArr);
			label.setSize(80, 80);
			panel.add(label);
		}
		frame.setVisible(true);
		frame.getDefaultCloseOperation();
	}
}
