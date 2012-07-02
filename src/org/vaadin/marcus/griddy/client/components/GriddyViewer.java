package org.vaadin.marcus.griddy.client.components;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.marcus.griddy.client.event.ImageSelectedListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class GriddyViewer extends FlowPanel {

	Set<ImageSelectedListener> listeners = new HashSet<ImageSelectedListener>();
	private Image fullSize;

	public GriddyViewer() {
		setStyleName("griddy-viewer");
	}

	public void showImage(GriddyImage image) {
		addStyleName("shown");
		fullSize = new Image();
		fullSize.setStyleName("full-size");
		fullSize.setUrl(image.getFullUrl());
		fullSize.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				imageClicked();
			}
		});
		add(fullSize);
	}
	

	protected void imageClicked() {
		removeStyleName("shown");
		remove(fullSize);
		for (ImageSelectedListener listener : listeners) {
			listener.imageSelected(null);
		}
	}

	public void addImageSelectedListener(ImageSelectedListener listener) {
		listeners.add(listener);
	}

	public void removeImageSelectedListener(ImageSelectedListener listener) {
		listeners.remove(listener);
	}
}
