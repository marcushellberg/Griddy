package org.vaadin.marcus.griddy.client.components;

import com.google.gwt.user.client.ui.FlowPanel;

public class GriddyColumn extends FlowPanel{
	
	public GriddyColumn() {
		setStyleName("griddy-column");
	}
	
	public int getHeight(){
		return getElement().getClientHeight();
	}
	
	public void addImage(GriddyImage image){
		add(image);
	}

}
