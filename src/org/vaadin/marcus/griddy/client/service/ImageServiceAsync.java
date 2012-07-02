package org.vaadin.marcus.griddy.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ImageServiceAsync {

	public void getImages(AsyncCallback<List<String>> callback);
}
