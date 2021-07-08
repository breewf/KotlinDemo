package com.hy.kotlindemo.`class`

/**
 * @author hy
 * @date 2021/7/8
 * desc:
 **/
sealed class ViewSealed {
    init {

    }

    fun click() {

    }
}

open class Button : ViewSealed() {

}

open class TextView : ViewSealed() {

}

// 因为 Sealed 类的子类对于编译器来说是可控的，所以如果在 when 表达式中处理了所有 Sealed 类的子类，那就不需要再提供 else 默认分支
// 即使以后由于业务变动又新增了 View 子类，编译器也会检测到 check 方法缺少分支检查后报错，所以说 check 方法是类型安全的
fun check(view: ViewSealed): Boolean {
    when (view) {
        is Button -> {
            println("is Button")
            return true
        }
        is TextView -> {
            println("is TextView")
            return true
        }
    }
}
