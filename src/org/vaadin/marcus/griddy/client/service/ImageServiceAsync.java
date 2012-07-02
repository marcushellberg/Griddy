package org.vaadin.marcus.griddy.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Asynchronous interface for {@link ImageService}.
 * 
 * @author Marcus Hellberg / Vaadin
 * 
 */
public interface ImageServiceAsync {

	/**
	 * Gets images from server with an asynchronous callback method.
	 * 
	 * @param callback
	 */
	public void getImages(AsyncCallback<List<String>> callback);
}
