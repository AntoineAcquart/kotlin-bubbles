package mobile.m1.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import mobile.m1.customvie.MagicCircle

class CustomView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private lateinit var mCircles: MutableList<MagicCircle>
    private var canvas: Canvas? = null

    private var mPaint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas
        for(mCircle in mCircles) {
            with(mCircle){
                // move()
                mPaint.color = color
                canvas?.drawCircle(cx, cy, rad, mPaint)
            }
        }
        invalidate()
    }

    private fun init() {
        mPaint.color = Color.BLUE
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bot: Int) {
        super.onLayout(changed, left, top, right, bot)
        mCircles = mutableListOf()
        for (i in 0..30) {
            val mCircle = MagicCircle(width, height)
            mCircle.cx = (0 until width).random().toFloat()
            mCircle.cy = (0 until height).random().toFloat()
            mCircle.rad = (0 until 360).random().toFloat()
            mCircles.add(i, mCircle)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        mCircles.forEach{ mCircle ->
            run {
                mCircle.dx = (-20 until 20).random().toFloat()
                mCircle.dy = (-20 until 20).random().toFloat()
            }
        }
        return true
    }

    fun changePositionWithSensor(inx : Float , iny : Float){
        if (this::mCircles.isInitialized) {
            for (mCircle in mCircles) {
                with(mCircle) {
                    moveWithSensor(inx, iny)
                    mPaint.color = color
                    canvas?.drawCircle(cx, cy, rad, mPaint)
                }
            }
        }
        invalidate()
    }


}