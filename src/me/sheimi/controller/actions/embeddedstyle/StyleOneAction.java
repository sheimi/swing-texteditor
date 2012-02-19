package me.sheimi.controller.actions.embeddedstyle;

import java.awt.Color;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * style one
 * @author sheimi
 *
 */
public class StyleOneAction extends EmbededStyleAction{

	public StyleOneAction(TextEdit textEdit) {
		super(EditorActionManager.EMBEDDED_STYLE[0], textEdit);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getFontFamily() {
		return "Serif";
	}

	@Override
	protected int getFontSize() {
		return 50;
	}

	@Override
	protected Color getColor() {
		return Color.YELLOW;
	}

}
