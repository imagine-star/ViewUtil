package com.example.myTest

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * 工厂方法测试
 */

fun main(args: Array<String>) {
    val humanFactory = HumanFactory()
    val whiteHuman = humanFactory.createHuman(WhiteHuman::class)
    whiteHuman.getColor()
    whiteHuman.talk()
    val blackHuman = humanFactory.createHuman(BlackHuman::class)
    blackHuman.getColor()
    blackHuman.talk()
    val yellowHuman = humanFactory.createHuman(YellowHuman::class)
    yellowHuman.getColor()
    yellowHuman.talk()
}

class HumanFactory : AbstractHumanFactory() {
    override fun <T : Human> createHuman(cls: KClass<T>): T {
        var human: Human? = null
        try {
            human = cls.createInstance()
        } catch (e: Exception) {
            println("人种生成错误！")
        }
        return human as T
    }

}

abstract class AbstractHumanFactory {
    abstract fun <T: Human> createHuman(cls: KClass<T>): T
}

interface Human {
    fun getColor()

    fun talk()
}

class BlackHuman : Human {
    override fun getColor() {
        println("黑色人种的皮肤颜色是黑色的！")
    }

    override fun talk() {
        println("黑人会说话，一般人听不懂。")
    }

}

class YellowHuman : Human {
    override fun getColor() {
        println("黄色人种的皮肤颜色是黄色的！")
    }

    override fun talk() {
        println("黄色人种会说话，一般说的都是双字节。")
    }

}

class WhiteHuman : Human {
    override fun getColor() {
        println("白色人种的皮肤颜色是白色的！")
    }

    override fun talk() {
        println("白色人种会说话，一般说的都是单字节。")
    }

}