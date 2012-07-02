package org.vaadin.marcus.griddy.client.components;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 * One column in the gallery. Has a convenience method for getting the height of
 * the column.
 * 
 * @author Marcus Hellberg / Vaadin
 * 
 */
public class GriddyColumn extends FlowPanel {

	public GriddyColumn() {
		setStyleName("griddy-column");
	}

	/**
	 * Get height of the column.
	 * 
	 * @return
	 */
	public int getHeight() {
		return getElement().getClientHeight();
	}

	/**
	 * Add image to this column.
	 * 
	 * @param image
	 */
	public void addImage(GriddyImage image) {
		add(image);
	}

}
