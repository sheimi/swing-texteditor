package me.sheimi.lang;

import me.sheimi.view.LangObserver;

public interface LangSubject {
	public void change();
	public void register(LangObserver observer);
	public String getText(String query);
}
