fun main() {
    val trafficLightColor = "Amber"

    val message =
            when (trafficLightColor) {
                "Red" -> "Stop"
                "Yellow", "Amber" -> "Slow"
                "Green" -> "Go"
                else -> "Invalid traffic-light color"
            }
    println(message)
}
