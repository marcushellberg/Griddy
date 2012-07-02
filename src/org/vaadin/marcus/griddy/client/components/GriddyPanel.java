package org.vaadin.marcus.griddy.client.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.vaadin.marcus.griddy.client.event.ImageSelectedListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * A panel that contains a number of {@link GriddyColumn}s calculated to fit
 * inside the browser window. The columns are packed shortest-first to avoid
 * gaps in the gallery even if images are differently oriented.
 * 
 * Clicking an image will flip the grid away and fire an event to all registered
 * {@link ImageSelectedListener}s.
 * 
 * @author Marcus Hellberg / Vaadin
 * 
 */
public class GriddyPanel extends FlowPanel implements ClickHandler,
		ResizeHandler {

	private static final String FLIP_STYLENAME = "flip";
	private static final String IMAGE_WIDTH = "200px";
	public static final int COLUMN_WIDTH_PX = 220;

	protected Set<ImageSelectedListener> imageSelectedListeners = new HashSet<ImageSelectedListener>();

	protected List<GriddyColumn> columns = new ArrayList<GriddyColumn>();
	protected GriddyImage selectedImage;
	private List<String> currentlyShownImageUrls;
	private boolean flipped = false;

	/**
	 * Creates a new {@link GriddyPanel} and populates it with
	 * {@link GriddyColumn}s.
	 */
	public GriddyPanel() {
		setStyleName("griddy-panel");
		createColumns();
		Window.addResizeHandler(this);
	}

	/**
	 * Calculates how many columns we can fit in the browser's width and creates
	 * them.
	 */
	protected void createColumns() {
		columns.clear();
		int availableWidth = Window.getClientWidth();
		for (int i = 0; i < availableWidth / COLUMN_WIDTH_PX; i++) {
			GriddyColumn column = new GriddyColumn();
			columns.add(column);
			add(column);
		}

	}

	/**
	 * Listen for image clicks to trigger animation.
	 */
	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() instanceof GriddyImage) {
			imageSelected((GriddyImage) event.getSource());
			setFlipped(!flipped);
		}
	}

	/**
	 * 
	 * @param selectedImage
	 */
	protected void imageSelected(GriddyImage selectedImage) {
		this.selectedImage = selectedImage;

		for (ImageSelectedListener listener : imageSelectedListeners) {
			listener.imageSelected(selectedImage);
		}
	}

	/**
	 * Creates {@link GriddyImage}s for each url in the list.
	 * 
	 * @param images
	 */
	public void showImages(List<String> images) {

		this.currentlyShownImageUrls = images;
		for (String url : images) {
			addImageToShortestColumn(createImageForUrl(url));
		}
	}

	/**
	 * Sets up a {@link GriddyImage} for the given url.
	 * 
	 * @param url
	 * @return
	 */
	protected GriddyImage createImageForUrl(String url) {
		GriddyImage image = new GriddyImage();
		image.setFullImageUrl(url);
		image.showSmall();
		image.setWidth(IMAGE_WIDTH);
		image.addClickHandler(this);
		return image;
	}

	/**
	 * Adds image to the shortest column.
	 * 
	 * @param image
	 */
	protected void addImageToShortestColumn(GriddyImage image) {
		getShortestColumn().addImage(image);
	}

	/**
	 * Calculates and returns the {@link GriddyColumn} that is shortest at the
	 * moment.
	 * 
	 * @return
	 */
	private GriddyColumn getShortestColumn() {
		int minHeight = Integer.MAX_VALUE;
		GriddyColumn shortestColumn = null;

		for (GriddyColumn column : columns) {
			int height = column.getHeight();
			if (height < minHeight) {
				minHeight = height;
				shortestColumn = column;
			}
		}

		return shortestColumn;
	}

	/**
	 * Resets the view to show all images.
	 */
	public void resetView() {
		if (selectedImage != null) {
			selectedImage.reset();
		}
		setFlipped(false);
	}

	/**
	 * Toggles flipped status.
	 * 
	 * @param flipStatus
	 */
	private void setFlipped(boolean flipStatus) {
		if (flipStatus) {
			addStyleName(FLIP_STYLENAME);
			flipped = true;
		} else {
			removeStyleName(FLIP_STYLENAME);
			flipped = false;
		}
	}

	@Override
	public void onResize(ResizeEvent event) {
		reflow();
	}

	/**
	 * Re-draws columns if required.
	 */
	private void reflow() {
		if (!needsReflow()) {
			return;
		}
		for (GriddyColumn column : columns) {
			remove(column);
		}

		createColumns();
		showImages(currentlyShownImageUrls);
	}

	/**
	 * See if the width has changed enough to warrant re-drawing of columns.
	 * 
	 * @return
	 */
	private boolean needsReflow() {
		return columns.size() != Window.getClientWidth() / COLUMN_WIDTH_PX;
	}

	/**
	 * Registers a new {@link ImageSelectedListener}.
	 * 
	 * @param listener
	 */
	public void addImageSelectedListener(ImageSelectedListener listener) {
		imageSelectedListeners.add(listener);
	}

	/**
	 * Removes given {@link ImageSelectedListener}.
	 * 
	 * @param listener
	 */
	public void removeImageSelectedListener(ImageSelectedListener listener) {
		imageSelectedListeners.remove(listener);
	}

}
