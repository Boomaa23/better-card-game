package com.boomaa.bcg.util

import java.awt.Image
import javax.imageio.ImageIO

object Textures {
    fun load(texPath: String): Image {
        return ImageIO.read(this::class.java.classLoader.getResourceAsStream("textures/$texPath"))
    }
}