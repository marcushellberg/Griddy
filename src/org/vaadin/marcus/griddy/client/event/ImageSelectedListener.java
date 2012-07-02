package org.vaadin.marcus.griddy.client.event;

import org.vaadin.marcus.griddy.client.components.GriddyImage;

/**
 * Listener interface for components interested in changes to the selected
 * image.
 * 
 * @author Marcus Hellberg / Vaadin
 * 
 */
public interface ImageSelectedListener {

	/**
	 * Image has been selected.
	 * 
	 * @param image
	 *            Selected {@link GriddyImage}, null if selection has been
	 *            cleared.
	 */
	public void imageSelected(GriddyImage image);
}
