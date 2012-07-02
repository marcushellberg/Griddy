package org.vaadin.marcus.griddy.server.service;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.marcus.griddy.client.service.ImageService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ImageServiceImpl  extends RemoteServiceServlet implements ImageService{

	private static final long serialVersionUID = 1L;
	
	@Override
	public List<String> getImages() {
		
		List<String> images = new ArrayList<String>();
		
		for(int i = 1; i<=14; i++){
			images.add("images/shape-"+i+".jpg");
		}
		
		return images;
	}

}
