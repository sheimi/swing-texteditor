package me.sheimi.controller.actions;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import me.sheimi.img.ImageManager;
import me.sheimi.lang.LangManager;
import me.sheimi.lang.LangSubject;
import me.sheimi.view.LangObserver;
import me.sheimi.view.TextEdit;

/**
 * an abstract class of all the action
 * @author sheimi
 *
 */
public abstract class EditorAction extends AbstractAction implements LangObserver{

	protected LangSubject m_ls = LangManager.instance();
	protected String m_actionName;
	protected TextEdit m_textEdit;
	
	public EditorAction() {
	}
	
	public EditorAction(String actionName, String actionICON, TextEdit textEdit) {
		this(actionName, textEdit);
		putValue(Action.SMALL_ICON,
				new ImageIcon(ImageManager.load(actionICON)));
	}
	
	public EditorAction(String actionName, TextEdit textEdit) {
		this.m_actionName = actionName;
		this.m_textEdit = textEdit;
		putValue(Action.NAME, m_ls.getText(actionName));
		m_ls.register(this);
	}

	@Override
	public void changeLang() {
		putValue(Action.NAME, m_ls.getText(m_actionName));
	}
}
