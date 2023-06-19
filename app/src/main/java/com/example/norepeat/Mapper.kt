package com.example.norepeat

import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

/**
 * @Author LiuHaoQi
 * @Description
 * @Date 2023/4/18 16:15
 */
object Mapper {
    fun <A : Any> toMap(a: A): Map<String, Any?> {
        return a::class.memberProperties.associate { m ->
            val p = m as KProperty<*>
            p.name to p.call(a)
        }
    }
}