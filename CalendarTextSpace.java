package calendarProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalendarTextSpace extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	String path = "\\src\\calendarProject\\memo";
	
	JPanel centerPan = new JPanel();
	JPanel bottomPan = new JPanel();
	
	JTextArea textArea = new JTextArea();
	
	JButton saveBtn = new JButton("Save");
	JButton escBtn = new JButton("Esc");
	
	CalendarFrame cal = new CalendarFrame();
	CalendarTextSpace(){
		centerPan.add(textArea);
		
		bottomPan.add(saveBtn);
		bottomPan.add(escBtn);
		
		centerPan.setBackground(Color.WHITE);
		centerPan.setSize(500, 400);
		
		bottomPan.setBackground(Color.BLACK);
		
		super.setLayout(new BorderLayout());
		super.add(centerPan,BorderLayout.CENTER);
		super.add(bottomPan,BorderLayout.SOUTH);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(500, 500);
		super.setVisible(true);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(saveBtn)) {
			String c = textArea.getText();
			OutputStream out;
			try {
				out = new FileOutputStream(path + cal.year +cal.month + cal.day + ".txt" );
				byte[] ab = c.getBytes();
				ab = c.getBytes(c);
				out.write(ab);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(obj.equals(escBtn)) {
			
		}
		
	}
	
	public static void main(String[] args) {
		new CalendarTextSpace();
	}
	
}
