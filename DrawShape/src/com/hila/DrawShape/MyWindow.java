package com.hila.DrawShape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//MyWindow class responsible for creating the user GUI 
public class MyWindow {
	//Buttons for the gui		
	JButton line,circle,curve,polygon,clear,uploadMyShape;	
	JButton translation, shearingX, shearingY, scaling, mirrorX, mirrorY, rotation, normalize;
	//buttonPanel is panel that include the default buttons 
	final JPanel buttonPanel = new JPanel();
	//userInputPanel is panel that include input fields from the user in the gui
	final JPanel userInputPanel = new JPanel();	
	//messagePanel is panel that include the buttonPanel and the userInputPanel
	final JPanel messagePanel = new JPanel();
	//input field is the input field from the user .
	static JTextField input = new JTextField();
	//panel is Object from DrawShape class, and this object responsible for drawing shapes .
	protected static final int PADDING = 0;
	//DrawShape
	DrawShape  panel= new DrawShape();
	//app is JFrame that include all the previous components .	
	final JFrame app = new JFrame();
	//MyWindow constructor creates the GUI .
	MyWindow (){
		//create the message panel
		messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0,0));
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setPreferredSize(new Dimension(200,150));
		messagePanel.setBackground(Color.white);
		//create the buttons
		uploadMyShape =  new JButton ("upload");
		translation =  new JButton ("translation");
		rotation = new JButton ("rotation");
		scaling = new JButton ("scaling");
		shearingX = new JButton ("shearingX");
		shearingY = new JButton ("shearingY");
		mirrorX = new JButton ("mirrorX");
		mirrorY= new JButton ("mirrorY");
		normalize = new JButton ("normaliztion");
		clear =  new JButton ("clear");
		//add the buttons to the buttonPanel
		buttonPanel.add(uploadMyShape);
		buttonPanel.add(translation);
		buttonPanel.add(rotation);
		buttonPanel.add(scaling);
		buttonPanel.add(shearingX);
		buttonPanel.add(shearingY);
		buttonPanel.add(mirrorX);
		buttonPanel.add(mirrorY);
		buttonPanel.add(normalize);
		buttonPanel.add(clear);
		buttonPanel.setBackground(Color.red);
		//add buttonPanel to messagePanel
		messagePanel.add(buttonPanel,BorderLayout.PAGE_START);
		//create userInputPanel	
	    userInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
		userInputPanel.setPreferredSize(new Dimension (500,110));
		userInputPanel.setBackground(Color.lightGray);
		JTextArea textArea = new JTextArea(
	            "Welcome to Transform App, Upload the sailboat and choose transform to begin :) " , 5, 50);
		   textArea.setLineWrap(true);
		   textArea.setWrapStyleWord(true);
		   textArea.setOpaque(false);
		   textArea.setEditable(false);
		   textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
		userInputPanel.add(textArea);
		userInputPanel.setVisible(true);
		//userInputPanel.setVisible(false);
		//add userInputPanel to messagePanel
		messagePanel.add(userInputPanel,BorderLayout.CENTER);	
		//create the app JFrame
		app.setSize(1100, 700);
		app.setLayout(new BorderLayout());
		app.add(messagePanel,BorderLayout.PAGE_START);
		app.add(panel);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
		//Add Action Listeners for the buttons 	    
		translation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.TRANSLATION;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
				JTextArea textArea = new JTextArea(
						"Hi, This is one of the primary transforms: Translation. \n"+ "To move the boat you need to click twice on the screen,\n"
						+ "The first click will be a starting point and the second click will be a destination." , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);
				System.out.println("You clicked the button");
						    					
            }			
		} );
		//Add Action Listeners for the buttons 	    
		uploadMyShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.UPLOAD;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
				JTextArea textArea = new JTextArea(
						"Hi, This is our boat , let's start by clicking on one of the Transform's buttons ..." , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);
				System.out.println("You clicked the button");
				panel.drawMyBoat();
						    					
            }			
		} );
		//Add Action Listeners for the buttons 	    
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.CLEAR;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
				JTextArea textArea = new JTextArea("Clear the surface,\n" , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);
				System.out.println("You clicked the button");
				if (panel.map.size() > 0 ){
					//userInputPanel.setVisible(false);
					//Execute when button is pressed
					userInputPanel.removeAll();
				    userInputPanel.revalidate();
				    panel.map.clear();
					panel.repaint();
				}
				
						    					
            }			
		} );
		
		//Add Action Listeners for the buttons 	    
		normalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.NORMALIZE;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
				JTextArea textArea = new JTextArea(
						"Hi, You pressed to normalize the shape to the borders of the screen." , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);
				System.out.println("You clicked the button");
				panel.normalize();
            }			
		} );
		//Add Action Listeners for the buttons 	    
		rotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.ROTATION;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
			    JTextArea textArea = new JTextArea(
						"Hi, Rotation Around Point. \n"+ "To rotate the boat you can insert the size of angle,\n"
						+ "then click on the screen to make a rotation around the point you clicked." , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);
			    JButton button =  new JButton("Click to change size of angle");
			    button.addActionListener(new ActionListener(){

			        public void actionPerformed(ActionEvent e)

			            {

			        		panel.setDelta(Math.toRadians(Double.parseDouble(input.getText())));

			            }

			    });
			    input = new JTextField ("10",10);
			    input.setHorizontalAlignment(JTextField.CENTER);
			    /*Get delta from text field*/
			    double delta = Double.parseDouble(input.getText());
			    delta = Math.toRadians(delta);
			    panel.setDelta(delta);
			    /*Finish with delta*/
			    JPanel inputUser = new JPanel();
			    inputUser.setBackground(Color.lightGray);
			    inputUser.add(input);
			    inputUser.add(button);
			    userInputPanel.add(inputUser);
			    JPanel inputPanel = new JPanel();
			    inputPanel.setSize(40, 10);	  
			    System.out.println("im here");
		
			   
				
            }			
		});

		//Add Action Listeners for the buttons 	    
		scaling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.SCALING;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
				JTextArea textArea = new JTextArea(
						"Hi, Scaling Aound point.\n" +"To make Scaling around a point you need to click on the screen,\n"+"You can change the scaling percentage . Less than 1 will increase the other way will reduce." , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);
			    JButton button =  new JButton("Click to change size of angle");
			    button.addActionListener(new ActionListener(){

			        public void actionPerformed(ActionEvent e)

			            {

			        		panel.setS(Double.parseDouble(input.getText()));

			            }

			    });			    input = new JTextField ("0.5",10);
			    input.setHorizontalAlignment(JTextField.CENTER);
			    JPanel inputUser = new JPanel();
			    inputUser.setBackground(Color.lightGray);
			    inputUser.add(input);
			    inputUser.add(button);

			    userInputPanel.add(inputUser);
			    JPanel inputPanel = new JPanel();
			    inputPanel.setSize(40, 10);	  
				
            }			
		});
		
		//Add Action Listeners for the buttons 	    
		shearingX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.SHEARINGX;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
				JTextArea textArea = new JTextArea(
						"Hi, Shearing by axis Y.\n" +"Horizontal shear action will take place by clicking twice on the screen,"
						+ "on right or left using the mouse." , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);
			   
				
            }			
		});
		
		//Add Action Listeners for the buttons 	    
		shearingY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				DrawShape.transform = Transform.SHEARINGY;
				userInputPanel.setVisible(false);
				//Execute when button is pressed
				userInputPanel.removeAll();
			    userInputPanel.revalidate();
				JTextArea textArea = new JTextArea(
						"Hi, Shearing by axis Y.\n" +"Vertical shear action will take place by clicking twice on the screen,"
						+ "on up or down using the mouse." , 5, 60);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setOpaque(false);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
				userInputPanel.add(textArea);
			    userInputPanel.setVisible(true);			
            }			
		});

			//Add Action Listeners for the buttons 	    
			mirrorX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
	            {
					DrawShape.transform = Transform.MIRRORX;
					userInputPanel.setVisible(false);
					//Execute when button is pressed
					userInputPanel.removeAll();
				    userInputPanel.revalidate();
					JTextArea textArea = new JTextArea(
							"Hi, Mirror axis X" , 5, 60);
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
					textArea.setOpaque(false);
					textArea.setEditable(false);
					textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
					userInputPanel.add(textArea);
				    userInputPanel.setVisible(true);	
				    panel.drawMirrorX();
	            }			
			});
			//Add Action Listeners for the buttons 	    
			mirrorY.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
	            {
					DrawShape.transform = Transform.MIRRORY;
					userInputPanel.setVisible(false);
					//Execute when button is pressed
					userInputPanel.removeAll();
				    userInputPanel.revalidate();
					JTextArea textArea = new JTextArea(
							"Hi, Mirror axis Y" , 5, 60);
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
					textArea.setOpaque(false);
					textArea.setEditable(false);
					textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, -30,-25));
					userInputPanel.add(textArea);
				    userInputPanel.setVisible(true);	
				    panel.drawMirrorY();

	            }			
			});

		};

		

}
