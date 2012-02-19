package me.sheimi.view.support;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * to make font in combobox visible
 * @author sheimi
 *
 */
public class FontActionCellRenderer extends JLabel implements
		ListCellRenderer {
	public FontActionCellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value != null) {
			setMinimumSize(new Dimension(0, 16));
			Font thisFont = new Font((String) value, Font.PLAIN, 12);
			setFont(thisFont);
			setText((String) value);

			// setIcon((ImageIcon)a.getValue(Action.SMALL_ICON));
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
}