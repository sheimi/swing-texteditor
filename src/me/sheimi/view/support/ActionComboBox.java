/**
 * Copyright (C) 2005 the Lexi Project.
 *
 * This file is part of the Lexi document editor.
 *
 * Lexi is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 * 
 * Lexi is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GNU Classpath; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 */

package me.sheimi.view.support;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import javax.swing.Action;
import javax.swing.JComboBox;

/**
 * General purpose Action combo-box class.
 */
public final class ActionComboBox extends JComboBox implements ItemListener {
	private final Hashtable m_actions = new Hashtable();

	public ActionComboBox() {
		super();
		this.addItemListener(this);
	}

	public ActionComboBox(Action[] items) {
		super();
		for (int i = 0; i < items.length; i++) {
			this.addItem(items[i]);
		}
		this.addItemListener(this);
	}

	public void addItem(Action a) {
		if (a != null) {
			String name = (String) a.getValue(Action.NAME);
			if (!m_actions.containsKey(name)) {
				m_actions.put(name, a);
				super.addItem(name);
			}
		}
	}

	public Object getItemAt(int index) {
		String name = (String) super.getItemAt(index);
		if (m_actions.containsKey(name)) {
			return ((Action) m_actions.get(name));
		} else {
			return null;
		}
	}

	public void insertItemAt(Action a, int index) {
		if (a != null) {
			String name = (String) a.getValue(Action.NAME);
			if (!m_actions.containsKey(name)) {
				m_actions.put(name, a);
				super.insertItemAt(name, index);
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {
		String name = (String) e.getItem();
		Action action = (Action) m_actions.get(name);
		if (action != null) {
			ActionEvent event = new ActionEvent(this,
					ActionEvent.ACTION_PERFORMED, name);
			action.actionPerformed(event);
		}
	}

	public void removeAllItems() {
		m_actions.clear();
		super.removeAllItems();
	}

	/**
	 * Not implemented: Use removeAllItems() instead.
	 */
	public void removeItem(Object anObject) {
		throw new UnsupportedOperationException("removeItem(Object)");
	}

	/**
	 * Nor implemented: Use removeAllItems() instead.
	 */
	public void removeItemAt(int anIndex) {
		throw new UnsupportedOperationException("removeItemAt(int)");
	}
}
