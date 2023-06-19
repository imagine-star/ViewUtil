package com.example.myview

import com.example.extension.easyPrint
import com.example.model.MyData
import com.example.norepeat.Mapper

/**
 * @Author LiuHaoQi
 * @Description
 * @Date 2023/4/18 16:33
 */

fun main() {
    val data = MyData("test", "value", 6)
    val map = Mapper.toMap(data)
    map.values.forEach {
        it.easyPrint()
    }
}