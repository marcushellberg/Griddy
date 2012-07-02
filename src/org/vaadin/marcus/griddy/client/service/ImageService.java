package org.vaadin.marcus.griddy.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("images")
public interface ImageService extends RemoteService{
	
	List<String> getImages();
}
