package fr.beaftech.surrender;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalendarMain {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("달력 프레임");
		JPanel myPanel = new JPanel();
		
		ArrayList<Integer> calArr = new ArrayList<Integer>();
		
		// 달력용
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		myPanel.setName(month+"월");
		
		// 이번달 세팅
		for (int i = 0; i < lastDay; i++) {
			calArr.add(i + 1);
		}
		// 지난달 세팅
		cal.set(Calendar.DATE, 1);
		int yoil = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(yoil);
		System.out.println(lastDay);
		int numOfPreMonth = yoil - 1;
		cal.add(Calendar.MONTH, -1);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		for (int i = 0; i < numOfPreMonth; i++) {
			calArr.add(0, lastDay);
			lastDay--;
		
		}
		
		// 다음달 세팅
		cal.add(Calendar.MONTH, 1);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DATE, lastDay);
		yoil = cal.get(Calendar.DAY_OF_WEEK);

		int numOfNextMonth = 7 - yoil;
		for (int i = 0; i < numOfNextMonth; i++) {
			calArr.add(i + 1);
		}
		// 콘솔창 출력
		for (int i = 0; i < calArr.size(); i++) {
			System.out.print(calArr.get(i) + ".");
			if (i % 7 == 6) {
				System.out.println();
			}

		}
		GridLayout gridLayout = new GridLayout(calArr.size() / 7, 7);
		myFrame.setLayout(gridLayout);
		
			
		for (int i = 0; i < calArr.size(); i++) {
			JButton btn = new JButton(calArr.get(i) + "");
			if (i % 7 == 0) {
				btn.setForeground(Color.RED);
			} else if (i % 7 == 6) {
				btn.setForeground(Color.RED);
			}
			
			myFrame.add(btn);
			
		}
		setFrame(700,700,myFrame);

	}
		private static void setFrame(int frameWidth,int frameHeight,JFrame myFrame) {
			myFrame.setSize(frameWidth, frameWidth); // 사이즈 지정
			myFrame.setResizable(false);
			
			Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
			int width = frameWidth;
			int height = frameHeight;
			myFrame.setLocation((width/2)-(frameWidth/2), (height/2)-(frameHeight/2));
			myFrame.setVisible(true);
			
			myFrame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		}

}
