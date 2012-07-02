package org.vaadin.marcus.griddy.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;

public class GriddyImage extends Image{
	
	private static final String CLICKED_STYLENAME = "clicked";
	private String fullUrl;

	public GriddyImage() {
		setStyleName("griddy-image");
		
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addStyleName(CLICKED_STYLENAME);
			}
		});
	}

	public void reset() {
		removeStyleName(CLICKED_STYLENAME);
	}
	
	public void setFullImageUrl(String url){
		fullUrl = url;
	}
	
	public String getFullUrl() {
		return fullUrl;
	}
	
	public void showFullSize(){
		setUrl(fullUrl);
	}
	
	public void showSmall(){
		String smallUrl = fullUrl.split(".jpg")[0] + "-small.jpg";
		setUrl(smallUrl);
	}

}
