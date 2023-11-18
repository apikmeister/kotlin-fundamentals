import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {
    var deviceStatus = "off"
        protected set

    open val deviceType = "unknown"

    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(name = deviceName, category = deviceCategory) {
    override val deviceType = "Smart TV"

    private var speakerVolume = 50
    private var channelNumber = 1

    fun decreaseVolume() {
        if (deviceStatus == "on") {
            if (speakerVolume > 0) speakerVolume--
            println("Speaker volume decreased to $speakerVolume.")
        } else {
            println("TV is off, cannot change volume.")
        }
    }

    fun previousChannel() {
        if (deviceStatus == "on") {
            if (channelNumber > 1) channelNumber--
            println("Switched to channel $channelNumber.")
        } else {
            println("TV is off, cannot change channel.")
        }
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(name = deviceName, category = deviceCategory) {
    override val deviceType = "Smart Light"

    private var brightnessLevel = 50

    fun decreaseBrightness() {
        if (deviceStatus == "on") {
            if (brightnessLevel > 0) brightnessLevel--
            println("Brightness decreased to $brightnessLevel.")
        } else {
            println("Light is off, cannot change brightness.")
        }
    }
}

class SmartHome(val smartTvDevice: SmartTvDevice, val smartLightDevice: SmartLightDevice) {
    var deviceTurnOnCount = 0
        private set

    private fun checkAllDevicesOn(): Boolean {
        return smartTvDevice.deviceStatus == "on" && smartLightDevice.deviceStatus == "on"
    }

    fun decreaseTvVolume() {
        if (checkAllDevicesOn()) {
            smartTvDevice.decreaseVolume()
        }
    }

    fun changeTvChannelToPrevious() {
        if (checkAllDevicesOn()) {
            smartTvDevice.previousChannel()
        }
    }

    fun printSmartTvInfo() {
        if (checkAllDevicesOn()) {
            smartTvDevice.printDeviceInfo()
        }
    }

    fun printSmartLightInfo() {
        if (checkAllDevicesOn()) {
            smartLightDevice.printDeviceInfo()
        }
    }

    fun decreaseLightBrightness() {
        if (checkAllDevicesOn()) {
            smartLightDevice.decreaseBrightness()
        }
    }

    fun turnOnAllDevices() {
        if (smartTvDevice.deviceStatus == "off") {
            smartTvDevice.turnOn()
            deviceTurnOnCount++
        }
        if (smartLightDevice.deviceStatus == "off") {
            smartLightDevice.turnOn()
            deviceTurnOnCount++
        }
    }

    fun turnOffAllDevices() {
        smartTvDevice.turnOff()
        smartLightDevice.turnOff()
        deviceTurnOnCount = 0
    }
}

fun main() {
    val smartTv = SmartTvDevice("Living Room TV", "Entertainment")
    val smartLight = SmartLightDevice("Bedroom Light", "Utility")
    val smartHome = SmartHome(smartTv, smartLight)

    smartHome.turnOnAllDevices()

    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToPrevious()
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
    smartHome.decreaseLightBrightness()

    smartHome.turnOffAllDevices()
}
