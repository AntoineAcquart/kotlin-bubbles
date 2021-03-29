package mobile.m1.customvie

import mobile.m1.customview.MainActivity


data class MagicCircle(val maxX: Int, val maxY: Int) {

    var color = MainActivity.sColors[(MainActivity.sColors.indices).random()]

    var cx: Float = 50F
    var cy: Float = 50F
    var rad: Float = 40F

    private var delta: Float = (1 until 200).random().toFloat() / 10

    var dx = delta
    var dy = delta

    fun move() {
        when {
            cx < 0 || cx >= maxX -> dx = -dx
            cy < 0 || cy >= maxY -> dy = -dy
        }
        cx += dx
        cy += dy
    }

    fun moveWithSensor(inx: Float, iny: Float) {
        dx -= iny
        dy += inx

        cx += dx
        cy += dy

        if(cx >= maxX - rad) {
            cx = maxX - rad
            dx = - delta
        }
        else if(cx <= rad) {
            cx = rad + 1
            dx = delta
        }

        if (cy >= maxY - rad) {
            cy = maxY - rad
            dy = - delta
        }
        else if(cy <= rad) {
            cy = rad + 1
            dy = delta
        }
    }

}