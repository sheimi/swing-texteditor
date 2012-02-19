package me.sheimi.helper;

import javax.swing.JOptionPane;

public class FileChooserHelpHandler extends HelpHandler {

	private final String NAME = "FIleChooserHelp";

	public FileChooserHelpHandler(HelpHandler h) {
		super(h);
	}

	@Override
	public void HandlerHelp(HelpRequest r) {
		// TODO Auto-generated method stub
		if (r instanceof FileChooserHelpRequest) {
			JOptionPane.showMessageDialog(null, m_lmg.getText(NAME),
					m_lmg.getText(NAME), 0);
		} else {
			m_helpHandle.HandlerHelp(r);
		}
	}

}
