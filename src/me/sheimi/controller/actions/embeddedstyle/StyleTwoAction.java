package me.sheimi.controller.actions.embeddedstyle;

import java.awt.Color;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * style two
 * @author sheimi
 *
 */
public class StyleTwoAction extends EmbededStyleAction{

	public StyleTwoAction(TextEdit textEdit) {
		super(EditorActionManager.EMBEDDED_STYLE[1], textEdit);
	}

	@Override
	protected String getFontFamily() {
		return "Serif";
	}

	@Override
	protected int getFontSize() {
		return 150;
	}

	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

}