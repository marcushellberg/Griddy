package org.vaadin.marcus.griddy.client;

import java.util.List;

import org.vaadin.marcus.griddy.client.components.GriddyImage;
import org.vaadin.marcus.griddy.client.components.GriddyPanel;
import org.vaadin.marcus.griddy.client.components.GriddyViewer;
import org.vaadin.marcus.griddy.client.event.ImageSelectedListener;
import org.vaadin.marcus.griddy.client.service.ImageService;
import org.vaadin.marcus.griddy.client.service.ImageServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class GriddyView extends FlowPanel implements ImageSelectedListener {
	
	private GriddyPanel griddyPanel;
	private GriddyViewer griddyViewer;
	
	ImageServiceAsync imageService;

	public GriddyView() {
		setStyleName("griddy-view");
		
		griddyPanel = new GriddyPanel();
		griddyPanel.addImageSelectedListener(this);
		
		griddyViewer = new GriddyViewer();
		griddyViewer.addImageSelectedListener(this);
		
		add(griddyPanel);
		add(griddyViewer);
		
		showImages();
	}


	private void showImages() {
		getImageService().getImages(new AsyncCallback<List<String>>() {
			
			@Override
			public void onSuccess(List<String> result) {
				griddyPanel.showImages(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				showError();
			}
		});
	}


	protected void showError() {
		add(new Label("UH-OH, something went wrong."));
	}


	private ImageServiceAsync getImageService() {
		if(imageService==null){
			imageService = GWT.create(ImageService.class);
		}
		return imageService;
	}

	@Override
	public void imageSelected(GriddyImage image) {
		if(image != null){
			griddyViewer.showImage(image);
		} else {
			griddyPanel.returnToList();
		}
	}
}
