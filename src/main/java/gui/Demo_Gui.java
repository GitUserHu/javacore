package gui;
/**
 * @author JiaBing
 * @date 2019-03-05 21:46:19 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Demo_Gui {
	static JButton startBtn = new JButton("Start");
	JButton cancelBtn = new JButton("Cancel");
	
	public static void main(String[] args) {
		startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(e.getActionCommand());
			}
		});
	}
}
