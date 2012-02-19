package me.sheimi.lang;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import me.sheimi.view.LangObserver;

public class LangManager implements LangSubject {

	public final static String[] LOCALES = {"English", "Chinese"};
	public final static Map<String, Locale> LOCALE_MAP = new HashMap<String, Locale>();
	
	static {
		LOCALE_MAP.put(LOCALES[0], Locale.ENGLISH);
		LOCALE_MAP.put(LOCALES[1], Locale.CHINESE);
	};

	// private variable
	private List<LangObserver> observers = new LinkedList<LangObserver>();
	private Locale lang = Locale.ENGLISH;
	private ResourceBundle resourceBundle;

	private static LangManager m_langManager = new LangManager();

	private LangManager() {
		resourceBundle = ResourceBundle.getBundle("me.sheimi.lang.edit", lang);
	}

	public static LangManager instance() {
		return m_langManager;
	}
	/**
	 * tell every observer text has changed
	 */
	@Override
	public void change() {
		// TODO Auto-generated method stub
		for (LangObserver o : observers) {
			o.changeLang();
		}
	}

	/**
	 * register observer
	 */
	@Override
	public void register(LangObserver observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	/**
	 * get text from properties file
	 */
	@Override
	public String getText(String query) {
		return resourceBundle.getString(query);
	}

	public void setLang(Locale lang) {
		if (this.lang == lang)
			return;
		this.lang = lang;
		resourceBundle = ResourceBundle.getBundle("me.sheimi.lang.edit", lang);
		//lang has changed
		change();
	}

}
