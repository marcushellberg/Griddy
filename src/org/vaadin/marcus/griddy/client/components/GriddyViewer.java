package org.vaadin.marcus.griddy.client.components;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.marcus.griddy.client.event.ImageSelectedListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

/**
 * Viewer class responsible for showing the full-sized image. When image is
 * clicked, listeners are notified so view can be hidden.
 * 
 * @author Marcus Hellberg / Vaadin
 * 
 */
public class GriddyViewer extends FlowPanel {

	Set<ImageSelectedListener> listeners = new HashSet<ImageSelectedListener>();

	protected Image fullSizeImage;

	public GriddyViewer() {
		setStyleName("griddy-viewer");
	}

	/**
	 * Show full-size version of given {@link GriddyImage}.
	 * 
	 * @param image
	 */
	public void showImage(GriddyImage image) {
		addStyleName("shown");
		fullSizeImage = new Image();
		fullSizeImage.setStyleName("full-size");
		fullSizeImage.setUrl(image.getFullUrl());
		fullSizeImage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				imageClicked();
			}
		});
		add(fullSizeImage);
	}

	/**
	 * Remove stylename and fire event to listeners.
	 */
	protected void imageClicked() {
		removeStyleName("shown");
		remove(fullSizeImage);
		for (ImageSelectedListener listener : listeners) {
			listener.imageSelected(null);
		}
	}

	/**
	 * Register new {@link ImageSelectedListener}.
	 * 
	 * @param listener
	 */
	public void addImageSelectedListener(ImageSelectedListener listener) {
		listeners.add(listener);
	}

	/**
	 * Remove given {@link ImageSelectedListener}.
	 * 
	 * @param listener
	 */
	public void removeImageSelectedListener(ImageSelectedListener listener) {
		listeners.remove(listener);
	}
}
