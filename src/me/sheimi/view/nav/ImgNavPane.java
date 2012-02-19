package me.sheimi.view.nav;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class ImgNavPane extends JLabel implements DocumentListener,
		MouseListener, Colleague {

	private JTextPane m_jtp;
	private final static double PER = 0.2;
	private int pageHeight;
	private Mediator m_mediator;
	private JScrollBar m_jsb;
	
	public ImgNavPane(JTextPane jtp) {
		this.addMouseListener(this);
		this.m_jtp = jtp;
		Document doc = jtp.getDocument();
		jtp.getDocument().addDocumentListener(this);
		this.pageHeight = (int) (jtp.getHeight() * PER);
		this.setBackground(Color.gray);
		this.takeSnapshot();
		this.setVerticalAlignment(JLabel.TOP);
	}
	
	public ImgNavPane(JTextPane jtp, Mediator m) {
		this(jtp);
		this.m_mediator = m;
		m.addColleague(this);
	}

	public void takeSnapshot() {
		BufferedImage img_o = new BufferedImage(m_jtp.getWidth(),
				m_jtp.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img_o.createGraphics();
		m_jtp.paint(g);
		BufferedImage img_r = new BufferedImage(
				((int) (m_jtp.getWidth() * PER)),
				((int) (m_jtp.getHeight() * PER)), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g_r = img_r.createGraphics();
		g_r.drawImage(img_o, 0, 0, ((int) (m_jtp.getWidth() * PER)),
				((int) (m_jtp.getHeight() * PER)), null);
		g.dispose();
		this.setIcon(new ImageIcon(img_r));
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		this.takeSnapshot();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		this.takeSnapshot();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		this.takeSnapshot();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x = arg0.getX();
		int y = arg0.getY();
		int page = y / this.pageHeight;
		System.out.println(page);
		m_mediator.setPageCurrent(page, this);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void setScrollBar(JScrollBar jsb) {
		this.m_jsb = jsb;
	}

	@Override
	public void setPageCurrent(int x) {
		try {
			m_jsb.setValue(x * this.pageHeight);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPageAll(int x) {

	}

}
