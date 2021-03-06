/* SpinCAD Designer - DSP Development Tool for the Spin FV-1 
 * Shelving_HipassControlPanel.java
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

import org.andrewkilpatrick.elmGen.ElmProgram;
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
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.text.DecimalFormat;
import com.holycityaudio.SpinCAD.SpinCADBlock;
import com.holycityaudio.SpinCAD.spinCADControlPanel;
import com.holycityaudio.SpinCAD.CADBlocks.Shelving_HipassCADBlock;

@SuppressWarnings("unused")
public class Shelving_HipassControlPanel extends spinCADControlPanel {
	private JFrame frame;
	private Shelving_HipassCADBlock gCB;
	// declare the controls
	JSlider freqSlider;
	JLabel  freqLabel;	
	JSlider shelfSlider;
	JLabel  shelfLabel;	

public Shelving_HipassControlPanel(Shelving_HipassCADBlock genericCADBlock) {
		
		gCB = genericCADBlock;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				frame = new JFrame();
				frame.setTitle("Shelving Hipass");
				frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

			//
			// these functions translate between slider values, which have to be integers, to whatever in program value you wish.
			//
					// multiplier is points per decade here
						freqSlider = SpinCADBlock.LogSlider(80,2500,gCB.getfreq(), "LOGFREQ", 100.0);
						freqSlider.addChangeListener(new Shelving_HipassListener());
						freqLabel = new JLabel();
						Border freqBorder1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
						freqLabel.setBorder(freqBorder1);
						updatefreqLabel();
						
						Border freqborder2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
						JPanel freqinnerPanel = new JPanel();
							
						freqinnerPanel.setLayout(new BoxLayout(freqinnerPanel, BoxLayout.Y_AXIS));
						freqinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						freqinnerPanel.add(freqLabel);
						freqinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						freqinnerPanel.add(freqSlider);		
						freqinnerPanel.setBorder(freqborder2);
			
						frame.add(freqinnerPanel);
			//
			// these functions translate between slider values, which have to be integers, to whatever in program value you wish.
			//
					// dB level slider goes in steps of 1 dB
						shelfSlider = new JSlider(JSlider.HORIZONTAL, (int)(-40),(int) (-3), (int) (20 * Math.log10(gCB.getshelf())));
						shelfSlider.addChangeListener(new Shelving_HipassListener());
						shelfLabel = new JLabel();
						Border shelfBorder1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
						shelfLabel.setBorder(shelfBorder1);
						updateshelfLabel();
						
						Border shelfborder2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
						JPanel shelfinnerPanel = new JPanel();
							
						shelfinnerPanel.setLayout(new BoxLayout(shelfinnerPanel, BoxLayout.Y_AXIS));
						shelfinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						shelfinnerPanel.add(shelfLabel);
						shelfinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						shelfinnerPanel.add(shelfSlider);		
						shelfinnerPanel.setBorder(shelfborder2);
			
						frame.add(shelfinnerPanel);
				frame.addWindowListener(new MyWindowListener());
				frame.pack();
				frame.setResizable(false);
				frame.setLocation(gCB.getX() + 100, gCB.getY() + 100);
				frame.setAlwaysOnTop(true);
				frame.setVisible(true);		
			}
		});
		}

		// add change listener for Sliders, Spinners 
		class Shelving_HipassListener implements ChangeListener { 
		public void stateChanged(ChangeEvent ce) {
			if(ce.getSource() == freqSlider) {
			gCB.setfreq((double) SpinCADBlock.freqToFilt(SpinCADBlock.sliderToLogval((int)(freqSlider.getValue()), 100.0)));
				updatefreqLabel();
			}
			if(ce.getSource() == shelfSlider) {
			gCB.setshelf((double) (shelfSlider.getValue()/1.0));			    					
				updateshelfLabel();
			}
			}
		}

		// add item state changed listener for Checkbox
		class Shelving_HipassItemListener implements java.awt.event.ItemListener { 
			
		@Override
			public void itemStateChanged(ItemEvent arg0) {
			}
		}
		
		// add action listener for Combo Box
		class Shelving_HipassActionListener implements java.awt.event.ActionListener { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		}
		private void updatefreqLabel() {
		freqLabel.setText("Frequency " + String.format("%4.1f", SpinCADBlock.filtToFreq(gCB.getfreq())) + " Hz");		
		}		
		private void updateshelfLabel() {
		shelfLabel.setText("Shelf Depth " + String.format("%4.1f dB", (20 * Math.log10(gCB.getshelf()))));		
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
