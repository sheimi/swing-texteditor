package me.sheimi.helper;

import javax.swing.JOptionPane;

public class TextPaneHelpHandler extends HelpHandler {

	private final String NAME = "TextPaneHelp";

	public TextPaneHelpHandler(HelpHandler h) {
		super(h);
	}

	@Override
	public void HandlerHelp(HelpRequest r) {
		// TODO Auto-generated method stub
		if (r instanceof TextPaneHelpRequest) {
			JOptionPane.showMessageDialog(null, m_lmg.getText(NAME),
					m_lmg.getText(NAME), 0);
		} else {
			m_helpHandle.HandlerHelp(r);
		}
	}
}
