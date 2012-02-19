package me.sheimi.view.text;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 * @author Stanislav Lapitsky
 * @version 1.0
 */

public class PagedEditorKit extends StyledEditorKit {
	transient PageableViewFactory factory = new PageableViewFactory();
	protected int pageWidth = 870;
	protected int pageHeight = 1030;
	public static int DRAW_PAGE_INSET = 15;
	protected Insets pageMargins = new Insets(40, 40, 40, 40);
	protected final static double PER = 0.3;

	/**
	 * Constructs kit instance
	 */
	public PagedEditorKit() {
	}

	public PagedEditorKit(int pageWidth, int pageHeight, Insets pageMargins) {
		super();
		this.pageWidth = pageWidth;
		this.pageHeight = pageHeight;
		this.pageMargins = pageMargins;
	}


	public int getPageWidth() {
		return pageWidth;
	}

	public void setPageWidth(int pageWidth) {
		this.pageWidth = pageWidth;
	}

	public int getPageHeight() {
		return pageHeight;
	}

	public void setPageHeight(int pageHeight) {
		this.pageHeight = pageHeight;
	}

	public Insets getPageMargins() {
		return pageMargins;
	}

	public void setPageMargins(Insets pageMargins) {
		this.pageMargins = pageMargins;
	}

	/**
	 * gets kit view factory.
	 * 
	 * @return ViewFactory
	 */
	public ViewFactory getViewFactory() {
		return factory;
	}

	/**
	 * The view factory class creates custom views for pagination root view
	 * (SectionView class) and paragraph (PageableParagraphView class)
	 * 
	 * @author Stanislav Lapitsky
	 * @version 1.0
	 */
	class PageableViewFactory implements ViewFactory {
		/**
		 * Creates view for specified element.
		 * 
		 * @param elem
		 *            Element parent element
		 * @return View created view instance.
		 */
		public View create(Element elem) {
			String kind = elem.getName();
			if (kind != null) {
				if (kind.equals(AbstractDocument.ContentElementName)) {
					return new LabelView(elem);
				} else if (kind.equals(AbstractDocument.ParagraphElementName)) {
					return new PageableParagraphView(elem);
				} else if (kind.equals(AbstractDocument.SectionElementName)) {
					return new SectionView(elem, View.Y_AXIS);
				} else if (kind.equals(StyleConstants.ComponentElementName)) {
					return new ComponentView(elem);
				} else if (kind.equals(StyleConstants.IconElementName)) {
					return new IconView(elem);
				}
			}
			// default to text display
			return new LabelView(elem);
		}

	}

	/**
	 * Root view which perform pagination and paints frame around pages.
	 * 
	 * @author Stanislav Lapitsky
	 * @version 1.0
	 */
	class SectionView extends BoxView {
		int pageNumber = 0;

		/**
		 * Creates view instace
		 * 
		 * @param elem
		 *            Element
		 * @param axis
		 *            int
		 */
		public SectionView(Element elem, int axis) {
			super(elem, axis);
		}

		/**
		 * Gets amount of pages
		 * 
		 * @return int
		 */
		public int getPageCount() {
			return pageNumber;
		}

		/**
		 * Perform layout on the box
		 * 
		 * @param width
		 *            the width (inside of the insets) >= 0
		 * @param height
		 *            the height (inside of the insets) >= 0
		 */
		protected void layout(int width, int height) {
			width = pageWidth - 2 * DRAW_PAGE_INSET - pageMargins.left
					- pageMargins.right;
			this.setInsets((short) (DRAW_PAGE_INSET + pageMargins.top),
					(short) (DRAW_PAGE_INSET + pageMargins.left),
					(short) (DRAW_PAGE_INSET + pageMargins.bottom),
					(short) (DRAW_PAGE_INSET + pageMargins.right));
			super.layout(width, height);
		}

		/**
		 * Determines the maximum span for this view along an axis.
		 * 
		 * overriddedn
		 */
		public float getMaximumSpan(int axis) {
			return getPreferredSpan(axis);
		}

		/**
		 * Determines the minimum span for this view along an axis.
		 * 
		 * overriddedn
		 */
		public float getMinimumSpan(int axis) {
			return getPreferredSpan(axis);
		}

		/**
		 * Determines the preferred span for this view along an axis.
		 * overriddedn
		 */
		public float getPreferredSpan(int axis) {
			float span = 0;
			if (axis == View.X_AXIS) {
				span = pageWidth;
			} else {
				span = pageHeight * getPageCount();
			}
			return span;
		}

