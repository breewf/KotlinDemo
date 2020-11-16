package com.hy.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class GrammarActivity : AppCompatActivity() {

    private val kotlinName = "Kotlin"
    private var name = "Kotlin"

    private val intValue1 = 100
    private val intValue2: Int = 100

    private val longValue: Long = 1

    private val str = "Kotlin"
    private val str2: String = "Kotlin"

    private var doubleIndex: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar)

        init()
    }

    private fun init() {
        // 数字类型不会自动转型，必须要进行明确的类型转换
        doubleIndex = intValue1.toDouble()

        val intValue = 100
        // 可以直接包含变量
        // intValue value is 100
        println("intValue value is $intValue")
        // 也可以包含表达式
        //(intValue + 100) value is 200
        println("(intValue + 100) value is ${intValue + 100}")

        val price = "${'$'}100.99"
        // $100.99
        println(price)

        // 包含给定元素的字符串数组
        val array1 = arrayOf("tt", "KK", "oo")
        array1[0] = "Kotlin"
        println(array1[0])
        println(array1[1])
        println(array1.size)

        // 初始元素均为 null ，大小为 10 的字符数组
        val array2 = arrayOfNulls<String>(10)

        // 创建从 “a” 到 “z” 的字符串数组
        val array3 = Array(26) { i -> ('a' + i).toString() }

        // 指定数组大小，包含的元素将是对应基本数据类型的默认值(int 的默认值是 0)
        val intArray = IntArray(5)
        // 指定数组大小以及用于初始化每个元素的 lambda
        val doubleArray = DoubleArray(5) { Random().nextDouble() }
        val charArray = charArrayOf('H', 'e', 'l', 'l', 'o')
        val booleanArray = booleanArrayOf(true, false, true, false)

        // Any 类型是 kotlin 所有非空类型的超类型，包括像 Int 这样的基本数据类型
        // 如果把基本数据类型的值赋给 Any 类型的变量，则会自动装箱
        val any: Any = 100
        println(any.javaClass) // class java.lang.Integer

        // 如果想要使变量可以存储包括 null 在内的所有可能的值，则需要使用 Any?
        val any2: Any? = null

        println(test1("kot", 6))
        println(test1("lin", 3))

        compute(20)
        compute(24, "Kotlin")

        compute2("Kotlin2", 10)
        compute2(age = 24)
        compute2(age = 24, name = "Kotlin2")
        compute2(age = 24, value = 90, name = "Kotlin2")
        compute2(value = 90, age = 24, name = "Kotlin2")

        compute3()
        compute3("Kotlin3")
        compute3("Kotlin3", "ll")
        compute3("Kotlin3", "kk", "tt")

        val names = arrayOf("Kotlin4", "aa", "bb")
        compute4(* names)

        compute5("Kotlin5", "pp")
        compute5("dd", "aa")
    }

    /**
     * kotlin 中的 Unit 类型类似于 Java 中的 void，可以用于函数没有返回值时的情况
     */
    fun check(): Unit {

    }

    /**
     * 如果返回值为 Unit，则可以省略该声明
     */
    fun check2() {

    }

    /**
     * Nothing 类型没有任何值，只有被当做函数返回值使用，
     * 或者被当做泛型函数返回值的类型参数使用时才会有意义，
     * 可以用 Nothing 来表示一个函数不会被正常终止，从而帮助编译器对代码进行诊断
     */
    fun fail(message: String): Nothing {
        throw IllegalStateException(message)
    }

    private fun test1(str: String, int: Int): Int {
        return str.length + int
    }

    // fun 用于表示声明一个函数，getNameLastChar 是函数名
    // 空括号表示该函数无传入参数，Char 表示函数的返回值类型是字符
    fun getNameLastChar(): Char {
        return name.get(name.length - 1)
    }

    /**
     * 表达式函数体的返回值类型可以省略，返回值类型可以自动推断，
     * 这种用单行表达式与等号定义的函数叫做表达式函数体。
     * 但对于一般情况下的有返回值的代码块函数体，必须显式地写出返回类型和 return 语句
     */
    //getNameLastChar 函数的返回值类型以及 return 关键字是可以省略的
    //返回值类型可以由编译器根据上下文进行推导
    //因此，函数可以简写为以下形式
    fun getNameLastChar2() = name.get(name.length - 1)
    fun getNameLastChar3() = name[name.length - 1]

    /**
     * 如果函数没有有意义的返回值，则可以声明为 Unit ，也可以省略 Unit
     * 以下三种写法都是等价的
     */
    fun testA(str: String, int: Int): Unit {
        println(str.length + int)
    }

    fun testB(str: String, int: Int) {
        println(str.length + int)
    }

    fun testC(str: String, int: Int) = println(str.length + int)


    /**
     * 可以在声明函数的时候指定参数的默认值，从而避免创建重载的函数
     */
    private fun compute(age: Int, name: String = "Kotlin") {

    }

    /**
     * 如果按照常规的调用语法时，必须按照函数声明定义的参数顺序来给定参数，可以省略的只有排在末尾的参数
     * 如果使用命名参数，可以省略任何有默认值的参数，而且也可以按照任意顺序传入需要的参数
     */
    private fun compute2(name: String = "Kotlin", age: Int, value: Int = 100) {

    }

    /**
     * 可变参数可以让我们把任意个数的参数打包到数组中传给函数，
     * kotlin 的语法相比 Java 有所不同，改为通过使用 varage 关键字声明可变参数
     */
    private fun compute3(vararg name: String) {
        name.forEach { println(it) }
    }

    /**
     * 在 Java 中，可以直接将数组传递给可变参数，
     * 而 kotlin 要求显式地解包数组，以便每个数组元素在函数中能够作为单独的参数来调用，
     * 这个功能被称为展开运算符，使用方式就是在数组参数前加一个 *
     */
    private fun compute4(vararg name: String) {
        name.forEach { println(it) }
    }

    /**
     * kotlin 支持在函数中嵌套函数，被嵌套的函数称为局部函数
     */
    private fun compute5(name: String, country: String) {
        fun check(string: String) {
            if (string.isEmpty()) {
                throw IllegalArgumentException("参数错误")
            }
        }
        check(name)
        check(country)
    }
}