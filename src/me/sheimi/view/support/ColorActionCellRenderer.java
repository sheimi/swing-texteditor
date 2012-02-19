package me.sheimi.view.support;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ColorActionCellRenderer extends JLabel implements ListCellRenderer {
	public ColorActionCellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value != null) {
			setMinimumSize(new Dimension(0, 16));
			//TODO
			Color color = Color.decode(Color.black.toString());
			setText((String) value);
			setIcon(new ColourIcon(color));
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			return this;

		} else {
			return new JLabel("VALUE IS NULL");
		}
	}

	/**
	 * Icon Renderer
	 */
	protected final class ColourIcon implements Icon, Serializable {
		private transient Color m_colour = null;
		private transient Image m_image = null;

		protected ColourIcon(Color colour) {
			super();
			m_colour = colour;
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			if (m_image == null) {
				m_image = c.createImage(getIconWidth(), getIconHeight());
				Graphics imageG = m_image.getGraphics();
				paintImage(c, imageG, m_colour);
			}
			g.drawImage(m_image, x, y, null);
		}

		private void paintImage(Component c, Graphics g, Color colour) {
			g.setColor(colour);
			g.fillRect(0, 0, getIconWidth(), getIconHeight());
			g.setColor(Color.black);
			g.drawRect(0, 0, getIconWidth() - 1, getIconHeight() - 1);
		}

		public int getIconWidth() {
			return 16;
		}

		public int getIconHeight() {
			return 16;
		}
	}
}