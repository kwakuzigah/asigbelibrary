package com.asigbe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * This view allows the drag and drop for each of this children.
 * 
 * @author Delali Zigah
 */
public class DragAndDropView extends LinearLayout {

	/**
	 * Delegates used by the view to manage the drag and drop.
	 * 
	 * @author Delali Zigah
	 */
	public interface TouchDelegate {
		/**
		 * Callback used when the user touch one of the children of the view.
		 */
		public void onTouchEvent(View view, MotionEvent event);
	}

	private View draggedView;
	private TouchDelegate touchDelegate;
	private Bitmap bitmap;
	private int offset[] = new int[2];
	private int moveLocation[] = new int[2];
	private int left = -1;
	private int top = -1;
	private int offsetLeft = -1;
	private int offsetTop = -1;
	private final Paint paint = new Paint();

	/**
	 * Creates a new drag and drop view with no attributes.
	 */
	public DragAndDropView(Context context) {
		super(context);
		initialize();
	}

	/**
	 * Creates a new drag and drop view with the given attributes.
	 */
	public DragAndDropView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	private void initialize() {
		this.paint.setARGB(170, 170, 170, 170);
	}

	/**
	 * Sets a new delegate.
	 */
	public void setTouchDelegate(TouchDelegate touchDelegate) {
		this.touchDelegate = touchDelegate;
	}

	/**
	 * Gets the view displayed during the drag and drop.
	 */
	public View getDraggedView() {
		return this.draggedView;
	}

	/**
	 * Sets the view displayed during the drag and drop.
	 */
	public void setDraggedView(View draggedView) {
		this.draggedView = draggedView;
		if (draggedView != null) {
			// this.left = this.draggedView.getLeft();
			// this.top = this.draggedView.getTop();
			this.bitmap = draggedView.getDrawingCache();
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		if (this.touchDelegate != null) {
			this.touchDelegate.onTouchEvent(this, event);
		}

		boolean returnValue = super.dispatchTouchEvent(event);
		if (this.draggedView != null) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				this.left = -1;
				this.top = -1;
				this.offsetLeft = (int) (event.getX());
				this.offsetTop = (int) (event.getY());
				this.draggedView.getLocationOnScreen(this.offset);
				break;
			case MotionEvent.ACTION_MOVE:
				getLocationOnScreen(this.moveLocation);
				this.left = (int) (event.getX()) - this.offsetLeft
						+ this.offset[0] - this.moveLocation[0];
				this.top = (int) (event.getY()) - this.offsetTop
						+ this.offset[1] - this.moveLocation[1];
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				cancelDragAndDrop();
				invalidate();
				break;
			}
		}
		return returnValue;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (this.bitmap != null && !this.bitmap.isRecycled() && this.left != -1 && this.top != -1) {
			canvas.drawBitmap(this.bitmap, this.left, this.top, this.paint);
		}
	}

	private void cancelDragAndDrop() {
		this.bitmap = null;
		this.draggedView = null;
		this.left = -1;
		this.top = -1;
	}
}
