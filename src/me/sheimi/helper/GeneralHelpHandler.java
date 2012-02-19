package me.sheimi.helper;

import javax.swing.JOptionPane;

public class GeneralHelpHandler extends HelpHandler {
	
	private final String NAME = "DefaultHelp";

	public GeneralHelpHandler() {
		super(null);
	}

	@Override
	public void HandlerHelp(HelpRequest r) {
		JOptionPane.showMessageDialog(null, m_lmg.getText(NAME),
				m_lmg.getText(NAME), 0);
	}

}
