package me.sheimi.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.controller.TextEditNotMatchException;
import me.sheimi.helper.FileChooserHelpHandler;
import me.sheimi.helper.FileChooserHelpRequest;
import me.sheimi.helper.GeneralHelpHandler;
import me.sheimi.helper.GeneralHelpRequest;
import me.sheimi.helper.HelpHandler;
import me.sheimi.helper.HelpRequest;
import me.sheimi.helper.MenuHelpHandler;
import me.sheimi.helper.MenuHelpRequest;
import me.sheimi.helper.TextPaneHelpHandler;
import me.sheimi.helper.TextPaneHelpRequest;
import me.sheimi.lang.LangManager;
import me.sheimi.lang.LangSubject;
import me.sheimi.view.nav.ImgNavPane;
import me.sheimi.view.nav.Mediator;
import me.sheimi.view.nav.MyTextPane;
import me.sheimi.view.support.ActionComboBox;
import me.sheimi.view.support.ActionToolBar;
import me.sheimi.view.support.FontActionCellRenderer;
import me.sheimi.view.text.PagedEditorKit;

public class TextEdit extends JFrame implements LangObserver {

	// static member
	private final static String APP_NAME = "TextEdit";
	private final static int APP_WIDTH = 1080;
	private final static int APP_HEIGHT = 800;
	private final static int DEFAULT_FONT_SIZE = 30;

	private final static String MENU_FILE = "File";
	private final static String MENU_EDIT = "Edit";
	private final static String MENU_VIEW = "View";
	private final static String MENU_FORMAT = "Format";
	private final static String MENU_HELP = "Help";
	private final static String SUB_MENU_LANG = "ChangeLang";
	private static final int FONT_BOX_MIN = 16;

	// ui memver
	private JMenuBar m_menuBar;
	private ActionToolBar m_iconToolBar;
	private ActionToolBar m_formatToolBar;
	private JPanel m_mainPanel;
	private ImgNavPane m_navPane;
	private MyTextPane m_textPane;
	private JSplitPane m_splitPane;
	private JScrollPane m_navScroll;
	private JScrollPane m_pageScroll;
	private JColorChooser m_jcc;
	private JFrame m_chooserFrame;
	private JSpinner m_fontSizeChooser;
	private JSlider m_fontSizeSlider;
	private JPopupMenu m_textPanePopupMenu;
	private JFileChooser m_fileChooser;
	private HelpHandler m_helpHandler;

	// manager
	private EditorActionManager m_eam;
	private LangSubject m_langm;

	private Map<String, Object> m_textMap = new HashMap<String, Object>();

	public TextEdit() {
		super(APP_NAME);

		//init
		this.initComponents();
		this.initMenu();
		this.initiconToolBar();
		this.initPopupMenu();
		this.setListener();
		this.initHelper();

		this.setVisible(true);
		this.setSize(APP_WIDTH, APP_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * to init component of the text edit
	 */
	public void initComponents() {
		m_fileChooser = new JFileChooser();
		m_menuBar = new JMenuBar();
		m_iconToolBar = new ActionToolBar();
		m_formatToolBar = new ActionToolBar();
		m_mainPanel = new JPanel();
		m_textPane = new MyTextPane();
		m_textPanePopupMenu = new JPopupMenu();

		initTextPane();

		m_navScroll = new JScrollPane((Component) m_navPane);
		m_pageScroll = new JScrollPane(m_textPane);
		m_splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, m_navScroll,
				m_pageScroll);
		m_fontSizeChooser = new JSpinner(new SpinnerNumberModel(
				DEFAULT_FONT_SIZE, 0, 100, 1));
		m_fontSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100,
				DEFAULT_FONT_SIZE);

		//just for test
		m_navPane.setScrollBar(this.m_navScroll.getVerticalScrollBar());
		m_textPane.setScrollBar(this.m_pageScroll.getVerticalScrollBar());
		
		// set color chooser
		m_jcc = new JColorChooser();
		m_chooserFrame = new JFrame("Color Chooser");
		m_chooserFrame.getContentPane().add(m_jcc);
		m_chooserFrame.setVisible(false);
		m_chooserFrame.setSize(500, 500);
		// m_chooserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel northPanel = new JPanel();
		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());

		// init contentpane
		northPanel.setLayout(new BorderLayout());
		northPanel.add(m_menuBar, BorderLayout.NORTH);
		northPanel.add(m_iconToolBar, BorderLayout.WEST);
		northPanel.add(m_formatToolBar, BorderLayout.SOUTH);
		pane.add(northPanel, BorderLayout.NORTH);
		pane.add(m_mainPanel, BorderLayout.CENTER);
		m_mainPanel.setLayout(new BorderLayout());

