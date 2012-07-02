package org.vaadin.marcus.griddy.client.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.vaadin.marcus.griddy.client.event.ImageSelectedListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public class GriddyPanel extends FlowPanel implements ClickHandler{

	Set<ImageSelectedListener> listeners = new HashSet<ImageSelectedListener>();
	
	private static final String FLIP = "flip";
	public static final int COLUMN_WIDTH_PX = 220;
	
	List<GriddyColumn> columns = new ArrayList<GriddyColumn>();
	
	private boolean flipped = false;
	
	GriddyImage selectedImage;

	private List<String> images;
	
	public GriddyPanel() {
		setStyleName("griddy-panel");
		createColumns();
	}

	private void createColumns() {
		int availableWidth = Window.getClientWidth();
		for(int i = 0; i < availableWidth / COLUMN_WIDTH_PX; i++){
			GriddyColumn column = new GriddyColumn();
			columns.add(column);
			add(column);
		}
		
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
		
		this.images = images;
		for (String url : images) {
			
			GriddyImage image = new GriddyImage();
			image.setFullImageUrl(url);
			image.showSmall();
			image.setWidth("200px");
			image.addClickHandler(this);
			
			addImageToShortestColumn(image);
		}
	}

	private void addImageToShortestColumn(GriddyImage image) {
		getShortestColumn().addImage(image);
	}

	private GriddyColumn getShortestColumn() {
		int minHeight = Integer.MAX_VALUE;
		GriddyColumn shortestColumn = null;
		
		for(GriddyColumn column : columns){
			int height = column.getHeight();
			if(height < minHeight){
				minHeight = height;
				shortestColumn = column;
			}
		}
		
		return shortestColumn;
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
