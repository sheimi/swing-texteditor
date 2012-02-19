package me.sheimi.controller.actions.embeddedstyle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.actions.EditorAction;
import me.sheimi.view.TextEdit;

/**
 * a abstract class of embedded style
 * @author sheimi
 *
 */
public abstract class EmbededStyleAction extends EditorAction {

	protected List<Action> actions = new LinkedList<Action>();
	
	public EmbededStyleAction(String styleName, TextEdit textEdit) {
		super(styleName, textEdit);
		actions.add(new StyledEditorKit.FontFamilyAction(null, getFontFamily()));
		actions.add(new StyledEditorKit.FontSizeAction(null, getFontSize()));
		actions.add(new StyledEditorKit.ForegroundAction(null, getColor()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Action action : actions) {
			action.actionPerformed(e);
		}
	}

	/**
	 * add other actions
	 * 
	 */
	
	protected void addOtherActions() {
	}
	/**
	 * get the font family of the 
	 * @return
	 */
	protected abstract String getFontFamily();
	
	/**
	 * get the font size 
	 * @return font size
	 */
	protected abstract int getFontSize();
	
	/**
	 * get the color of foreground
	 * @return a color
	 */
	protected abstract Color getColor();
}
