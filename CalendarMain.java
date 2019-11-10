package moneybook;

import java.util.ArrayList;
import java.util.Calendar;

class CalendarMethod{
	private String[] calHeader = {"��","��","ȭ","��","��","��","��"};
	ArrayList<Integer> calArr = new ArrayList<>();
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DATE);
	int lastDay = cal.getActualMaximum(Calendar.DATE); // �ش� ���� ��������¥
	int startDay_yoil = cal.get(Calendar.DAY_OF_WEEK); // �ش� ���� ���� �⺻������ ���� ��¥�� ����
	
	
	
	public ArrayList<Integer> getThisMonth() {
		for(int i =0;i<lastDay;i++) {
			calArr.add(i+1);
		}
				
		return  calArr;
	}
	
	public ArrayList<Integer> getlastMonth(){
		cal.set(Calendar.DATE, 1);
		startDay_yoil = cal.get(Calendar.DAY_OF_WEEK);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		int yoil = startDay_yoil - 1;
		for(int i =0; i<yoil;i++) {
			calArr.add(0, lastDay);
			lastDay--;
		}
		
		return calArr;
		
	}
	
	public void conSol(){
		for(int i = 0; i <calArr.size();i++) {
			System.out.print(calArr.get(i)+".");
			
			if(i % 7 == 6) {
				System.out.println();
			}
		}
	}
	
	
	
	
}


public class CalendarMain extends CalendarMethod{
	public static void main(String[] args) {
		CalendarMethod cal = new CalendarMethod();
		cal.getlastMonth();
		cal.getThisMonth();
		cal.conSol();
		
	
	}
}