		//init spit Pane
		m_splitPane.setDividerLocation(180);
		m_splitPane.setDividerSize(5);
		m_splitPane.setOneTouchExpandable(true);
		m_mainPanel.add(m_splitPane);

		m_langm = LangManager.instance();
		m_langm.register(this);

	}

	/**
	 * to init the TextPain and defautAttribute
	 */
	private void initTextPane() {
		// init TextPane
		
		Mediator mediator = new Mediator();
		m_textPane.setSize(870, 1030);
		m_textPane.setHeight(1030);
		PagedEditorKit pagedEditorKit = new PagedEditorKit();
		m_textPane.setEditorKit(pagedEditorKit);
		try {
			m_eam = EditorActionManager.instance(this);
		} catch (TextEditNotMatchException e) {
			e.printStackTrace();
			System.exit(1);
		}
		m_navPane = new ImgNavPane(m_textPane, mediator);
		m_textPane.add(m_textPanePopupMenu);
		m_textPane.setMediator(mediator);

	}

	/**
	 * to init the menu
	 */
	private void initMenu() {
		
		// init menus=========
		JMenu fileMenu = new JMenu(m_langm.getText(MENU_FILE));
		JMenu editMenu = new JMenu(m_langm.getText(MENU_EDIT));
		JMenu viewMenu = new JMenu(m_langm.getText(MENU_VIEW));
		JMenu formatMenu = new JMenu(m_langm.getText(MENU_FORMAT));
		JMenu helpMenu = new JMenu(m_langm.getText(MENU_HELP));

		// =========add menuitem to file menu=========
		fileMenu.add(m_eam.createAction(EditorActionManager.New_FILE_ACTION));
		fileMenu.add(m_eam.createAction(EditorActionManager.OPEN_FILE_ACTION));
		fileMenu.add(m_eam.createAction(EditorActionManager.SAVE_FILE_ACTION));
		fileMenu.addSeparator();
		fileMenu.add(m_eam.createAction(EditorActionManager.QUIT_ACTION));

		// =========add menuitem to edit menu=========
		editMenu.add(m_eam.createAction(EditorActionManager.UNDO_ACTION));
		editMenu.add(m_eam.createAction(EditorActionManager.REDO_ACTION));
		editMenu.addSeparator();
		editMenu.add(m_eam.createAction(EditorActionManager.CUT_ACTION));
		editMenu.add(m_eam.createAction(EditorActionManager.COPY_ACTION));
		editMenu.add(m_eam.createAction(EditorActionManager.PASTE_ACTION));
		editMenu.addSeparator();
		editMenu.add(m_eam
				.createAction(EditorActionManager.INSERT_IMAGE_ACTION));
		
		// =========add menuitem to style menu=========
		formatMenu.add(m_eam.createAction(EditorActionManager.ITALIC_ACTION));
		formatMenu.add(m_eam.createAction(EditorActionManager.BOLD_ACTION));
		formatMenu
				.add(m_eam.createAction(EditorActionManager.UNDERLINE_ACTION));
		formatMenu.addSeparator();
		formatMenu.add(m_eam
				.createAction(EditorActionManager.ALIGNMENT_LEFT_ACTION));
		formatMenu.add(m_eam
				.createAction(EditorActionManager.ALIGNMENT_RIGHT_ACTION));
		formatMenu.add(m_eam
				.createAction(EditorActionManager.ALIGNMENT_CENTER_ACTION));
		formatMenu.addSeparator();
		formatMenu.add(m_eam
				.createAction(EditorActionManager.EMBEDDED_STYLE[0]));
		formatMenu.add(m_eam
				.createAction(EditorActionManager.EMBEDDED_STYLE[1]));

		// =========add menuitem to view menu=========
		JMenu langMenu = new JMenu(m_langm.getText(SUB_MENU_LANG));
		ButtonGroup langGroup = new ButtonGroup();
		for (String l : LangManager.LOCALES) {
			JCheckBoxMenuItem langItem = new JCheckBoxMenuItem();
			langItem.setAction(m_eam.createAction(l));
			viewMenu.add(langMenu);
			langMenu.add(langItem);
			langGroup.add(langItem);
		}

		// =========add help to help menu=========
		helpMenu.add(m_eam.createAction(EditorActionManager.GET_HELP));

		// =========add menus=========
		m_menuBar.add(fileMenu);
		m_menuBar.add(editMenu);
		m_menuBar.add(viewMenu);
		m_menuBar.add(formatMenu);
		m_menuBar.add(helpMenu);

		m_textMap.put(MENU_FILE, fileMenu);
		m_textMap.put(MENU_EDIT, editMenu);
		m_textMap.put(MENU_VIEW, viewMenu);
		m_textMap.put(MENU_FORMAT, formatMenu);
		m_textMap.put(MENU_HELP, helpMenu);
		m_textMap.put(SUB_MENU_LANG, langMenu);
	}

	/**
	 * to init the tool bar
	 */
	private void initiconToolBar() {
		// init iconToolBar 1
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.New_FILE_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.OPEN_FILE_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.SAVE_FILE_ACTION));
		m_iconToolBar.addSeparator();
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.UNDO_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.REDO_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.CUT_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.COPY_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.PASTE_ACTION));
		m_iconToolBar.addSeparator();
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.BOLD_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.ITALIC_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.UNDERLINE_ACTION));
		m_iconToolBar.addSeparator();
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.ALIGNMENT_LEFT_ACTION));
		m_iconToolBar
				.add(false,
						m_eam.createAction(EditorActionManager.ALIGNMENT_CENTER_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.ALIGNMENT_RIGHT_ACTION));
		m_iconToolBar.addSeparator();
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.COLOR_CHOOSER_ACTION));
		m_iconToolBar.add(false,
				m_eam.createAction(EditorActionManager.INSERT_IMAGE_ACTION));

		// init format toolbar
		JLabel fontLabel = new JLabel(
				m_langm.getText(EditorActionManager.FONT_CHOOSER_ACTION));
		JLabel fontSizeLabel = new JLabel(
				m_langm.getText(EditorActionManager.FONT_SIZE_ACTION));
		m_formatToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

		ActionComboBox fontsBox = new ActionComboBox();
		fontsBox.setMinimumSize(new Dimension(0, FONT_BOX_MIN));
		fontsBox.setEditable(false);
		fontsBox.setRenderer(new FontActionCellRenderer());

		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts();
		for (Font font : fonts) {
			fontsBox.addItem(m_eam.createAction(font.getFontName()));
		}

		ActionComboBox fontSizeBox = new ActionComboBox();
		fontsBox.setMinimumSize(new Dimension(0, 16));
		fontsBox.setEditable(false);
		for (int size : EditorActionManager.SUPPORT_FONT_SIZE) {
			String query = EditorActionManager.FONT_SIZE_ACTION + "_" + size;
			fontSizeBox.addItem(m_eam.createAction(query));
		}

		m_textMap.put(EditorActionManager.FONT_CHOOSER_ACTION, fontLabel);
		m_textMap.put(EditorActionManager.FONT_SIZE_ACTION, fontSizeLabel);

		//add to toolBar
		m_formatToolBar.add(fontLabel);
		m_formatToolBar.add(fontsBox);
		m_formatToolBar.add(fontSizeLabel);
		m_formatToolBar.add(fontSizeBox);

	}

	/**
	 * to init the PopupMenu
	 */
	private void initPopupMenu() {
		m_textPanePopupMenu.add(m_eam
				.createAction(EditorActionManager.CUT_ACTION));
		m_textPanePopupMenu.add(m_eam
				.createAction(EditorActionManager.COPY_ACTION));
		m_textPanePopupMenu.add(m_eam
				.createAction(EditorActionManager.PASTE_ACTION));
		m_textPanePopupMenu.addSeparator();
		m_textPanePopupMenu.add(m_eam
				.createAction(EditorActionManager.BOLD_ACTION));
		m_textPanePopupMenu.add(m_eam
				.createAction(EditorActionManager.ITALIC_ACTION));
		m_textPanePopupMenu.add(m_eam
				.createAction(EditorActionManager.UNDERLINE_ACTION));

	}

	/**
	 * to setup the Listener of this APP
	 */
	private void setListener() {
		m_jcc.getSelectionModel().addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				Action a = new StyledEditorKit.ForegroundAction("null", m_jcc
						.getColor());
				a.actionPerformed(null);
			}

		});
		this.m_textPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checkPopup(e);
			}

			public void mouseClicked(MouseEvent e) {
				checkPopup(e);
			}

			public void mouseReleased(MouseEvent e) {
				checkPopup(e);
			}

			private void checkPopup(MouseEvent e) {
				if (e.isPopupTrigger()) {
					m_textPanePopupMenu.show(m_textPane, e.getX(), e.getY());
				}
			}
		});
	}

	/**
	 * init the helper chain
	 */
	private void initHelper() {

		HelpHandler h = new GeneralHelpHandler();
		h = new FileChooserHelpHandler(h);
		h = new MenuHelpHandler(h);
		m_helpHandler = new TextPaneHelpHandler(h);

		this.addMouseListener(new RequestListener(new GeneralHelpRequest()));
		this.m_fileChooser.addMouseListener(new RequestListener(
				new FileChooserHelpRequest()));
		this.m_menuBar.addMouseListener(new RequestListener(
				new MenuHelpRequest()));
		this.m_textPane.addMouseListener(new RequestListener(
				new TextPaneHelpRequest()));
	}

	/**
	 * a text chain
	 * @author sheimi
	 *
	 */
	private class RequestListener implements MouseListener {

		long t;

		HelpRequest m_hr;

		RequestListener(HelpRequest helpRequest) {
			this.m_hr = helpRequest;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			t = System.currentTimeMillis();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			long deltaT = System.currentTimeMillis() - t;
			if (deltaT >= 1000) {
				m_helpHandler.HandlerHelp(this.m_hr);
			}
		}

	}

	public static void main(String[] args) {
		new TextEdit();
	}

	// public methods
	public JTextPane getTextPane() {
		return this.m_textPane;
	}

	public void showColorChooser() {
		m_chooserFrame.setVisible(true);

	}

	public void hideColorChooser() {
		m_chooserFrame.setVisible(false);
	}
	
	/**
	 * new document
	 */
	public void newFile() {
		Document doc = new DefaultStyledDocument();
		m_eam.initDocumentListener(doc);
		doc.addDocumentListener(m_navPane);
		m_textPane.setDocument(doc);
		m_navPane.takeSnapshot();
	}

	/**
	 * to open a document
	 */
	public void openFile() {
		int option = m_fileChooser.showOpenDialog(this);
		StyledDocument doc = null;
		if (option == JFileChooser.APPROVE_OPTION) {
			File f = m_fileChooser.getSelectedFile();
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				doc = ((StyledDocument) ois.readObject());
				m_eam.initDocumentListener(doc);
				doc.addDocumentListener(m_navPane);
				m_textPane.setDocument(doc);
				m_navPane.takeSnapshot();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * to save the document
	 */
	public void saveFile() {
		int option = m_fileChooser.showSaveDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			if (m_fileChooser.getSelectedFile() != null) {
				File theFileToSave = m_fileChooser.getSelectedFile();
				try {
					Document doc = m_textPane.getDocument();
					this.m_eam.removeDocumentListener(doc);
					doc.removeDocumentListener(m_navPane);
					FileOutputStream fos = new FileOutputStream(
							theFileToSave.getPath());
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(doc);
					this.m_eam.initDocumentListener(doc);
					doc.addDocumentListener(m_navPane);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * to insert a image
	 */
	public void insertImage() {
		int option = m_fileChooser.showDialog(this, "Select a picture");
		if (option == JFileChooser.APPROVE_OPTION) {
			if (m_fileChooser.getSelectedFile() != null) {
				File f = m_fileChooser.getSelectedFile();
				String path = f.getPath();
				m_textPane.insertIcon(new ImageIcon(path));
			}
		}
	}

	public void getHelp() {
		JOptionPane.showMessageDialog(null, m_langm.getText("HELP"),
				m_langm.getText("HELP"), 0);
	}

	/**
	 * the method of LangObserver
	 */
	@Override
	public void changeLang() {
		// TODO Auto-generated method stus
		((AbstractButton) m_textMap.get(MENU_FILE)).setText(m_langm
				.getText(MENU_FILE));
		((AbstractButton) m_textMap.get(MENU_EDIT)).setText(m_langm
				.getText(MENU_EDIT));
		((AbstractButton) m_textMap.get(MENU_VIEW)).setText(m_langm
				.getText(MENU_VIEW));
		((AbstractButton) m_textMap.get(MENU_FORMAT)).setText(m_langm
				.getText(MENU_FORMAT));
		((AbstractButton) m_textMap.get(MENU_HELP)).setText(m_langm
				.getText(MENU_HELP));
		((AbstractButton) m_textMap.get(SUB_MENU_LANG)).setText(m_langm
				.getText(SUB_MENU_LANG));
		((JLabel) m_textMap.get(EditorActionManager.FONT_CHOOSER_ACTION))
				.setText(m_langm
						.getText(EditorActionManager.FONT_CHOOSER_ACTION));
		((JLabel) m_textMap.get(EditorActionManager.FONT_SIZE_ACTION))
				.setText(m_langm.getText(EditorActionManager.FONT_SIZE_ACTION));
	}

}
