package mousedraw;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MouseDraw extends JFrame {

	// ------------ Class Variables / Field Variables ------------ //

	JMenuBar mainMenuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenu boardColor = new JMenu("BoardColor");
	JMenu penSize = new JMenu("PenSize");

	JMenuItem newMenuItem = new JMenuItem("New");
	JMenuItem exitMenuItem = new JMenuItem("Exit");

	JMenuItem blackBoard = new JMenuItem("Black");
	JMenuItem yellowBoard = new JMenuItem("Yellow");
	JMenuItem redBoard = new JMenuItem("Red");

	JMenuItem penFine = new JMenuItem("Fine");
	JMenuItem penSmall = new JMenuItem("Small");
	JMenuItem penMedium = new JMenuItem("Medium");
	JMenuItem penLarge = new JMenuItem("Large");
	JMenuItem penXLarge = new JMenuItem("Extra Large");

	JPanel drawPanel = new JPanel();
	JLabel leftColorLabel = new JLabel();
	JLabel rightColorLabel = new JLabel();

	JPanel colorPanel = new JPanel();
	JLabel[] colorLabel = new JLabel[18];
	Color drawColor, leftColor, rightColor;
	
	double xPrevious, yPrevious;
	
	Graphics2D g2D;

	public MouseDraw() {

		// ------------ Constructors ------------ //

		setTitle("Aristic Creations by Josh S October 2018");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}

		});

		getContentPane().setLayout(new GridBagLayout());
		getContentPane().setBackground(new Color(168, 231, 255));
		setJMenuBar(mainMenuBar);
		mainMenuBar.setBackground(new Color(234, 168, 255));
		mainMenuBar.setBorder(BorderFactory.createLineBorder(new Color(234, 168, 255)));

		fileMenu.setMnemonic('F');
		mainMenuBar.add(fileMenu);
		fileMenu.add(newMenuItem);
		newMenuItem.setBackground(new Color(234, 168, 255));
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		exitMenuItem.setBackground(new Color(234, 168, 255));

		boardColor.setMnemonic('B');
		mainMenuBar.add(boardColor);
		blackBoard.setBackground(new Color(0, 0, 0));
		blackBoard.setForeground(new Color(255, 255, 255));
		boardColor.add(blackBoard);

		boardColor.addSeparator();
		yellowBoard.setBackground(new Color(244, 244, 0));
		boardColor.add(yellowBoard);

		boardColor.addSeparator();
		redBoard.setBackground(new Color(244, 0, 0));
		boardColor.add(redBoard);

		penSize.setMnemonic('P');
		mainMenuBar.add(penSize);
		penSize.add(penFine);
		penFine.setBackground(new Color(234, 168, 255));
		penSize.addSeparator();
		penSize.add(penSmall);
		penSmall.setBackground(new Color(234, 168, 255));
		penSize.addSeparator();
		penSize.add(penMedium);
		penMedium.setBackground(new Color(234, 168, 255));
		penSize.addSeparator();
		penSize.add(penLarge);
		penLarge.setBackground(new Color(234, 168, 255));
		penSize.addSeparator();
		penSize.add(penXLarge);
		penXLarge.setBackground(new Color(234, 168, 255));
		
		drawPanel.setPreferredSize(new Dimension(500,400));
		drawPanel.setBackground(new Color(200,200,200));

		// ------------ Listeners ------------ //

		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMenuItemActionPerformed(e);
			}

		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMenuItemActionPerformed(e);
			}

		});
		blackBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blackBoardActionPerformed(e);
			}

		});
		yellowBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yellowBoardActionPerformed(e);
			}

			

		});
		redBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redBoardActionPerformed(e);
			}

		});
		
		
		penFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				penFineActionPerformed(e);
			}

		});
		penSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				penSmallActionPerformed(e);
			}

		});
		penMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				penMediumActionPerformed(e);
			}

		});
		penLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				penLargeActionPerformed(e);
			}

		});
		penXLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				penXLargeActionPerformed(e);
			}

		});

		// ------------ Constraints ------------ //


		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.gridheight = 2;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(drawPanel, gridConstraints);

		drawPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				drawPanelMousePressed(e);
			}

		});
		drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				drawPanelMouseDragged(e);
			}

		});
		drawPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				drawPanelMouseReleased(e);
			}

		});

		leftColorLabel.setPreferredSize(new Dimension(40, 40));
		leftColorLabel.setOpaque(true);
		leftColorLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10, -50, 10, 0);
		getContentPane().add(leftColorLabel, gridConstraints);

		rightColorLabel.setPreferredSize(new Dimension(40, 40));
		rightColorLabel.setOpaque(true);
		rightColorLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10, 60, 10, 10);
		getContentPane().add(rightColorLabel, gridConstraints);

		colorPanel.setPreferredSize(new Dimension(100, 350));
		colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
		colorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		colorPanel.setBackground(new Color(205,205,205));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.gridwidth = 1;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(0, 10, 10, 10);
		getContentPane().add(colorPanel, gridConstraints);

		colorPanel.setLayout(new GridBagLayout());
		int j = 0;
		for (int i = 0; i < 18; i++) {
			colorLabel[i] = new JLabel();
			colorLabel[i].setPreferredSize(new Dimension(30, 30));
			colorLabel[i].setOpaque(true);
			colorLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			gridConstraints = new GridBagConstraints();
			gridConstraints.gridx = j;
			gridConstraints.gridy = i - j * 9;
			gridConstraints.insets = new Insets(6, 3, 0, 3);
			colorPanel.add(colorLabel[i], gridConstraints);
			
			if (i == 8) {
				j++;
			}
			colorLabel[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					colorMousePressed(e);
				}

			});

		}// end of color grids loops
		colorLabel[0].setBackground(new Color(255, 190, 190));
		colorLabel[1].setBackground(new Color(245, 45, 45));
		colorLabel[2].setBackground(new Color(110, 1, 1));
		
		colorLabel[3].setBackground(new Color(190, 255, 190));
		colorLabel[4].setBackground(new Color(45, 245, 45));
		colorLabel[5].setBackground(new Color(1, 110, 1));
		
		colorLabel[6].setBackground(new Color(190, 190, 255));
		colorLabel[7].setBackground(new Color(45, 45, 245));
		colorLabel[8].setBackground(new Color(1, 1, 110));
		
		colorLabel[9].setBackground(new Color(190, 255, 255));
		colorLabel[10].setBackground(new Color(45, 245, 245));
		colorLabel[11].setBackground(new Color(1, 110, 110));
		
		colorLabel[12].setBackground(new Color(255, 190, 255));
		colorLabel[13].setBackground(new Color(245, 45, 245));
		colorLabel[14].setBackground(new Color(110, 1, 110));
		
		colorLabel[15].setBackground(new Color(255, 255, 190));
		colorLabel[16].setBackground(new Color(245, 245, 45));
		colorLabel[17].setBackground(new Color(110, 110, 1));

		leftColor = colorLabel[3].getBackground();
		leftColorLabel.setBackground(leftColor);
		rightColor = colorLabel[1].getBackground();
		rightColorLabel.setBackground(rightColor);

		pack();
		setLocationRelativeTo(null);
		g2D = (Graphics2D) drawPanel.getGraphics();
	}// end of constructor

	// ---------------- Methods ---------------- //
	private void exitForm(WindowEvent e) {

		g2D.dispose();
		System.exit(0);
	}

	private void newMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new drawing?", "New Drawing", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.YES_OPTION) {
			g2D.setPaint(drawPanel.getBackground());
			g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
		}
	}

	private void exitMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Program",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			return;
		} else {
			exitForm(null);
		}

	}
	
	private void blackBoardActionPerformed(ActionEvent e) {
		drawPanel.setBackground(new Color(50,50,50));
		
	}
	private void yellowBoardActionPerformed(ActionEvent e) {
		drawPanel.setBackground(new Color(255,255,100));
		
	}
	private void redBoardActionPerformed(ActionEvent e) {
		drawPanel.setBackground(new Color(255,50,50));
		
	}
	
	private void penFineActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(1));
		
	}
	private void penSmallActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(2));
		
	}
	private void penMediumActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(4));
		
	}
	private void penLargeActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(8));
		
	}
	private void penXLargeActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(50));
		
	}
	
	

	private void drawPanelMousePressed(MouseEvent e) {
		//if left button or right button clicked, set color and start drawing process
		if (e.getButton() == MouseEvent.BUTTON1||e.getButton() == MouseEvent.BUTTON3) {
			xPrevious = e.getX();
			yPrevious = e.getY();
			if(e.getButton() == MouseEvent.BUTTON1) {
				drawColor = leftColor;
			}else 
			{
				drawColor = rightColor;
			}
			//System.out.println("mouse x, y = " + xPrevious + ", " + yPrevious);
		}
	}// end of MousePressed

	private void drawPanelMouseDragged(MouseEvent e) {
		// if drawing, connect previous point with new point 
		Line2D.Double myLine = new Line2D.Double(xPrevious-7, yPrevious-3,e.getX()-7,e.getY()-3);
		g2D.setPaint(drawColor);
		g2D.draw(myLine);
		xPrevious = e.getX();
		yPrevious = e.getY();
		//System.out.println("mouse x, y = " + xPrevious + ", " + yPrevious);
	}

	private void drawPanelMouseReleased(MouseEvent e) {
		if(e.getButton()== MouseEvent.BUTTON1 || e.getButton()== MouseEvent.BUTTON3) {
			Line2D.Double myLine = new Line2D.Double(xPrevious-7, yPrevious-3, e.getX()-7, e.getY()-3);
			g2D.setPaint(drawColor);
			g2D.draw(myLine);
		}
	}//end of MouseReleased
	
	private void colorMousePressed(MouseEvent e) {
		//decide which color was selected and which button was used
		Component clickedColor = e.getComponent();
		//make audible tone and set drawing color
		Toolkit.getDefaultToolkit().beep();
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftColor = clickedColor.getBackground();
			leftColorLabel.setBackground(leftColor);
		}else if(e.getButton() == MouseEvent.BUTTON3) {
			rightColor = clickedColor.getBackground();
			rightColorLabel.setBackground(rightColor);
		}
	}// end of colorMousePressed
}// end of class MouseDraw
