
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalBorders.ButtonBorder;

//import graphics_library.ButtonListener;


public class graphics_library extends JFrame implements ActionListener{
	
	 /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static final Font font = new Font("monspaced", Font.BOLD, 25);
	private static JLabel display; // initial display

	// Listener for numeric and operator buttons
    // ActionListener buttonListener = new ButtonListener();
     public String buttonLabel = "";
     
     
     
     
	 public int[] grid1 = /*{2,1,3,4,5,6,7,8,9} */new int[9];
	 //public int current1;
	 int[] current1 = {0,0};
 	 int count = 0;
 	 boolean end = false, again = false;
 	 
	 JPanel content = new JPanel();
	 JPanel buttonPanel = new JPanel();
	 JButton reset = new JButton();
	 JLabel count1 = new JLabel("Number of moves: " + 0);
     
	 JLabel label = new JLabel();
	 
	 control control = new control();
	
	 
	 
	public static void main(String[] args) {
		
		 graphics_library graphics_library = new graphics_library();
		 
		 graphics_library.setupGraphics();
	     graphics_library.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     graphics_library.setVisible(true);
	     
	}
	
	
	
	public void buttons(String label) {
		
        // Layout numeric keys in a grid.
        buttonPanel.setLayout(new GridLayout(3, 3, 2, 2));    
        // Add button to panel
        JButton b = new JButton(label);
        
        if (end) {
        	b.removeActionListener(this);
        } else {
        	b.addActionListener(this);
        }
        b.setFont(font);
        
        if (Integer.parseInt(label) == current1[0]) {
        	b.setBackground(new java.awt.Color(133, 170, 229));
        } else {
        	b.setBackground(new java.awt.Color(112, 165, 249));
        }
        b.setLayout(new BorderLayout(5, 5));;
        buttonPanel.add(b);	
	}
	public void setupGraphics() {
		
        control.setGrid();
        for (int i = 0; i < grid1.length; i++) {
    		grid1[i] = control.grid[i];
		}
        for (int i = 0; i < grid1.length; i++) {
        	
        	buttons(Integer.toString(grid1[i]));
        	
        }
     // Layout the content panel.
        display = new JLabel("Sort this grid: ");
        display.setFont(font);
        
        content.setLayout(new BorderLayout(15, 15));
        content.setBackground(new java.awt.Color(225, 231, 242));
        content.add(display, BorderLayout.NORTH);
        content.add(buttonPanel, BorderLayout.CENTER);
        count1.setFont(font);
        content.add(count1, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
     // Finish building the window
        this.setContentPane(content);
        this.pack();
        this.setTitle("Number Sort");
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setSize(600, 600);
	}
	public void reset() {
        reset = new JButton("Play again?");
        reset.setFont(font);
        reset.setBackground(new java.awt.Color(112, 165, 249));
        reset.setLayout(new BorderLayout(5, 5));;
        if (end) {
        	reset.addActionListener(this);
        	again = true;
        }
       
	}
	
	// Button Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (!again) {
		String action1 = e.getActionCommand();
    	int action = Integer.parseInt(action1);
    	
    	if (count%2 == 0) {
    		current1[0] = action;
    	}
    	else {
    		current1[1] = action;
    	}  
    	count++;
    	
    	count1.setText("Moves count: " + count/2);
    	 buttonPanel.removeAll();
         content.remove(buttonPanel);
         for (int button = 0; button < grid1.length; button++) {
         	buttons(Integer.toString(grid1[button]));  	
         }

         content.add(buttonPanel, BorderLayout.CENTER);
         this.setContentPane(content);
         
    if (current1[0] != 0 && current1[1] != 0 && count%2 == 0) {
    		
            control.change(grid1, current1[0], current1[1]);
            current1[0] = 0;
    		current1[1] = 0;
            buttonPanel.removeAll();
            content.remove(buttonPanel);
            
            for (int button = 0; button < grid1.length; button++) {
            	buttons(Integer.toString(grid1[button]));  	
            }

            content.add(buttonPanel, BorderLayout.CENTER);
            this.setContentPane(content);
     
    		if (!control.check(grid1)) {
    			end = true;
    			buttonPanel.removeAll();
                content.remove(buttonPanel);
    			for (int button = 0; button < grid1.length; button++) {
                	buttons(Integer.toString(grid1[button]));  	
                }
    			reset();
    			display.setText("You've completed the sort!" + "     " + "Moves: " + count/2);
    			display.setFont(new Font("monspaced", Font.BOLD, 25));
    			content.remove(count1);
    			content.add(display, BorderLayout.NORTH);
    			content.add(reset, BorderLayout.SOUTH);
    			content.add(buttonPanel);
       			this.setContentPane(content);
       			
       			again = true;
       			
    		}
    	}
    }
		else {
			System.out.println("??");
			
			end = false;
			again = false;
			count = 0;
			current1[0] = 0;
    		current1[1] = 0;
            content.removeAll();
            //setupGraphics();
           /* display.setText("Sort this grid: ");
            display.setFont(font);
            content.add(display, BorderLayout.NORTH);*/
			//reset_true();
    		/*control.setGrid();
			for (int i = 0; i < grid1.length; i++) {
	    		grid1[i] = control.grid[i];
			}
			for (int i = 0; i < grid1.length; i++) {
	        	
	        	buttons(Integer.toString(grid1[i]));
	        	
	        }*/
			
			//reset.removeActionListener(this);
			
			//This is a really hacky way to do it but idgaf since testing
			control.setGrid();
	        for (int i = 0; i < grid1.length; i++) {
	    		grid1[i] = control.grid[i];
			}
	        for (int i = 0; i < grid1.length; i++) {
	        	
	        	buttons(Integer.toString(grid1[i]));
	        	
	        }
	     // Layout the content panel.
	        display = new JLabel("Sort this grid: ");
	        display.setFont(font);
	        
	        content.setLayout(new BorderLayout(15, 15));
	        content.setBackground(new java.awt.Color(225, 231, 242));
	        content.add(display, BorderLayout.NORTH);
	        content.add(buttonPanel, BorderLayout.CENTER);
	        count1.setFont(font);
	        content.add(count1, BorderLayout.SOUTH);
	        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	     // Finish building the window
	        this.setContentPane(content);
	        
	        
			/*count1.setText("Moves Count: : " + count/2);
			count1.setFont(font);
		    content.add(count1, BorderLayout.SOUTH);
			
			content.add(count1);
			content.remove(reset);
			content.add(buttonPanel);
			this.setContentPane(content);
			System.out.println("Reset function here");*/
		}	
	
	}
}
