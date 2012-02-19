package me.sheimi.view.nav;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;
import javax.swing.JTextPane;

public class MyTextPane extends JTextPane implements Colleague, AdjustmentListener{

	private int height;
	private int width;
	private Mediator m_mediator;
	private JScrollBar m_jsb;
	
	public void setHeight(int y) {
		this.height = y;
	}
	
	public void setMediator(Mediator m) {
		this.m_mediator = m;
		m.addColleague(this);
	}
	
	public void setScrollBar(JScrollBar jsb) {
		this.m_jsb = jsb;
		jsb.addAdjustmentListener(this);
	}

	@Override
	public void setPageCurrent(int x) {
		// TODO Auto-generated method stub
		System.out.println(x);
		m_jsb.setValue(x * height);
		System.out.println(height);
	}

	@Override
	public void setPageAll(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		int value = m_jsb.getValue();
		int x = value / this.height;
		m_mediator.setPageCurrent(x, this);
	}

}
