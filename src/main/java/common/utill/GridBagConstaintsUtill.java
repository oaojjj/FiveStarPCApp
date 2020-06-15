package main.java.common.utill;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GridBagConstaintsUtill {
	public GridBagConstraints gbc;
	JPanel jPanel;
	Insets insets;

	public GridBagConstaintsUtill(GridBagConstraints g, JPanel j, Insets insets) {
		gbc = g;
		jPanel = j;
		this.insets = insets;
	}

	public void gbAdd(JComponent c, int x, int y, int w, int h, double wx) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.weightx = wx;
		gbc.insets = insets;
		jPanel.add(c, gbc);
	}

}
