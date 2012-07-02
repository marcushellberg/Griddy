package org.vaadin.marcus.griddy.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;

/**
 * Class representing one image in the gallery. Has a click listener to hide the
 * image when clicked.
 * 
 * @author Marcus Hellberg / Vaadin
 * 
 */
public class GriddyImage extends Image {

	private static final String CLICKED_STYLENAME = "clicked";
	private String fullImageUrl;

	public GriddyImage() {
		setStyleName("griddy-image");

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addStyleName(CLICKED_STYLENAME);
			}
		});
	}

	/**
	 * Resets image to default styles.
	 */
	public void reset() {
		removeStyleName(CLICKED_STYLENAME);
	}

	/**
	 * Sets the url to the full sized image.
	 * 
	 * @param url
	 */
	public void setFullImageUrl(String url) {
		fullImageUrl = url;
	}

	/**
	 * Get url to full sized image.
	 * 
	 * @return
	 */
	public String getFullUrl() {
		return fullImageUrl;
	}

	/**
	 * Display full-sized.
	 */
	public void showFullSize() {
		setUrl(fullImageUrl);
	}

	/**
	 * Display small version. Currently substitutes full url with
	 * FULLNAME-small.jpg.
	 */
	public void showSmall() {
		String smallUrl = fullImageUrl.split(".jpg")[0] + "-small.jpg";
		setUrl(smallUrl);
	}

}
