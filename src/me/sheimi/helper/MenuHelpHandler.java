package me.sheimi.helper;

import javax.swing.JOptionPane;

public class MenuHelpHandler extends HelpHandler {

	private final String NAME = "MenuHelp";

	public MenuHelpHandler(HelpHandler h) {
		super(h);
	}

	@Override
	public void HandlerHelp(HelpRequest r) {
		// TODO Auto-generated method stub
		if (r instanceof MenuHelpRequest) {
			JOptionPane.showMessageDialog(null, m_lmg.getText(NAME),
					m_lmg.getText(NAME), 0);
		} else {
			m_helpHandle.HandlerHelp(r);
		}
	}
}
