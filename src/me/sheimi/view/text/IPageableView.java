package me.sheimi.view.text;

/**
 * padged view should implement this interface.
 * 
 * @author Stanislav Lapitsky modified by sheimi
 * @version 2.0
 */
public interface IPageableView {

	/**
	 * Perform pageable layout of componet's children views.
	 * 
	 * @param targetSpan
	 *            available view's span
	 * @param axis
	 *            view axis (in most cases Y_AXIS.
	 * @param offsets
	 *            children offsets
	 * @param spans
	 *            children spans
	 */
	public void performMultiPageLayout(int targetSpan, int axis, int[] offsets,
			int[] spans);

	/**
	 * Gets view's start page number
	 * 
	 * @return page number
	 */
	public int getStartPageNumber();

	/**
	 * Gets view's end page number
	 * 
	 * @return page number
	 */
	public int getEndPageNumber();

	/**
	 * Gets view's extra space (space between pages)
	 * 
	 * @return extra space
	 */
	public int getAdditionalSpace();

	/**
	 * Gets view's break span
	 * 
	 * @return break span
	 */
	public int getBreakSpan();

	/**
	 * Gets view's offsets on the page
	 * 
	 * @return offset
	 */
	public int getPageOffset();

	/**
	 * Sets view's start page number
	 * 
	 * @param startPageNumber
	 *            page number
	 */
	public void setStartPageNumber(int startPageNumber);

	/**
	 * Sets view's end page number
	 * 
	 * @param endPageNumber
	 *            page number
	 */
	public void setEndPageNumber(int endPageNumber);

	/**
	 * Sets extra space (space between pages)
	 * 
	 * @param additionalSpace
	 *            additional space
	 */
	public void setAdditionalSpace(int additionalSpace);

	/**
	 * Sets view's break span.
	 * 
	 * @param breakSpan
	 *            break span
	 */
	public void setBreakSpan(int breakSpan);

	/**
	 * Sets view's offset on the page
	 * 
	 * @param pageOffset
	 *            offset
	 */
	public void setPageOffset(int pageOffset);
}
