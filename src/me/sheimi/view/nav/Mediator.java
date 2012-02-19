package me.sheimi.view.nav;

import java.util.LinkedList;
import java.util.List;

public class Mediator {
	
	List<Colleague> colleagues = new LinkedList<Colleague>(); 
	
	public void addColleague(Colleague c) {
		colleagues.add(c);
	}
	
	public void setPageAll(int x) {
		for (Colleague c : colleagues) {
			c.setPageAll(x);
		}
	}
	
	public void setPageCurrent(int x, Colleague co) {
		for (Colleague c : colleagues) {
			if (co == c)
				continue;
			c.setPageCurrent(x);
		}
	}
	
}
