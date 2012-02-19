package me.sheimi.helper;

import me.sheimi.lang.LangManager;

public abstract class HelpHandler {
	
	protected HelpHandler m_helpHandle;
	protected LangManager m_lmg = LangManager.instance();
	
	public HelpHandler(HelpHandler h) {
		m_helpHandle = h;
	}
	
	public abstract void HandlerHelp(HelpRequest r);
}
