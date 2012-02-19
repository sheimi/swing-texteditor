package me.sheimi.controller.actions;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.text.StyledEditorKit.FontFamilyAction;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * to choose a font
 * @author sheimi
 *
 */
public class FontChooserAction extends AbstractAction {

	FontFamilyAction m_fontFamilyAction;
	TextEdit m_textEdit;
	Font m_font;
	
	public FontChooserAction(Font font, TextEdit textEdit) {
		super(font.getFontName());
		this.m_textEdit = textEdit;
		this.m_font = font;
		this.m_fontFamilyAction = new FontFamilyAction(font.getName(), font.getFamily());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m_fontFamilyAction.actionPerformed(e);
	}

}
