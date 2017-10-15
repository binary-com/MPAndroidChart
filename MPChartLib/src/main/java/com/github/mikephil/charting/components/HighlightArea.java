
package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

import com.github.mikephil.charting.utils.Utils;

/**
 * The Highlight Area is an additional feature for all Line-, Bar- and
 * ScatterCharts. It allows the displaying of an additional line in the chart
 * that marks a certain maximum / limit on the specified axis (x- or y-axis).
 *
 * @author Philipp Jahoda
 */
public class HighlightArea extends ComponentBase {

    /** the left / bottom point (the y-value or xIndex) */
    private float mStartPoint = 0f;
    /** the right / top point (the y-value or xIndex) */
    private float mEndPoint = 0f;

    /** the width of the Highlight Area */
    private float mBorderWidth = 0f;

    /** the color of the Highlight Area */
    private int mAreaColor = Color.argb(50, 237, 91, 91);

    /** the color of the area's border */
    private int mBorderColor = Color.argb(100, 255, 255, 255);

    /** the style of the label text */
    private Paint.Style mTextStyle = Paint.Style.FILL_AND_STROKE;

    /** label string that is drawn next to the Highlight Area */
    private String mLabel = "";

    /** the path effect of this HighlightArea that makes dashed lines possible */
    private DashPathEffect mDashPathEffect = null;

    /** indicates the position of the HighlightArea label */
    private AreaLabelPosition mLabelPosition = AreaLabelPosition.RIGHT_TOP;

    /** enum that indicates the position of the HighlightArea label */
    public enum AreaLabelPosition {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
    }

    /**
     * Constructor with limit.
     *
     * @param startPoint - the position (the value) on the y-axis (y-value) or x-axis
     *            (xIndex) where the left side of the area should appear
     * @param endPoint - the position (the value) on the y-axis (y-value) or x-axis
     *            (xIndex) where the right side of the area should appear
     *
     */
    public HighlightArea(float startPoint, float endPoint) {
        mStartPoint = startPoint;
        mEndPoint = endPoint;
    }

    /**
     * Constructor with limit and label.
     *
     * @param startPoint - the position (the value) on the y-axis (y-value) or x-axis
     *            (xIndex) where the left side of the area should appear
     * @param endPoint - the position (the value) on the y-axis (y-value) or x-axis
     *            (xIndex) where the right side of the area should appear
     * @param label - provide "" if no label is required
     */
    public HighlightArea(float startPoint, float endPoint, String label) {
        mStartPoint = startPoint;
        mEndPoint = endPoint;
        mLabel = label;
    }

    /**
     * Returns the start point that is set for this line.
     *
     * @return
     */
    public float getStartPoint() {
        return mStartPoint;
    }

    /**
     * Returns the end point that is set for this line.
     *
     * @return
     */
    public float getEndPoint() {
        return mEndPoint;
    }

    /**
     * Sets the area color for this HighlightArea. Make sure to use
     * getResources().getColor(...)
     *
     * @param color
     */
    public void setAreaColor(int color) {
        mAreaColor = color;
    }

    /**
     * Returns the color that is used for this HighlightArea
     *
     * @return
     */
    public int getAreaColor() {
        return mAreaColor;
    }

    /**
     * set the border width of the chart (min = 0.2f, max = 12f); default 2f NOTE:
     * thinner line == better performance, thicker line == worse performance
     *
     * @param width
     */
    public void setBorderWidth(float width) {

        if (width < 0.2f)
            width = 0.2f;
        if (width > 12.0f)
            width = 12.0f;
        mBorderWidth = Utils.convertDpToPixel(width);
    }

    /**
     * returns the width of border
     *
     * @return
     */
    public float getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * Sets the border color for this HighlightArea. Make sure to use
     * getResources().getColor(...)
     *
     * @param color
     */
    public void setBorderColor(int color) {
        mBorderColor = color;
    }

    /**
     * Returns the color that is used for this HighlightArea
     *
     * @return
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * Enables the border to be drawn in dashed mode, e.g. like this "- - - - - -"
     *
     * @param lineLength the length of the line pieces
     * @param spaceLength the length of space inbetween the pieces
     * @param phase offset, in degrees (normally, use 0)
     */
    public void enableDashedBorder(float lineLength, float spaceLength, float phase) {
        mDashPathEffect = new DashPathEffect(new float[] {
                lineLength, spaceLength
        }, phase);
    }

    /**
     * Disables the line to be drawn in dashed mode.
     */
    public void disableDashedBorder() {
        mDashPathEffect = null;
    }

    /**
     * Returns true if the dashed-line effect is enabled, false if not. Default:
     * disabled
     *
     * @return
     */
    public boolean isDashedBorderEnabled() {
        return mDashPathEffect == null ? false : true;
    }

    /**
     * returns the DashPathEffect that is set for this HighlightArea
     *
     * @return
     */
    public DashPathEffect getDashPathEffect() {
        return mDashPathEffect;
    }

    /**
     * Sets the color of the value-text that is drawn next to the HighlightArea.
     * Default: Paint.Style.FILL_AND_STROKE
     *
     * @param style
     */
    public void setTextStyle(Paint.Style style) {
        this.mTextStyle = style;
    }

    /**
     * Returns the color of the value-text that is drawn next to the HighlightArea.
     *
     * @return
     */
    public Paint.Style getTextStyle() {
        return mTextStyle;
    }

    /**
     * Sets the position of the HighlightArea value label (either on the right or on
     * the left edge of the chart). Not supported for RadarChart.
     *
     * @param pos
     */
    public void setLabelPosition(AreaLabelPosition pos) {
        mLabelPosition = pos;
    }

    /**
     * Returns the position of the HighlightArea label (value).
     *
     * @return
     */
    public AreaLabelPosition getLabelPosition() {
        return mLabelPosition;
    }

    /**
     * Sets the label that is drawn next to the Highlight Area. Provide "" if no
     * label is required.
     *
     * @param label
     */
    public void setLabel(String label) {
        mLabel = label;
    }

    /**
     * Returns the label that is drawn next to the Highlight Area.
     *
     * @return
     */
    public String getLabel() {
        return mLabel;
    }
}
