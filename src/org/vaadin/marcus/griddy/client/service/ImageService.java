package org.vaadin.marcus.griddy.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Service interface for getting image info from server.
 * 
 * @author Marcus Hellberg / Vaadin
 * 
 */
@RemoteServiceRelativePath("images")
public interface ImageService extends RemoteService {

	/**
	 * Get urls to all images.
	 * 
	 * @return
	 */
	List<String> getImages();
}
