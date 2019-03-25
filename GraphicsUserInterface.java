import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GraphicsUserInterface extends JFrame implements ActionListener {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -7816657412276748760L;
	
	private int windowWidth = 500;
	private int windowHieght = 500;
	private JButton startbtn, stopbtn;
	private JLabel lastSessionInfo;
	private JLabel subjectDescriptions;
	private ButtonGroup subjects;
	private Font font = new Font("Helvetica Neue",10,15);
	private int btnwidth = 140;
	private int btnheight = 40;
	
	DateAndTime dateClass = new DateAndTime("/Users/j4k3/eclipse-workspace/TrackTime/src/Dates.csv");
	
	public GraphicsUserInterface() {
		setTitle("Time Tracker");
		setLayout(null);
		setSize(windowWidth,windowHieght);
		setLocationRelativeTo(null);
		add(startPanel());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected Panel startPanel() {
		Panel actionPanel = new Panel();
		GroupLayout layout = new GroupLayout(actionPanel);
		
		setLabel(dateClass.lastAction() + "ed");
		setButtons();
		setAction();
		
		actionPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(lastSessionInfo)
						.addGroup(layout.createSequentialGroup()
							.addComponent(startbtn, 50, btnwidth, btnwidth)
							.addComponent(stopbtn, 50, btnwidth, btnwidth)
						)
				)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(lastSessionInfo)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(startbtn, 20, btnheight, btnheight)
						.addComponent(stopbtn, 20, btnheight, btnheight)
				)
				
		);
		
		actionPanel.setSize(windowWidth, 75);
		actionPanel.setLocation(70, 350);
		actionPanel.setVisible(true);
		return actionPanel;
	}
	
	private void checkLastAction() {
		boolean previousAction = dateClass.lastAction().equals("start");
		stopbtn.setEnabled(previousAction);
		startbtn.setEnabled(!previousAction);
	}
	
	private void setButtons() {
		startbtn = new JButton("Start");
		stopbtn = new JButton("Stop");
		startbtn.setPreferredSize(new Dimension(btnwidth, btnheight));
		stopbtn.setPreferredSize(new Dimension(btnwidth, btnheight));
		checkLastAction();
	}
	private void setAction() {
		startbtn.addActionListener(this);
		startbtn.setActionCommand("startTime");
		stopbtn.addActionListener(this);
		stopbtn.setActionCommand("stopTime");
	}
	
	private void setLabel(String lastAction) {
		lastSessionInfo = new JLabel();
		lastSessionInfo.setText("Session " + lastAction + " at: " + dateClass.LastSession());
		lastSessionInfo.setFont(font);
		lastSessionInfo.setVisible(true);
	}
	private void clearLabel() {
		remove(lastSessionInfo);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "startTime":
				dateClass.start("start");
				clearLabel();
				setLabel("started");
				break;
			case "stopTime":
				dateClass.start("end");
				clearLabel();
				setLabel("ended");
				break;
				
		}
//		if (e.getActionCommand().equals("startTime")) {
//			dateClass.start("start");
//			clearLabel();
//			setLabel("started");
//		} else if (e.getActionCommand().equals("stopTime")) {
//			dateClass.start("end");
//			clearLabel();
//			setLabel("ended");
//		}
		checkLastAction();
		repaint();
		
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GraphicsUserInterface gui = new GraphicsUserInterface();
	}

}
