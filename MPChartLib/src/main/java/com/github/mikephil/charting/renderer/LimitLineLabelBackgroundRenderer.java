package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by morteza on 11/28/2017.
 */

class LimitLineLabelBackgroundRenderer {
    private ViewPortHandler mViewPortHandler;
    private Paint mLimitLinePaint;
    private LimitLine mLimitLine;
    private Transformer mTrans;
    private float textWidth;
    private float textHeight;
    private float[] point = new float[]{0, 0};

    public LimitLineLabelBackgroundRenderer(ViewPortHandler viewPortHandler, Paint limitLinePaint,
                                            Transformer trans, LimitLine line) {
        this.mViewPortHandler = viewPortHandler;
        this.mLimitLinePaint = limitLinePaint;
        this.mLimitLine = line;
        this.mTrans = trans;
    }

    public void render(Canvas c, Paint bgPaint) {
        Path path;

        this.point[1] = mLimitLine.getLimit();
        mTrans.pointValuesToPixel(point);

        this.textWidth =
                (Utils.calcTextWidth(mLimitLinePaint, mLimitLine.getLabel()) + mLimitLine.getXOffset()) * 1.5f;
        this.textHeight =
                Utils.calcTextHeight(mLimitLinePaint, mLimitLine.getLabel()) * 2;

        if (mLimitLine.getLabelBackground() == LimitLine.LimitLineLabelBackground.RECTANGLE) {
            path = this.calculateRectanglePath();
            CornerPathEffect cornerPathEffect = new CornerPathEffect(20.0f);
            bgPaint.setPathEffect(cornerPathEffect);
        } else if (mLimitLine.getLabelBackground() == LimitLine.LimitLineLabelBackground.POLYGON) {
            path = this.calculatePolygonPath();
        } else {
            return;
        }

        bgPaint.setStyle(mLimitLine.getLabelBackgroundStyle());
        bgPaint.setColor(mLimitLine.getLabelBackgroundColor());

        c.drawPath(path, bgPaint);

        CornerPathEffect cornerPathEffect = new CornerPathEffect(0.0f);
        bgPaint.setPathEffect(cornerPathEffect);
    }

    private Path calculateRectanglePath() {
        Path path = new Path();
        float y = this.point[1];

        if (mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                || mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.RIGHT_TOP) {
            path.moveTo(mViewPortHandler.contentRight(), y + this.textHeight);
            path.lineTo(mViewPortHandler.contentRight() - textWidth, y + textHeight);
            path.lineTo(mViewPortHandler.contentRight() - textWidth, y - textHeight);
            path.lineTo(mViewPortHandler.contentRight(), y - textHeight);
            path.close();
        } else if (mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.LEFT_BOTTOM
                || mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.LEFT_TOP) {
            path.moveTo(mViewPortHandler.contentLeft(), y + this.textHeight);
            path.lineTo(mViewPortHandler.contentLeft() + textWidth, y + textHeight);
            path.lineTo(mViewPortHandler.contentLeft() + textWidth, y - textHeight);
            path.lineTo(mViewPortHandler.contentLeft(), y - textHeight);
            path.close();
        }

        return path;
    }

    private Path calculatePolygonPath() {
        Path path = new Path();
        float y = this.point[1];

        if (mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                || mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.RIGHT_TOP) {
            path.moveTo(mViewPortHandler.contentRight() - (textWidth + 25f), y);
            path.lineTo(mViewPortHandler.contentRight() - textWidth, y + textHeight);
            path.lineTo(mViewPortHandler.contentRight(), y + textHeight);
            path.lineTo(mViewPortHandler.contentRight(), y - textHeight);
            path.lineTo(mViewPortHandler.contentRight() - textWidth, y - textHeight);
            path.lineTo(mViewPortHandler.contentRight() - (textWidth + 25f), y);
            path.close();
        } else if (mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.LEFT_BOTTOM
                || mLimitLine.getLabelPosition() == LimitLine.LimitLabelPosition.LEFT_TOP) {
            path.moveTo(mViewPortHandler.contentLeft() + textWidth + 25f, y);
            path.lineTo(mViewPortHandler.contentLeft() + textWidth, y + textHeight);
            path.lineTo(mViewPortHandler.contentLeft(), y + textHeight);
            path.lineTo(mViewPortHandler.contentLeft(), y - textHeight);
            path.lineTo(mViewPortHandler.contentLeft() + textWidth, y - textHeight);
            path.lineTo(mViewPortHandler.contentLeft() + textWidth + 25f, y);
            path.close();
        }

        return path;
    }
}