		/**
		 * Performs layout along Y_AXIS with shifts for pages.
		 * 
		 * @param targetSpan
		 *            int
		 * @param axis
		 *            int
		 * @param offsets
		 *            int[]
		 * @param spans
		 *            int[]
		 */
		protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets,
				int[] spans) {
			super.layoutMajorAxis(targetSpan, axis, offsets, spans);
			int totalOffset = 0;
			int n = offsets.length;
			pageNumber = 0;
			for (int i = 0; i < n; i++) {
				offsets[i] = totalOffset;
				View v = getView(i);
				if (v instanceof IPageableView) {
					((IPageableView) v).setBreakSpan(0);
					((IPageableView) v).setAdditionalSpace(0);
				}

				if ((offsets[i] + spans[i]) > (pageNumber * pageHeight
						- DRAW_PAGE_INSET * 2 - pageMargins.top - pageMargins.bottom)) {
					if ((v instanceof IPageableView) && (v.getViewCount() > 1)) {
						IPageableView IPageableView = (IPageableView) v;
						int space = offsets[i] - (pageNumber - 1) * pageHeight;
						int breakSpan = (pageNumber * pageHeight
								- DRAW_PAGE_INSET * 2 - pageMargins.top - pageMargins.bottom)
								- offsets[i];
						IPageableView.setBreakSpan(breakSpan);
						IPageableView.setPageOffset(space);
						IPageableView.setStartPageNumber(pageNumber);
						IPageableView.setEndPageNumber(pageNumber);
						int height = (int) getHeight();

						int width = ((BoxView) v).getWidth();
						if (v instanceof PageableParagraphView) {
							PageableParagraphView parView = (PageableParagraphView) v;
							parView.layout(width, height);
						}

						pageNumber = IPageableView.getEndPageNumber();
						spans[i] += IPageableView.getAdditionalSpace();
					} else {
						offsets[i] = pageNumber * pageHeight;
						pageNumber++;
					}
				}
				totalOffset = (int) Math.min((long) offsets[i]
						+ (long) spans[i], Integer.MAX_VALUE);
			}
		}

		/**
		 * Paints view content and page frames.
		 * 
		 * @param g
		 *            Graphics
		 * @param a
		 *            Shape
		 */
		public void paint(Graphics g, Shape a) {
			Rectangle alloc = (a instanceof Rectangle) ? (Rectangle) a : a
					.getBounds();
			Shape baseClip = g.getClip().getBounds();
			int pageCount = getPageCount();
			Rectangle page = new Rectangle();
			page.x = alloc.x;
			page.y = alloc.y;
			page.height = pageHeight;
			page.width = pageWidth;
			String sC = Integer.toString(pageCount);
			
			for (int i = 0; i < pageCount; i++) {
				page.y = alloc.y + pageHeight * i;
				paintPageFrame(g, page, (Rectangle) baseClip);
				g.setColor(Color.blue);
				String sN = Integer.toString(i + 1);
				String pageStr = "Page: " + sN;
				pageStr += " of " + sC;
			}
			super.paint(g, a);
			g.setColor(Color.gray);
			// Fills background of pages
			int currentWidth = (int) alloc.getWidth();
			int currentHeight = (int) alloc.getHeight();
			int x = page.x + DRAW_PAGE_INSET;
			int y = 0;
			int w = 0;
			int h = 0;
			if (pageWidth < currentWidth) {
				w = currentWidth;
				h = currentHeight;
				g.fillRect(page.x + page.width, alloc.y, w, h);
			}
			if (pageHeight * pageCount < currentHeight) {
				w = currentWidth;
				h = currentHeight;
				g.fillRect(page.x, alloc.y + page.height * pageCount, w, h);
			}
		}

