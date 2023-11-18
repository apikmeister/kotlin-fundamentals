class Song(val title: String, val artist: String, val yearPublished: Int, var playCount: Int) {
    val isPopular: Boolean
        get() = playCount >= 1000

    fun printSongDescription() {
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}
