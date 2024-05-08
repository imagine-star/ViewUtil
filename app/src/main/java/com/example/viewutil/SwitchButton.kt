package com.example.viewutil

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.example.myview.R

class SwitchButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatCheckBox(context, attrs, defStyleAttr) {

    private val paint: Paint
    private val rectF: RectF
    private val DEFAULT_WIDTH = 200
    private val DEFAULT_HEIGHT = DEFAULT_WIDTH / 8 * 5
    private val backgroundColorUnchecked = getContext().getColor(R.color.gray_D8D8D8)
    private val backgroundColorChecked = getContext().getColor(R.color.blue_line)
    private val buttonColor = getContext().getColor(R.color.white)

    private var buttonCenterXOffset: Float = 0f
        set(value) { field = value }
    private var colorGradientFactor = 1f
        set(value) { field = value }

    init {
        //不显示CheckBox默认的Button
        setButtonDrawable(null)
        //不显示CheckBox默认的背景
        setBackgroundResource(0)
        //默认CheckBox为关闭状态
        isChecked = false

        paint = Paint()
        paint.isAntiAlias = true

        rectF = RectF()
        //点击开始时开始动画
        setOnClickListener {
            startAnimate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val wideMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width = if (wideMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            (paddingLeft + DEFAULT_HEIGHT + paddingRight)
        }
        val height = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            (paddingTop + DEFAULT_HEIGHT + paddingBottom)
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeWidth = measuredWidth / 40f
        paint.color =
            if (isChecked) getCurrentColor(colorGradientFactor, backgroundColorUnchecked, backgroundColorChecked)
            else getCurrentColor(colorGradientFactor, backgroundColorChecked, backgroundColorUnchecked)
        rectF.set(paint.strokeWidth, paint.strokeWidth, measuredWidth - paint.strokeWidth, measuredHeight - paint.strokeWidth)
        canvas.drawRoundRect(rectF, measuredHeight.toFloat(), measuredHeight.toFloat(), paint)
        paint.color = buttonColor
        val radius = (measuredHeight - paint.strokeWidth * 4) / 2
        val x = if (isChecked) measuredWidth - radius - paint.strokeWidth - paint.strokeWidth - buttonCenterXOffset
            else radius + paint.strokeWidth + paint.strokeWidth + buttonCenterXOffset
        val y = measuredHeight / 2f
        canvas.drawCircle(x, y, radius, paint)
    }

    private fun startAnimate() {
        val animatorTime: Long = 500

        val radius = (measuredHeight - paint.strokeWidth * 4) / 2
        val centerXOffset = measuredWidth - (paint.strokeWidth + paint.strokeWidth + radius) * 2
        val animatorSet = AnimatorSet()
        val objectAnimator = ObjectAnimator.ofFloat(this, "buttonCenterXOffset", centerXOffset, 0f)
        objectAnimator.duration = animatorTime
        objectAnimator.addUpdateListener { invalidate() }

        val objectAnimator2 = ObjectAnimator.ofFloat(this, "colorGradientFactor", 0f, 1f)
        objectAnimator2.duration = animatorTime

        animatorSet.play(objectAnimator).with(objectAnimator2)
        animatorSet.start()
    }

    /**
     * Author: QinHao
     * Email:qinhao@jeejio.com
     * Date:2019/6/3 9:04
     * Description:获取一个过渡期中当前颜色,fraction 为过渡系数,取值范围 0f-1f,值越接近 1,颜色就越接近 endColor
     *
     * @param fraction   当前渐变系数
     * @param startColor 过渡开始颜色
     * @param endColor   过渡结束颜色
     * @return 当前颜色
     */
    private fun getCurrentColor(fraction: Float, startColor: Int, endColor: Int): Int {
        val redStart = Color.red(startColor)
        val blueStart = Color.blue(startColor)
        val greenStart = Color.green(startColor)
        val alphaStart = Color.alpha(startColor)

        val redEnd = Color.red(endColor)
        val blueEnd = Color.blue(endColor)
        val greenEnd = Color.green(endColor)
        val alphaEnd = Color.alpha(endColor)

        val redDifference = redEnd - redStart
        val blueDifference = blueEnd - blueStart
        val greenDifference = greenEnd - greenStart
        val alphaDifference = alphaEnd - alphaStart

        val redCurrent = (redStart + fraction * redDifference).toInt()
        val blueCurrent = (blueStart + fraction * blueDifference).toInt()
        val greenCurrent = (greenStart + fraction * greenDifference).toInt()
        val alphaCurrent = (alphaStart + fraction * alphaDifference).toInt()

        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent)
    }

}