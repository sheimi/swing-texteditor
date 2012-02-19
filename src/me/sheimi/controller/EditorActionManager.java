package me.sheimi.controller;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import me.sheimi.controller.actions.AlignmentCenterAction;
import me.sheimi.controller.actions.AlignmentLeftAction;
import me.sheimi.controller.actions.AlignmentRightAction;
import me.sheimi.controller.actions.BoldAction;
import me.sheimi.controller.actions.ChangeLangAction;
import me.sheimi.controller.actions.ColorChooserAction;
import me.sheimi.controller.actions.CopyAction;
import me.sheimi.controller.actions.CutAction;
import me.sheimi.controller.actions.FontChooserAction;
import me.sheimi.controller.actions.FontSizeAction;
import me.sheimi.controller.actions.GetHelp;
import me.sheimi.controller.actions.InsertImageAction;
import me.sheimi.controller.actions.ItalicAction;
import me.sheimi.controller.actions.NewFileAction;
import me.sheimi.controller.actions.OpenFileAction;
import me.sheimi.controller.actions.PasteAction;
import me.sheimi.controller.actions.QuitAction;
import me.sheimi.controller.actions.ReDoAction;
import me.sheimi.controller.actions.SaveFileAction;
import me.sheimi.controller.actions.UnDoAction;
import me.sheimi.controller.actions.UnderlineAction;
import me.sheimi.controller.actions.embeddedstyle.StyleOneAction;
import me.sheimi.controller.actions.embeddedstyle.StyleTwoAction;
import me.sheimi.lang.LangManager;
import me.sheimi.view.TextEdit;

public class EditorActionManager {

	//singleton
	private static EditorActionManager m_actionManager;

	private static TextEdit m_textEdit;

	private UndoManager undo = new UndoManager();

	// symbols of actions
	public static final String New_FILE_ACTION = "NewFile";
	public static final String OPEN_FILE_ACTION = "OpenFile";
	public static final String SAVE_FILE_ACTION = "SaveFile";
	public static final String UNDO_ACTION = "UnDo";
	public static final String REDO_ACTION = "ReDo";
	public static final String CUT_ACTION = "Cut";
	public static final String COPY_ACTION = "Copy";
	public static final String PASTE_ACTION = "Paste";
	public static final String BOLD_ACTION = "Bold";
	public static final String ITALIC_ACTION = "Italic";
	public static final String UNDERLINE_ACTION = "Underline";
	public static final String QUIT_ACTION = "Quit";
	public static final String FONT_CHOOSER_ACTION = "FontChooser";
	public static final String COLOR_CHOOSER_ACTION = "ColorChooser";
	public static final String FONT_SIZE_ACTION = "FontSize";
	public static final String INSERT_IMAGE_ACTION = "InsertImage";
	public static final String[] EMBEDDED_STYLE = {"StyleOne", "StyleTwo"};
	public static final String ALIGNMENT_LEFT_ACTION = "AlignmentLeft";
	public static final String ALIGNMENT_RIGHT_ACTION = "AlignmentRight";
	public static final String ALIGNMENT_CENTER_ACTION = "AlignmentCenter";
	public static final String GET_HELP = "GetHelp";

	// icon_name of action
	public static final String New_FILE_ICON = "filenew.png";
	public static final String OPEN_FILE_ICON = "fileopen.png";
	public static final String SAVE_FILE_ICON = "filesave.png";
	public static final String UNDO_ICON = "editundo.png";
	public static final String REDO_ICON = "editredo.png";
	public static final String CUT_ICON = "editcut.png";
	public static final String COPY_ICON = "editcopy.png";
	public static final String PASTE_ICON = "editpaste.png";
	public static final String QUIT_ICON = "quit.png";
	public static final String COLOR_CHOOSER_ICON = "color.png";
	public static final String BOLD_ICON = "textbold.png";
	public static final String ITALIC_ICON = "textitalic.png";
	public static final String UNDERLINE_ICON = "textunder.png";
	public static final String INSERT_IMAGE_ICON = "insertimg.png";
	public static final String ALIGNMENT_LEFT_ICON = "textleft.png";
	public static final String ALIGNMENT_RIGHT_ICON = "textright.png";
	public static final String ALIGNMENT_CENTER_ICON = "textcenter.png";

