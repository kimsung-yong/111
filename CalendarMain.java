package moneybook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CalendarMethod {
	private String[] calHeader = { "��", "��", "ȭ", "��", "��", "��", "��" };
	ArrayList<Integer> calArr = new ArrayList<>();
	Calendar cal = Calendar.getInstance(); // ����
	Calendar cal1 = Calendar.getInstance(); // �̹���
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DATE);
	int lastDay = cal.getActualMaximum(Calendar.DATE); // �ش� ���� ��������¥
	int startDay_yoil = cal.get(Calendar.DAY_OF_WEEK); // �ش� ���� ���� �⺻������ ���� ��¥�� ����

	public String[] getCalHeader() {
		return calHeader;
	}

	public void setCalHeader(String[] calHeader) {
		this.calHeader = calHeader;
	}

	public ArrayList<Integer> getThisMonth() {
 //     cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.DATE, 1);
		System.out.println("�̹���"+cal1.get(Calendar.MONTH));
		lastDay = cal1.getActualMaximum(Calendar.DATE);
		for (int i = 0; i < lastDay+1; i++) {
			calArr.add(i + 1);
		}

		return calArr;
	}
		
	public ArrayList<Integer> getlastMonth() {
		cal.set(Calendar.DATE, 1);
		
		startDay_yoil = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("��ŸƮ����"+startDay_yoil);
		cal.add(Calendar.MONTH, -1);
		System.out.println("��Ʈ����"+lastDay+"������"+cal.get(Calendar.MONTH));
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
	//	JPanel panel = new JPanel(new GridLayout(7,7));
		JPanel panel = new JPanel(); //�޷�
		JPanel panel1 = new JPanel(); //��� �� ���� �г�
	    JLabel label = new JLabel(cal.year+"��"+(cal.month+1)+"��");
	    ImageIcon img = new ImageIcon("C:\\Users\\user\\Desktop\\workspace\\moneybook\\src\\moneybook\\IMG\\�»�ǥ.png");
	    ImageIcon img1 = new ImageIcon("C:\\Users\\user\\Desktop\\workspace\\moneybook\\src\\moneybook\\IMG\\���ǥ.png");
	    JButton btn2 = new JButton(img);
	    JButton btn3 = new JButton(img1);
	    btn2.setPreferredSize(new Dimension(50, 50));
	    btn3.setPreferredSize(new Dimension(50, 50));
		label.setFont(new Font("",Font.BOLD,30));
	    frame.setLayout(new BorderLayout());
		GridLayout gridLayout = new GridLayout(0,7);
		panel.setLayout(gridLayout);
	//	frame.getContentPane().setLayout(new FlowLayout());
		
		frame.setSize(640, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,"Center");
		panel.setSize(640, 600);
		frame.setLocationRelativeTo(null);
		frame.add(panel1,"North");
		panel1.setSize(640, 200);
		panel1.add(btn2);
		panel1.add(label);
		panel1.add(btn3);
		
		
		
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
			panel.add(btn1);
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
		
		System.out.println(cal.calArr.size());
		frame.setVisible(true);
		
	}
}