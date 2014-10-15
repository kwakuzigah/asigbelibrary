package com.asigbe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * A custom gridview which doesn't care if the view has the focus for touch events.
 * 
 * @author Delali Zigah
 */
public class CustomGridView extends GridView {
    
    /**
     * Listener used to listen to touch event on items.
     * @author Delali Zigah
     */
    public static interface OnItemTouchListener {
	/**
	 * Called when an item is touched.
	 * @param position
	 */
	public void onItemTouch(int position);
    }

    private int pointToPosition;
    private OnItemTouchListener onItemTouchListener;

    /**
     * Creates a custom grid view.
     */
    public CustomGridView(Context context) {
        super(context);
    }

    /**
     * Creates a custom grid view with the given attributes.
     */
    public CustomGridView(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    /**
     * Creates a custom grid view with the given attributes and style.
     */
    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            this.pointToPosition = pointToPosition((int)ev.getX(), (int)ev.getY());
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (this.onItemTouchListener != null) {
        	this.onItemTouchListener.onItemTouch(this.pointToPosition);
            }
        }
        return super.onTouchEvent(ev);
    }
    
    /**
     * Adds a listener called when an item is touched.
     */
    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
	this.onItemTouchListener = onItemTouchListener;
    }
}
