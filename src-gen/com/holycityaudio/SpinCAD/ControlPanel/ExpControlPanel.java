/* SpinCAD Designer - DSP Development Tool for the Spin FV-1 
 * ExpControlPanel.java
 * Copyright (C) 2015 - Gary Worsham 
 * Based on ElmGen by Andrew Kilpatrick 
 * 
 *   This program is free software: you can redistribute it and/or modify 
 *   it under the terms of the GNU General Public License as published by 
 *   the Free Software Foundation, either version 3 of the License, or 
 *   (at your option) any later version. 
 * 
 *   This program is distributed in the hope that it will be useful, 
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *   GNU General Public License for more details. 
 * 
 *   You should have received a copy of the GNU General Public License 
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 *     
 */ 
		package com.holycityaudio.SpinCAD.ControlPanel;
		import javax.swing.JFrame;
		import javax.swing.SwingUtilities;
		import javax.swing.event.ChangeEvent;
		import javax.swing.event.ChangeListener;
		import java.awt.event.ActionEvent;
		import java.awt.event.WindowEvent;
		import java.awt.event.WindowListener;
		import java.awt.event.ItemEvent;
		import javax.swing.BoxLayout;
		import javax.swing.JSlider;
		import javax.swing.JLabel;
		import javax.swing.JCheckBox;
		import javax.swing.JComboBox;
		import javax.swing.Box;
		import java.awt.Dimension;
		import com.holycityaudio.SpinCAD.spinCADControlPanel;
		import com.holycityaudio.SpinCAD.CADBlocks.ExpCADBlock;

		public class ExpControlPanel extends spinCADControlPanel {
		private JFrame frame;

		private ExpCADBlock gCB;
		// declare the controls
			JSlider multiplierSlider;
			JLabel  multiplierLabel;	

		public ExpControlPanel(ExpCADBlock genericCADBlock) {
		
		gCB = genericCADBlock;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				frame = new JFrame();
				frame.setTitle("Exp");
				frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

			
			multiplierSlider = new JSlider(JSlider.HORIZONTAL, (int)(0.001 * 1000.0),(int) (0.99999 * 1000.0), (int) (gCB.getmultiplier() * 1000.0));
				multiplierSlider.addChangeListener(new ExpSliderListener());
				multiplierLabel = new JLabel();
				updatemultiplierLabel();
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.getContentPane().add(multiplierLabel);
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.getContentPane().add(multiplierSlider);		
				frame.addWindowListener(new MyWindowListener());
				frame.setVisible(true);		
				frame.pack();
				frame.setResizable(false);
				frame.setLocation(gCB.getX() + 100, gCB.getY() + 100);
				frame.setAlwaysOnTop(true);
			}
		});
		}

		// add change listener for Sliders 
		class ExpSliderListener implements ChangeListener { 
		public void stateChanged(ChangeEvent ce) {
			if(ce.getSource() == multiplierSlider) {
				gCB.setmultiplier((double) (multiplierSlider.getValue()/1000.0));
				updatemultiplierLabel();
			}
			}
		}

		// add item listener 
		class ExpItemListener implements java.awt.event.ItemListener { 
		public void stateChanged(ChangeEvent ce) {
			}
			
		@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
			}
		}
		
		// add action listener 
		class ExpActionListener implements java.awt.event.ActionListener { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		}
		private void updatemultiplierLabel() {
		multiplierLabel.setText("Exp_Multiplier " + String.format("%4.3f", gCB.getmultiplier()));		
		}		
		
		class MyWindowListener implements WindowListener
		{
		@Override
			public void windowActivated(WindowEvent arg0) {
			}

		@Override
			public void windowClosed(WindowEvent arg0) {
			}

		@Override
			public void windowClosing(WindowEvent arg0) {
				gCB.clearCP();
			}

		@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}

		@Override
		public void windowIconified(WindowEvent arg0) {

		}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		}
		
	}
