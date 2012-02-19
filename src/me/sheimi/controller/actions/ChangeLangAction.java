package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;
import java.util.Locale;

import me.sheimi.lang.LangManager;
import me.sheimi.view.TextEdit;

/**
 * change the language of the interface
 * @author sheimi
 *
 */
public class ChangeLangAction extends EditorAction {
	LangManager lm = LangManager.instance();
	Locale locale;
	
	public ChangeLangAction(String locale) {
		super(locale, null);
		this.locale = LangManager.LOCALE_MAP.get(locale);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lm.setLang(locale);
	}
	

}
