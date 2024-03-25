package component.chart

class Value {
    val x:Double
    val y: Double
    var xLocOff: Float
    var yLocOff: Float
    var xGlobOff: Float
    var yGlobOff: Float

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
        this.xLocOff = 0F
        this.yLocOff = 0F
        this.xGlobOff = 0F
        this.yGlobOff = 0F
    }
}