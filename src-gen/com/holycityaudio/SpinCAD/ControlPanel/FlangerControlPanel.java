/* SpinCAD Designer - DSP Development Tool for the Spin FV-1 
 * FlangerControlPanel.java
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
import com.holycityaudio.SpinCAD.CADBlocks.FlangerCADBlock;

@SuppressWarnings("unused")
public class FlangerControlPanel extends spinCADControlPanel {
	private JFrame frame;
	private FlangerCADBlock gCB;
	// declare the controls
	JSlider inputGainSlider;
	JLabel  inputGainLabel;	
	JSlider fbkGainSlider;
	JLabel  fbkGainLabel;	
	JSlider delayLengthSlider;
	JLabel  delayLengthLabel;	
	JSlider rateSlider;
	JLabel  rateLabel;	
	JSlider widthSlider;
	JLabel  widthLabel;	
	private JComboBox <String> lfoSelComboBox; 

public FlangerControlPanel(FlangerCADBlock genericCADBlock) {
		
		gCB = genericCADBlock;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				frame = new JFrame();
				frame.setTitle("Flanger");
				frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

			//
			// these functions translate between slider values, which have to be integers, to whatever in program value you wish.
			//
					// dB level slider goes in steps of 1 dB
						inputGainSlider = new JSlider(JSlider.HORIZONTAL, (int)(-24),(int) (0), (int) (20 * Math.log10(gCB.getinputGain())));
						inputGainSlider.addChangeListener(new FlangerListener());
						inputGainLabel = new JLabel();
						Border inputGainBorder1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
						inputGainLabel.setBorder(inputGainBorder1);
						updateinputGainLabel();
						
						Border inputGainborder2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
						JPanel inputGaininnerPanel = new JPanel();
							
						inputGaininnerPanel.setLayout(new BoxLayout(inputGaininnerPanel, BoxLayout.Y_AXIS));
						inputGaininnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						inputGaininnerPanel.add(inputGainLabel);
						inputGaininnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						inputGaininnerPanel.add(inputGainSlider);		
						inputGaininnerPanel.setBorder(inputGainborder2);
			
						frame.add(inputGaininnerPanel);
			//
			// these functions translate between slider values, which have to be integers, to whatever in program value you wish.
			//
					// dB level slider goes in steps of 1 dB
						fbkGainSlider = new JSlider(JSlider.HORIZONTAL, (int)(-24),(int) (0), (int) (20 * Math.log10(gCB.getfbkGain())));
						fbkGainSlider.addChangeListener(new FlangerListener());
						fbkGainLabel = new JLabel();
						Border fbkGainBorder1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
						fbkGainLabel.setBorder(fbkGainBorder1);
						updatefbkGainLabel();
						
						Border fbkGainborder2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
						JPanel fbkGaininnerPanel = new JPanel();
							
						fbkGaininnerPanel.setLayout(new BoxLayout(fbkGaininnerPanel, BoxLayout.Y_AXIS));
						fbkGaininnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						fbkGaininnerPanel.add(fbkGainLabel);
						fbkGaininnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						fbkGaininnerPanel.add(fbkGainSlider);		
						fbkGaininnerPanel.setBorder(fbkGainborder2);
			
						frame.add(fbkGaininnerPanel);
			//
			// these functions translate between slider values, which have to be integers, to whatever in program value you wish.
			//
					delayLengthSlider = new JSlider(JSlider.HORIZONTAL, (int)(16 * 1),(int) (512 * 1), (int) (gCB.getdelayLength() * 1));
						delayLengthSlider.addChangeListener(new FlangerListener());
						delayLengthLabel = new JLabel();
						Border delayLengthBorder1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
						delayLengthLabel.setBorder(delayLengthBorder1);
						updatedelayLengthLabel();
						
						Border delayLengthborder2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
						JPanel delayLengthinnerPanel = new JPanel();
							
						delayLengthinnerPanel.setLayout(new BoxLayout(delayLengthinnerPanel, BoxLayout.Y_AXIS));
						delayLengthinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						delayLengthinnerPanel.add(delayLengthLabel);
						delayLengthinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						delayLengthinnerPanel.add(delayLengthSlider);		
						delayLengthinnerPanel.setBorder(delayLengthborder2);
			
						frame.add(delayLengthinnerPanel);
			//
			// these functions translate between slider values, which have to be integers, to whatever in program value you wish.
			//
					rateSlider = new JSlider(JSlider.HORIZONTAL, (int)(0.0 * 100.0),(int) (511.0 * 100.0), (int) ((gCB.getrate()) * 100.0));
						rateSlider.addChangeListener(new FlangerListener());
						rateLabel = new JLabel();
						Border rateBorder1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
						rateLabel.setBorder(rateBorder1);
						updaterateLabel();
						
						Border rateborder2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
						JPanel rateinnerPanel = new JPanel();
							
						rateinnerPanel.setLayout(new BoxLayout(rateinnerPanel, BoxLayout.Y_AXIS));
						rateinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						rateinnerPanel.add(rateLabel);
						rateinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						rateinnerPanel.add(rateSlider);		
						rateinnerPanel.setBorder(rateborder2);
			
						frame.add(rateinnerPanel);
			//
			// these functions translate between slider values, which have to be integers, to whatever in program value you wish.
			//
					widthSlider = new JSlider(JSlider.HORIZONTAL, (int)(5.0 * 100.0),(int) (100.0 * 100.0), (int) (gCB.getwidth() * 100.0));
						widthSlider.addChangeListener(new FlangerListener());
						widthLabel = new JLabel();
						Border widthBorder1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
						widthLabel.setBorder(widthBorder1);
						updatewidthLabel();
						
						Border widthborder2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
						JPanel widthinnerPanel = new JPanel();
							
						widthinnerPanel.setLayout(new BoxLayout(widthinnerPanel, BoxLayout.Y_AXIS));
						widthinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						widthinnerPanel.add(widthLabel);
						widthinnerPanel.add(Box.createRigidArea(new Dimension(5,4)));			
						widthinnerPanel.add(widthSlider);		
						widthinnerPanel.setBorder(widthborder2);
			
						frame.add(widthinnerPanel);
				lfoSelComboBox = new JComboBox <String> ();
				lfoSelComboBox.addItem("LFO 0");
				lfoSelComboBox.addItem("LFO 1");
				lfoSelComboBox.setSelectedIndex(gCB.getlfoSel());
				frame.add(Box.createRigidArea(new Dimension(5,8)));			
				frame.getContentPane().add(lfoSelComboBox);
				lfoSelComboBox.addActionListener(new FlangerActionListener());
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
		class FlangerListener implements ChangeListener { 
		public void stateChanged(ChangeEvent ce) {
			if(ce.getSource() == inputGainSlider) {
			gCB.setinputGain((double) (inputGainSlider.getValue()/1.0));			    					
				updateinputGainLabel();
			}
			if(ce.getSource() == fbkGainSlider) {
			gCB.setfbkGain((double) (fbkGainSlider.getValue()/1.0));			    					
				updatefbkGainLabel();
			}
			if(ce.getSource() == delayLengthSlider) {
			gCB.setdelayLength((double) (delayLengthSlider.getValue()/1));			    					
				updatedelayLengthLabel();
			}
			if(ce.getSource() == rateSlider) {
			gCB.setrate((double) (rateSlider.getValue()/100.0));			    					
				updaterateLabel();
			}
			if(ce.getSource() == widthSlider) {
			gCB.setwidth((double) (widthSlider.getValue()/100.0));
				updatewidthLabel();
			}
			}
		}

		// add item state changed listener for Checkbox
		class FlangerItemListener implements java.awt.event.ItemListener { 
			
		@Override
			public void itemStateChanged(ItemEvent arg0) {
			}
		}
		
		// add action listener for Combo Box
		class FlangerActionListener implements java.awt.event.ActionListener { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == lfoSelComboBox) {
				gCB.setlfoSel((lfoSelComboBox.getSelectedIndex()));
			}
			}
		}
		private void updateinputGainLabel() {
		inputGainLabel.setText("Input Gain " + String.format("%4.1f dB", (20 * Math.log10(gCB.getinputGain()))));		
		}		
		private void updatefbkGainLabel() {
		fbkGainLabel.setText("Feedback Gain " + String.format("%4.1f dB", (20 * Math.log10(gCB.getfbkGain()))));		
		}		
		private void updatedelayLengthLabel() {
		delayLengthLabel.setText("Delay Time " + String.format("%4.2f", (1000 * gCB.getdelayLength())/ElmProgram.getSamplerate()));		
		}		
		private void updaterateLabel() {
		rateLabel.setText("LFO Rate " + String.format("%4.2f", coeffToLFORate(gCB.getrate())));		
		}		
		private void updatewidthLabel() {
		widthLabel.setText("LFO Width " + String.format("%4.1f", gCB.getwidth()));		
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