		/**
		 * Paints frame for specified page
		 * 
		 * @param g
		 *            Graphics
		 * @param page
		 *            Shape page rectangle
		 * @param container
		 *            Rectangle
		 */
		public void paintPageFrame(Graphics g, Shape page, Rectangle container) {
			Rectangle alloc = (page instanceof Rectangle) ? (Rectangle) page
					: page.getBounds();
			if (container.intersection(alloc).height <= 0)
				return;
			Color oldColor = g.getColor();

			// borders
			g.setColor(Color.gray);
			g.fillRect(alloc.x, alloc.y, alloc.width, DRAW_PAGE_INSET);
			g.fillRect(alloc.x, alloc.y, DRAW_PAGE_INSET, alloc.height);
			g.fillRect(alloc.x, alloc.y + alloc.height - DRAW_PAGE_INSET,
					alloc.width, DRAW_PAGE_INSET);
			g.fillRect(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y,
					DRAW_PAGE_INSET, alloc.height);

			// frame
			g.setColor(Color.black);
			g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET,
					alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y
							+ DRAW_PAGE_INSET);
			g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET,
					alloc.x + DRAW_PAGE_INSET, alloc.y + alloc.height
							- DRAW_PAGE_INSET);
			g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + alloc.height
					- DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET,
					alloc.y + alloc.height - DRAW_PAGE_INSET);
			g.drawLine(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y
					+ DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET,
					alloc.y + alloc.height - DRAW_PAGE_INSET);

			// shadow
			g.fillRect(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y
					+ DRAW_PAGE_INSET + 4, 4, alloc.height - 2
					* DRAW_PAGE_INSET);
			g.fillRect(alloc.x + DRAW_PAGE_INSET + 4, alloc.y + alloc.height
					- DRAW_PAGE_INSET, alloc.width - 2 * DRAW_PAGE_INSET, 4);

			g.setColor(oldColor);
		}

	}

	/**
	 * Represents multipage paragraph.
	 * 
	 * @author Stanislav Lapitsky
	 * @version 1.0
	 */
	class PageableParagraphView extends ParagraphView implements IPageableView {
		protected int additionalSpace = 0;
		protected int breakSpan = 0;
		protected int pageOffset = 0;
		protected int startPageNumber = 0;
		protected int endPageNumber = 0;

		public PageableParagraphView(Element elem) {
			super(elem);
		}

		public void layout(int width, int height) {
			super.layout(width, height);
		}

		protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets,
				int[] spans) {
			super.layoutMajorAxis(targetSpan, axis, offsets, spans);
			performMultiPageLayout(targetSpan, axis, offsets, spans);
		}

		/**
		 * Layout paragraph's content splitting between pages if needed.
		 * Calculates shifts and breaks for parent view (SectionView)
		 * 
		 * @param targetSpan
		 *            int
		 * @param axis
		 *            int
		 * @param offsets
		 *            int[]
		 * @param spans
		 *            int[]
		 */
		public void performMultiPageLayout(int targetSpan, int axis,
				int[] offsets, int[] spans) {
			if (breakSpan == 0)
				return;
			int space = breakSpan;

			additionalSpace = 0;
			endPageNumber = startPageNumber;
			int topInset = this.getTopInset();
			int offs = 0;
			for (int i = 0; i < offsets.length; i++) {
				if (offs + spans[i] + topInset > space) {
					int newOffset = endPageNumber * pageHeight;
					int addSpace = newOffset - (startPageNumber - 1)
							* pageHeight - pageOffset - offs - topInset;
					additionalSpace += addSpace;
					offs += addSpace;
					for (int j = i; j < offsets.length; j++) {
						offsets[j] += addSpace;
					}
					endPageNumber++;
					space = (endPageNumber * pageHeight - 2 * DRAW_PAGE_INSET
							- pageMargins.top - pageMargins.bottom)
							- (startPageNumber - 1) * pageHeight - pageOffset;
				}
				offs += spans[i];
			}
		}

		/**
		 * Gets view's start page number
		 * 
		 * @return page number
		 */
		public int getStartPageNumber() {
			return startPageNumber;
		}

		/**
		 * Gets view's end page number
		 * 
		 * @return page number
		 */
		public int getEndPageNumber() {
			return endPageNumber;
		}

		/**
		 * Gets view's extra space (space between pages)
		 * 
		 * @return extra space
		 */
		public int getAdditionalSpace() {
			return additionalSpace;
		}

		/**
		 * Gets view's break span
		 * 
		 * @return break span
		 */
		public int getBreakSpan() {
			return breakSpan;
		}

		/**
		 * Gets view's offsets on the page
		 * 
		 * @return offset
		 */
		public int getPageOffset() {
			return pageOffset;
		}

		/**
		 * Sets view's start page number
		 * 
		 * @param startPageNumber
		 *            page number
		 */
		public void setStartPageNumber(int startPageNumber) {
			this.startPageNumber = startPageNumber;
		}

		/**
		 * Sets view's end page number
		 * 
		 * @param endPageNumber
		 *            page number
		 */
		public void setEndPageNumber(int endPageNumber) {
			this.endPageNumber = endPageNumber;
		}

		/**
		 * Sets extra space (space between pages)
		 * 
		 * @param additionalSpace
		 *            additional space
		 */
		public void setAdditionalSpace(int additionalSpace) {
			this.additionalSpace = additionalSpace;
		}

		/**
		 * Sets view's break span.
		 * 
		 * @param breakSpan
		 *            break span
		 */
		public void setBreakSpan(int breakSpan) {
			this.breakSpan = breakSpan;
		}

		/**
		 * Sets view's offset on the page
		 * 
		 * @param pageOffset
		 *            offset
		 */
		public void setPageOffset(int pageOffset) {
			this.pageOffset = pageOffset;
		}
	}
}
