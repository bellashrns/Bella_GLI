interface Shapes {
    fun getVolume(): Double
}

class Cube(private val s: Double) : Shapes {
    override fun getVolume(): Double {
        return s * s * s
    }
}

class Cuboid(private val l: Double, private val w: Double, private val h: Double) : Shapes {
    override fun getVolume(): Double {
        return l * w * h
    }
}

class Cylinder(private val r: Double, private val h: Double) : Shapes {
    override fun getVolume(): Double {
        return Math.PI * r * r * h
    }
}

class Cone(private val r: Double, private val h: Double) : Shapes {
    override fun getVolume(): Double {
        return (Math.PI * r * r * h) / 3
    }
}

class Sphere(private val r: Double) : Shapes {
    override fun getVolume(): Double {
        return (4 * Math.PI * r * r * r) / 3
    }
}

fun main() {
    val cube = Cube(5.0)
    val cuboid = Cuboid(5.0, 4.0, 3.0)
    val cylinder = Cylinder(5.0, 3.0)
    val cone = Cone(5.0, 3.0)
    val sphere = Sphere(5.0)

    println("Volume of Cube: ${cube.getVolume()}")
    println("Volume of Cuboid: ${cuboid.getVolume()}")
    println("Volume of Cylinder: ${cylinder.getVolume()}")
    println("Volume of Cone: ${cone.getVolume()}")
    println("Volume of Sphere: ${sphere.getVolume()}")
}