	// a map of actions
	private Map<String, Action> m_actionMap = Collections
			.synchronizedMap(new HashMap<String, Action>());
	
	//some other static variable
	public final static int[] SUPPORT_FONT_SIZE = { 10, 20, 30, 40, 50, 60, 70 };
	public final static int ALIGN_LEFT = 0;
	public final static int ALIGN_RIGHT = 2;
	public final static int ALIGN_CENTER = 1;

	private UEL uel = new UEL();

	private EditorActionManager(TextEdit textEdit) {
		
		//init the table(Map)
		m_actionMap.put(OPEN_FILE_ACTION, new OpenFileAction(textEdit));
		m_actionMap.put(New_FILE_ACTION, new NewFileAction(textEdit));
		m_actionMap.put(SAVE_FILE_ACTION, new SaveFileAction(textEdit));
		m_actionMap.put(UNDO_ACTION, new UnDoAction(textEdit, undo));
		m_actionMap.put(REDO_ACTION, new ReDoAction(textEdit, undo));
		m_actionMap.put(CUT_ACTION, new CutAction(textEdit));
		m_actionMap.put(COPY_ACTION, new CopyAction(textEdit));
		m_actionMap.put(PASTE_ACTION, new PasteAction(textEdit));
		m_actionMap.put(BOLD_ACTION, new BoldAction(textEdit));
		m_actionMap.put(ITALIC_ACTION, new ItalicAction(textEdit));
		m_actionMap.put(UNDERLINE_ACTION, new UnderlineAction(textEdit));
		m_actionMap.put(QUIT_ACTION, new QuitAction(textEdit));
		m_actionMap.put(COLOR_CHOOSER_ACTION, new ColorChooserAction(textEdit));
		m_actionMap.put(EMBEDDED_STYLE[0], new StyleOneAction(textEdit));
		m_actionMap.put(EMBEDDED_STYLE[1], new StyleTwoAction(textEdit));
		m_actionMap.put(INSERT_IMAGE_ACTION, new InsertImageAction(textEdit));
		m_actionMap.put(ALIGNMENT_LEFT_ACTION, new AlignmentLeftAction(textEdit));
		m_actionMap.put(ALIGNMENT_RIGHT_ACTION, new AlignmentRightAction(textEdit));
		m_actionMap.put(ALIGNMENT_CENTER_ACTION, new AlignmentCenterAction(textEdit));
		m_actionMap.put(GET_HELP, new GetHelp(textEdit));
		
		for (String l : LangManager.LOCALES) {
			m_actionMap.put(l, new ChangeLangAction(l));
		}

		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts();
		for (Font font : fonts) {
			m_actionMap.put(font.getFontName(), new FontChooserAction(font,
					textEdit));
		}

		for (int size : SUPPORT_FONT_SIZE) {
			m_actionMap.put(FONT_SIZE_ACTION + "_" + size, new FontSizeAction(
					size, textEdit));
		}
		//===========init END==========
		
		//init undo manager
		this.m_textEdit = textEdit;
		JTextPane textPane = textEdit.getTextPane();
		Document doc = textPane.getDocument();
		this.initDocumentListener(doc);
	}
	
	public void initDocumentListener(Document doc) {
		doc.addUndoableEditListener(uel);
	}
	
	public void removeDocumentListener(Document doc) {
		doc.removeUndoableEditListener(uel);
	}


	/**
	 * get a action manager
	 * 
	 * @param textPane
	 * @return
	 * @throws TextEditNotMatchException
	 */
	public static EditorActionManager instance(TextEdit textEdit)
			throws TextEditNotMatchException {
		if (m_actionManager == null) {
			m_actionManager = new EditorActionManager(textEdit);
		}
		if (textEdit != m_actionManager.m_textEdit) {
			throw new TextEditNotMatchException();
		}
		return m_actionManager;
	}

	/**
	 * provide a way to get Action
	 * @param action
	 * @return
	 */
	public Action createAction(String action) {
		return m_actionMap.get(action);
	}
	
	private class UEL implements UndoableEditListener {
		public void undoableEditHappened(UndoableEditEvent e) {
			// Remember the edit and update the menus.
			m_actionManager.undo.addEdit(e.getEdit());
		}
	}
	
}
