package me.sheimi.view.support;

import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ActionToolBar extends JToolBar {
	/*
	 * registry of listeners created for Action-JButton linkage. This is needed
	 * so that references can be cleaned up at remove time to allow GC.
	 */
	private static Hashtable s_listenerRegistry = null;

	/**
	 * Add a new JButton which dispatches the action.
	 * 
	 * @param action
	 *            the Action object to add as a new menu item
	 */
	public JButton add(Action action) {
		JButton button = new JButton((String) action.getValue(Action.NAME),
				(Icon) action.getValue(Action.SMALL_ICON));
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setEnabled(action.isEnabled());
		button.addActionListener(action);
		button.setBorder(null);
		add(button);
		return button;
	}

	/**
	 * Add a new JButton which dispatches the action.
	 * 
	 * @param action
	 *            the Action object to add as a new menu item
	 * @param showText
	 *            true if the button should show the action text.
	 */
	public JButton add(boolean showText, Action action) {
		JButton button = showText ? new JButton(
				(String) action.getValue(Action.NAME),
				(Icon) action.getValue(Action.SMALL_ICON)) : new JButton(
				(Icon) action.getValue(Action.SMALL_ICON));
		if (showText) {
			button.setHorizontalTextPosition(JButton.CENTER);
			button.setVerticalTextPosition(JButton.BOTTOM);
		} else {
			button.setMargin(new Insets(0, 0, 0, 0));
		}
		button.setEnabled(action.isEnabled());
		button.addActionListener(action);
		add(button);
		return button;
	}
}
