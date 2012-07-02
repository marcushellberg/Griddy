package org.vaadin.marcus.griddy.client.components;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.vaadin.marcus.griddy.client.event.ImageSelectedListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;

public class GriddyPanel extends FlowPanel implements ClickHandler{

	Set<ImageSelectedListener> listeners = new HashSet<ImageSelectedListener>();
	
	private static final String FLIP = "flip";
	private boolean flipped = false;
	
	GriddyImage selectedImage;
	
	public GriddyPanel() {
		setStyleName("griddy-panel");
	}

	@Override
	public void onClick(ClickEvent event) {
		if(event.getSource() instanceof GriddyImage){
			imageSelected((GriddyImage) event.getSource());
			setFlipped(!flipped);
		}
	}

	private void imageSelected(GriddyImage selectedImage) {
		this.selectedImage = selectedImage;
		for(ImageSelectedListener listener : listeners){
			listener.imageSelected(selectedImage);
		}
	}

	public void showImages(List<String> images) {
		for (String url : images) {
			GriddyImage image = new GriddyImage();
			image.setFullImageUrl(url);
			image.showSmall();
			image.setWidth("200px");
			add(image);
			image.addClickHandler(this);
		}
	}

	public void returnToList() {
		if(selectedImage!=null){
			selectedImage.reset();
		}
		setFlipped(false);
	}
	
	
	private void setFlipped(boolean flipStatus){
		if(flipStatus){
			addStyleName(FLIP);
			flipped = true;
		} else {
			removeStyleName(FLIP);
			flipped = false;
		}
	}
	
	public void addImageSelectedListener(ImageSelectedListener listener){
		listeners.add(listener);
	}
	
	public void removeImageSelectedListener(ImageSelectedListener listener){
		listeners.remove(listener);
	}
}
