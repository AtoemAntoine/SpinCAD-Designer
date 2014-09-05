/* SpinCAD Designer - DSP Development Tool for the Spin FV-1
 * Copyright (C) 2013 - 2014 - Gary Worsham
 * Based on ElmGen by Andrew Kilpatrick.  Modified by Gary Worsham 2013 - 2014.  Look for GSW in code.
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


package com.holycityaudio.SpinCAD.CADBlocks;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
class PhaserControlPanel implements ChangeListener, ActionListener {
	JSlider stagesSlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 4);
	JLabel stagesLabel = new JLabel();
	
	private JLabel controlTypeLabel = new JLabel("Control Type");
	private JComboBox<String> controlType;
	private String listOptions[] = {
			"Internal LFO",
			"Single",
			"Individual"
	};


	private JFrame frame;

	private PhaserCADBlock pong;

	public PhaserControlPanel(PhaserCADBlock swoosh) {

		stagesSlider.addChangeListener(this);
		controlType = new JComboBox<String>(listOptions);
		controlType.addActionListener(this);

		pong = swoosh;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame("Ramp LFO");
				frame.setTitle("Phaser");
				frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
				frame.setResizable(false);

				stagesSlider.setMajorTickSpacing(1);
				stagesSlider.setPaintTicks(true);

				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.add(stagesLabel);
				updateFreqLabel();
				frame.add(stagesSlider);
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
			
				frame.add(controlTypeLabel);
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.add(controlType);
				frame.add(Box.createRigidArea(new Dimension(5,4)));			

				stagesSlider.setValue((pong.getStages()));

				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
				frame.pack();
				frame.setLocation(new Point(pong.getX() + 200, pong.getY() + 150));			}
		});
	}

	public void stateChanged(ChangeEvent ce) {
		if(ce.getSource() == stagesSlider) {
			pong.setStages(stagesSlider.getValue());
			updateFreqLabel();
		}
	}

	public void updateFreqLabel() {
		stagesLabel.setText("Stages " + String.format("%d", 2 * pong.getStages()));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}