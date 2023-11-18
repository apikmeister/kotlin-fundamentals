fun main() {
    printFinalTemperature(27.0, "Celsius", "Fahrenheit") { celsiusToFahrenheit(it) }
    printFinalTemperature(350.0, "Kelvin", "Celsius") { kelvinToCelsius(it) }
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin") { fahrenheitToKelvin(it) }
}

fun printFinalTemperature(
        initialMeasurement: Double,
        initialUnit: String,
        finalUnit: String,
        conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement))
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

fun celsiusToFahrenheit(celsius: Double): Double {
    return (celsius * 9 / 5) + 32
}

fun kelvinToCelsius(kelvin: Double): Double {
    return kelvin - 273.15
}

fun fahrenheitToKelvin(fahrenheit: Double): Double {
    return (5 / 9.0) * (fahrenheit - 32) + 273.15
}
