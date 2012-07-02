package org.vaadin.marcus.griddy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Loads Griddy application and shows {@link GriddyView}.
 */
public class Griddy implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		RootPanel.get().add(new GriddyView());
	}
}
