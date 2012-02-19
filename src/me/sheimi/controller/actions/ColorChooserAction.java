package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * open the Color Chooser
 * @author sheimi
 *
 */
public class ColorChooserAction extends EditorAction {

	boolean show = false;
	
	public ColorChooserAction(TextEdit textEdit) {
		super(EditorActionManager.COLOR_CHOOSER_ACTION,
				EditorActionManager.COLOR_CHOOSER_ICON, textEdit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (show) {
			m_textEdit.hideColorChooser();
		} else {
			m_textEdit.showColorChooser();
		}
		show = !show;
	}
}
