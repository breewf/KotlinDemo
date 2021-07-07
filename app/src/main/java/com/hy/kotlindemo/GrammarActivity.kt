package com.hy.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import java.util.*

/**
 * @author hy
 * @date 2021/7/7
 * desc: Kotlin语法
 **/
class GrammarActivity : AppCompatActivity() {

    private val kotlinName = "Kotlin"

    private val str = "str"
    private val strKotlin: String = "strKotlin"

    private val value = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar)

        init()
    }

    private fun init() {
        // java中存在int，float，boolean等基础类型，这些基础类型在Kotlin里将全部以对象的形式继续存在
        // Int无法自动转换为Double, 需要类型转换
        val a: Int = 2
        val b: Double = a.toDouble()

        // Kotlin中使用var定义变量，使用val定义常量(相当于java当中的final)。定义变量时既可以指定类型，也可以不指定，Kotlin支持类型推断
        // 下面代码等价于 val intIndex = 100，编译器会自动进行类型推导
        val intIndex: Int = 100
        // 以下代码会提示错误，需要进行明确的类型转换
        //val doubleIndex: Double = intIndex

        val intValue1: Int = 1
        val longValue1: Long = 1
        // 以下代码会提示错误，因为两者的数据类型不一致，需要转换为同一类型后才能进行比较
        //println(intValue1 == longValue1)

        // kotlin 与 Java 一样用 String 类型来表示字符串，字符串是不可变的，可以使用索引运算符 [] 来访问包含的单个字符
        // 也可以用 for 循环来迭代字符串，此外也可以用 + 来连接字符串
        println("字符串索引：" + kotlinName[1])
        for (t in kotlinName) {
            println("循环迭代字符串:$t")
        }
        val strAdd = str + "Hello"
        println("+连接字符串:$strAdd")

        // kotlin 支持在字符串字面值中引用局部变量，只需要在变量名前加上字符 $ 即可
        // 此外还可以包含用花括号括起来的表达式，此时会自动求值并把结果合并到字符串中
        val intValue = 100
        // 可以直接包含变量
        // intValue value is 100
        println("intValue value is $intValue")
        // 也可以包含表达式
        //(intValue + 100) value is 200
        println("(intValue + 100) value is ${intValue + 100}")

        // 如果你需要在原始字符串中表示字面值（$）字符（它不支持反斜杠转义），可以用下列语法
        val price = "${'$'}100.99"
        // $100.99
        println(price)

        //创建数组的方法有以下几种
        //用 arrayOf 函数创建一个数组，包含的元素是指定为该函数的实参
        //用 arrayOfNulls 创建一个给定大小的数组，包含的元素均为 null，只能用来创建包含元素类型可空的数组
        //调用 Array 类的构造方法，传递数组的大小和一个 lambda 表达式，调用 lambda 表达式来创建每一个数组元素

        // 包含给定元素的字符串数组
        val array1 = arrayOf("tt", "KK", "oo")
        array1[0] = "Kotlin"
        println(array1[0])
        println(array1[1])
        println("数组长度:" + array1.size)

        // 初始元素均为 null ，大小为 10 的字符数组
        val array2 = arrayOfNulls<String>(10)

        // 创建从 “a” 到 “z” 的字符串数组
        val array3 = Array(26) { i -> ('a' + i).toString() }

        // 需要注意的是，数组类型的类型参数始终会变成对象类型，因此声明 Array<Int> 将是一个包含装箱类型（java.lang.Integer）的数组。
        // 如果想要创建没有装箱的基本数据类型的数组，必须使用一个基本数据类型数组的特殊类。

        // 为了表示基本数据类型的数组，kotlin 为每一种基本数据类型都提供了若干相应的类并做了特殊的优化。
        // 例如，有 IntArray、ByteArray、BooleanArray 等类型，这些类型都会被编译成普通的 Java 基本数据类型数组，比如 int[]、byte[]、boolean[] 等，
        // 这些数组中的值存储时没有进行装箱。需要注意，IntArray 等并不是 Array 的子类。

        // 指定数组大小，包含的元素将是对应基本数据类型的默认值(int 的默认值是 0)
        val intArray = IntArray(5)
        // 指定数组大小以及用于初始化每个元素的 lambda
        val doubleArray = DoubleArray(5) { Random().nextDouble() }
        // 接收变长参数的值来创建存储这些值的数组
        val charArray = charArrayOf('H', 'e', 'l', 'l', 'o')
        val booleanArray = booleanArrayOf(true, false, true, false)

        // Any 类型是 kotlin 所有非空类型的超类型，包括像 Int 这样的基本数据类型
        // 如果把基本数据类型的值赋给 Any 类型的变量，则会自动装箱
        val any: Any = 100
        println(any.javaClass) // class java.lang.Integer

        // 如果想要使变量可以存储包括 null 在内的所有可能的值，则需要使用 Any?
        val any2: Any? = null

        // Unit
        check()
        check2()
        // Unit 是一个完备的类型，可以作为类型参数，但 void 不行
        //interface Test<T> {
        //    fun test(): T
        //}
        //
        //class NoResultClass : Test<Unit> {
        //
        //    //返回 Unit，但可以省略类型说明，函数也不需要显式地 return
        //    override fun test() {
        //
        //    }
        //
        //}

        // Nothing
        // Nothing 类型没有任何值，只有被当做函数返回值使用，或者被当做泛型函数返回值的类型参数使用时才会有意义
        // 可以用 Nothing 来表示一个函数不会被正常终止，从而帮助编译器对代码进行诊断
        //fail("异常")

        /**
         * ==========函数=========
         */
        // kotlin 中的函数以关键字 fun 作为开头，函数名称紧随其后，再之后是用括号包裹起来的参数列表
        // 如果函数有返回值，则再加上返回值类型，用一个冒号与参数列表隔开
        test1("kot", 6)

        getNameLastChar()
        getNameLastChar2()
        getNameLastChar3()

        testA("aa", 1)
        testB("bb", 2)
        testC("cc", 3)

        // 为了增强代码的可读性，kotlin 允许我们使用命名参数，即在调用某函数的时候，可以将函数参数名一起标明，从而明确地表达该参数的含义与作用，
        // 但是在指定了一个参数的名称后，之后的所有参数都需要标明名称
        //compute(age = 110, "leavesC") //错误，在指定了一个参数的名称后，之后的所有参数都需要标明名称
        compute(age = 120, name = "leavesC")
        compute(130, name = "leavesC")

        // 默认参数值
        compute(20)
        compute(24, "Kotlin")

        // 如果使用命名参数，可以省略任何有默认值的参数，而且也可以按照任意顺序传入需要的参数
        compute2("Kotlin2", 10)
        compute2(age = 24)
        compute2(age = 24, name = "Kotlin2")
        compute2(age = 24, value = 90, name = "Kotlin2")
        compute2(value = 90, age = 24, name = "Kotlin2")

        // 可变参数
        // 可变参数可以让我们把任意个数的参数打包到数组中传给函数，kotlin 的语法相比 Java 有所不同，改为通过使用 varage 关键字声明可变参数
        compute3()
        compute3("vararg--aa")
        compute3("vararg--pp", "vararg--oo")
        compute3("vararg--mm", "vararg--kk", "vararg--tt")

        // 在 Java 中，可以直接将数组传递给可变参数，而 kotlin 要求显式地解包数组，以便每个数组元素在函数中能够作为单独的参数来调用
        // 这个功能被称为展开运算符，使用方式就是在数组参数前加一个 *
        val names = arrayOf("arrayOf11", "arrayOf22", "arrayOf33")
        compute4(* names)

        // 局部函数
        // kotlin 支持在函数中嵌套函数，被嵌套的函数称为局部函数
        compute5("Kotlin5", "pp")
        compute5("dd", "aa")

        /**
         * ==========表达式和条件循环==========
         */
        // 语句和表达式
        // 语句是可以单独执行，能够产生实际效果的代码，表现为赋值逻辑、打印操作、流程控制等形式，Java 中的流程控制（if，while，for）等都是语句
        // 表达式可以是一个值、变量、常量、操作符、或它们之间的组合，表达式可以看做是包含返回值的语句

        // if 表达式
        // if 的分支可以是代码块，最后的表达式作为该块的返回值
        val maxValue = if (10 > 20) {
            println("if 表达式 maxValue is 10")
        } else {
            println("if 表达式 maxValue is 20")
        }
        // 与 Java 不同，kotlin 中的 if 是作为表达式存在的，其可以拥有返回值
        getLength(null)
        getLength("length_str")

        // 完全可以用 if 来替代 Java 中的三元运算符
        val list = listOf(1, 4, 10, 4, 10, 30)
        val size = if (list.isNotEmpty()) list.size else null
        // 如果 if 表达式分支是用于执行某个命令，那么此时的返回值类型就是 Unit ，此时的 if 语句就看起来和 Java 的一样了
        val value11 = if (list.isNotEmpty()) println("if 表达式:11") else println("if 表达式:list empty")
        println(value11.javaClass) // class kotlin.Unit
        // 如果将 if 作为表达式而不是语句（例如：返回它的值或者把它赋给变量），该表达式需要有 else 分支

        // when 表达式
        // when 表达式与 Java 中的 switch/case 类似，但是要强大得多。
        // when 既可以被当做表达式使用也可以被当做语句使用，when 将参数和所有的分支条件顺序比较直到某个分支满足条件，然后它会运行右边的表达式。
        // 如果 when 被当做表达式来使用，符合条件的分支的值就是整个表达式的值，如果当做语句使用，则忽略个别分支的值。
        // 与 Java 的 switch/case 不同之处是 when 表达式的参数可以是任何类型，并且分支也可以是一个条件。

        // 和 if 一样，when 表达式每一个分支可以是一个代码块，它的值是代码块中最后的表达式的值，如果其它分支都不满足条件将会求值于 else 分支
        // 如果 when 作为一个表达式使用，则必须有 else 分支，除非编译器能够检测出所有的可能情况都已经覆盖了。如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔
        when (value) {
            in 1..10 -> println("when in 1..10") //区间判断
            100 -> println("when value is 100") //相等判断
            10, 20 -> println("when value is 10 or 20") //多值相等性判断
            is Int -> println("when value is Int") // 类型判断
            else -> println("when else") // 如果以上条件都不满足，则执行 else
        }

        // 返回 when 表达式
        returnWhen(1)
        returnWhen(1L)
        returnWhen("4")
        returnWhen(100.0)

        // when 语句也可以不带参数来使用
        when {
            1 > 5 -> println("when 1 > 5")
            3 > 1 -> println("when 3 > 1")
        }

        // for 循环
        val listFor = listOf(1, 3, 6, 10, 20, 8)
        for (value in listFor) {
            println("for循环:$value")
        }
        // 通过索引来遍历
        val items = listOf("H", "e", "l", "l", "o")
        for (index in items.indices) {
            println("for索引遍历-->>${index}对应的值是：${items[index]}")
        }
        // 也可以在每次循环中获取当前索引和相应的值
        for ((index, value) in listFor.withIndex()) {
            println("for获取当前索引和相应的值-->>index:$index , value :$value")
        }
        // 也可以自定义循环区间
        for (index in 2..8) {
            println("for自定义循环区间:$index")
        }

        // while 和 do/while 循环
        val listWhile = listOf(1, 4, 15, 2, 4, 10, 0, 9)
        var indexWhile = 0
        while (indexWhile < listWhile.size) {
            println("while循环：" + listWhile[indexWhile])
            indexWhile++
        }

        var indexWhile2 = 0
        do {
            println("do while循环：" + listWhile[indexWhile2])
            indexWhile2++
        } while (indexWhile2 < listWhile.size)

        // 返回和跳转
        // return 默认从最直接包围它的函数或者匿名函数返回
        // break 终止最直接包围它的循环
        // continue 继续下一次最直接包围它的循环
        funBreak()

        // kotlin 有函数字面量、局部函数和对象表达式。因此 kotlin 的函数可以被嵌套
        // 标签限制的 return 允许我们从外层函数返回，最重要的一个用途就是从 lambda 表达式中返回。通常情况下使用隐式标签更方便，该标签与接受该 lambda 的函数同名
        funReturn1()
        funReturn2()
        funReturn3()
        funReturn4()
        funReturn5()

        // 区间
        // Ranges 表达式使用一个 .. 操作符来声明一个闭区间，它被用于定义实现一个 RangTo 方法
        // 以下三种声明方式都是等价的
        var indexRange = 5
        if (indexRange >= 0 && indexRange <= 10) {

        }
        if (indexRange in 0..10) {

        }
        if (indexRange in 0.rangeTo(10)) {

        }

        // 数字类型的 ranges 在被迭代时，编译器会将它们转换为与 Java 中使用 index 的 for 循环的相同字节码的方式来进行优化
        // Ranges 默认会自增长，所以像以下的代码就不会被执行
        //for (index in 10..0) {
        //    println(index)
        //}

        // 可以改用 downTo 函数来将之改为递减
        for (index in 10 downTo 0) {
            println("downTo index:$index")
        }

        // 可以在 ranges 中使用 step 来定义每次循环递增或递增的长度
        for (index in 1..8 step 2) {
            println("step index:$index")
        }
        for (index in 8 downTo 1 step 2) {
            println("downTo step index:$index")
        }
        // 以上声明的都是闭区间，如果想声明的是开区间，可以使用 until 函数
        for (index in 0 until 4) {
            println("until index:$index")
        }

        // 扩展函数 reversed() 可用于返回将区间反转后的序列
        val rangTo = 1.rangeTo(3)
        for (i in rangTo) {
            println("rangeTo i:$i") // 1  2  3
        }
        for (i in rangTo.reversed()) {
            println("rangTo.reversed i:$i") // 3 2 1
        }

        /**
         * ==========修饰符==========
         */
        // final 和 open
        // kotlin 中的类和方法默认都是 final 的，即不可继承的，如果想允许创建一个类的子类，需要使用 open 修饰符来标识这个类
        // 此外，也需要为每一个希望被重写的属性和方法添加 open 修饰符
        val button = Button()
        button.click()
        button.longClick()

        // 如果重写了一个基类或者接口的成员，重写了的成员同样默认是 open 的。例如，如果 Button 类是 open 的，则其子类也可以重写其 click() 方法
        val button2 = CompatButton()
        button2.click()

        // public
        // public 修饰符是限制级最低的修饰符，是默认的修饰符。如果一个定义为 public 的成员被包含在一个 private 修饰的类中，那么这个成员在这个类以外也是不可见的

        // protected
        // protected 修饰符只能被用在类或者接口中的成员上。在 Java 中，可以从同一个包中访问一个 protected 的成员
        // 但对于 kotlin 来说，protected 成员只在该类和它的子类中可见。此外，protected 不适用于顶层声明

        // internal
        // 一个定义为 internal 的包成员，对其所在的整个 module 可见，但对于其它 module 而言就是不可见的了
        // 例如，假设我们想要发布一个开源库，库中包含某个类，我们希望这个类对于库本身是全局可见的，但对于外部使用者来说它不能被引用到，此时就可以选择将其声明为 internal 的来实现这个目的

        // private
        // private 修饰符是限制级最高的修饰符，kotlin 允许在顶层声明中使用 private 可见性，包括类、函数和属性，这表示只在自己所在的文件中可见。
        // 所以如果将一个类声明为 private，就不能在定义这个类之外的地方中使用它。
        // 此外，如果在一个类里面使用了 private 修饰符，那访问权限就被限制在这个类里面，继承这个类的子类也不能使用它。
        // 所以如果类、对象、接口等被定义为 private，那么它们只对被定义所在的文件可见。如果被定义在了类或者接口中，那它们只对这个类或者接口可见。

        // 修饰符	         类成员	        顶层声明
        // public（默认）	 所有地方可见	    所有地方可见
        // internal	         模块中可见	    模块中可见
        // protected	     子类中可见
        // private	         类中可见	    文件中可见

        /**
         * ==========空安全==========
         */
        // 可空性
        // 在 kotlin 中，类型系统将一个引用分为可以容纳 null （可空引用）或者不能容纳 null（非空引用）两种类型。 例如，String 类型的常规变量不能指向 null
        var name: String = "leavesC"
        //编译错误
        //name = null

        // 如果希望一个变量可以储存 null 引用，需要显式地在类型名称后面加上问号
        var name2: String? = "leavesC"
        name2 = null

        // 问号可以加在任何类型的后面来表示这个类型的变量可以存储 null 引用：Int?、Double? 、Long? 等
        // kotlin 对可空类型的显式支持有助于防止 NullPointerException 导致的异常问题，编译器不允许不对可空变量做 null 检查就直接调用其属性。
        // 这个强制规定使得开发者必须在编码初期就考虑好变量的可赋值范围并为其各个情况做好分支处理
        checkNull(null)
        checkNull2(null)

        // 安全调用运算符：?.
        // 安全调用运算符：?. 允许把一次 null 检查和一次方法调用合并为一个操作，如果变量值非空，则方法或属性会被调用，否则直接返回 null
        checkNullSafe("Hello Null")
        checkNullSafe2(null)

        // Elvis 运算符：?:
        // Elvis 运算符：?: 用于替代 ?. 直接返回默认值 null 的情况
        // Elvis 运算符接收两个运算数，如果第一个运算数不为 null ，运算结果就是其运算结果值，如果第一个运算数为 null ，运算结果就是第二个运算数
        checkNullElvis("Elvis")
        checkNullElvis2(null)

        // 安全转换运算符：as?
        // 安全转换运算符：as? 用于把值转换为指定的类型，如果值不适合该类型则返回 null
        checkSafeAs("999")

        // 非空断言：!!
        // 非空断言用于把任何值转换为非空类型，如果对 null 值做非空断言，则会抛出异常
        var cc: String? = "leavesC"
        checkNotNull(cc) //7
        cc = null
        checkNotNull(cc) //kotlinNullPointerException

        // 可空类型的扩展
        // 为可空类型定义扩展函数是一种更强大的处理 null 值的方式，可以允许接收者为 null 的调用，并在该函数中处理 null ，而不是在确保变量不为 null 之后再调用它的方法
        // 例如，如下方法可以被正常调用而不会发生空指针异常
        val canNull: String? = null
        canNull.isNullOrEmpty() //true
        // isNullOrEmpty() 的方法签名如下所示，可以看到这是为可空类型 CharSequence? 定义的扩展函数，方法中已经处理了方法调用者为 null 的情况

        /**
         * ==========类型的检查与转换==========
         */
        // 类型检查
        // is 与 !is 操作符用于在运行时检查对象是否符合给定类型
        val intValueIs = 100
        println("类型检查 int:" + (intValueIs is Int))
        val strValueIs = "类型检查 is"
        println("类型检查 str:" + (strValueIs is String))
        // 同时，is 操作符也附带了一个智能转换功能。在许多情况下，不需要在 kotlin 中使用显式转换操作符，因为编译器跟踪不可变值的 is 检查以及显式转换，并在需要时自动插入安全的转换
        // 例如，在上面的例子中，当判断 value 为 String 类型通过时，就可以直接将 value 当做 String 类型变量并调用其内部属性，这个过程就叫做智能转换
        // 编译器会指定根据上下文环境，将变量智能转换为合适的类型

        // || 右侧的 value 被自动隐式转换为字符串，所以可以直接访问其 length 属性
        if (strValueIs !is String || strValueIs.length > 0) {

        }
        // && 右侧的 value 被自动隐式转换为字符串，所以可以直接访问其 length 属性
        if (strValueIs is String && strValueIs.length > 0) {

        }

        // 不安全的转换操作符
        // 如果转换是不可能的，转换操作符 as 会抛出一个异常。因此，我们称之为不安全的转换操作符
        parserType("leavesC") //value is String , length is 7
        parserType(10) //会抛出异常 ClassCastException

        // 安全的转换操作符
        // 可以使用安全转换操作符 as? 来避免在转换时抛出异常，它在失败时返回 null
        val xxx = null
        val yyy: String? = xxx as? String


    }

    /**
     * kotlin 中的 Unit 类型类似于 Java 中的 void，可以用于函数没有返回值时的情况
     */
    private fun check(): Unit {

    }

    /**
     * 如果返回值为 Unit，则可以省略该声明
     */
    private fun check2() {

    }

    /**
     * Nothing 类型没有任何值，只有被当做函数返回值使用，
     * 或者被当做泛型函数返回值的类型参数使用时才会有意义，
     * 可以用 Nothing 来表示一个函数不会被正常终止，从而帮助编译器对代码进行诊断
     */
    fun fail(message: String): Nothing {
        throw IllegalStateException(message)
    }

    /**
     * 带有两个不同类型的参数，一个是 String 类型，一个是 Int 类型
     * 返回值为 Int 类型
     */
    private fun test1(str: String, int: Int): Int {
        return str.length + int
    }

    /**
     * fun 用于表示声明一个函数，getNameLastChar 是函数名
     * 空括号表示该函数无传入参数，Char 表示函数的返回值类型是字符
     */
    private fun getNameLastChar(): Char {
        return kotlinName.get(kotlinName.length - 1)
    }

    /**
     * 表达式函数体的返回值类型可以省略，返回值类型可以自动推断，
     * 这种用单行表达式与等号定义的函数叫做表达式函数体。
     * 但对于一般情况下的有返回值的代码块函数体，必须显式地写出返回类型和 return 语句
     */
    //getNameLastChar 函数的返回值类型以及 return 关键字是可以省略的
    //返回值类型可以由编译器根据上下文进行推导
    //因此，函数可以简写为以下形式
    private fun getNameLastChar2() = kotlinName.get(kotlinName.length - 1)
    private fun getNameLastChar3() = kotlinName[kotlinName.length - 1]

    /**
     * 如果函数没有有意义的返回值，则可以声明为 Unit ，也可以省略 Unit
     * 以下三种写法都是等价的
     */
    private fun testA(str: String, int: Int): Unit {
        println("fun:" + (str.length + int))
    }

    private fun testB(str: String, int: Int) {
        println("fun:" + (str.length + int))
    }

    private fun testC(str: String, int: Int) = println("fun:" + (str.length + int))


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

    /**
     * 与 Java 不同，kotlin 中的 if 是作为表达式存在的，其可以拥有返回值
     */
    private fun getLength(str: String?): Int {
        return if (str.isNullOrBlank()) 0 else str.length
    }

    /**
     * 返回 when 表达式
     */
    private fun returnWhen(obj: Any): String =
        when (obj) {
            1 -> "value is 1"
            "4" -> "value is string 4"
            is Long -> "value type is long"
            else -> "unknown"
        }

    /**
     * 循环和跳转
     *
     * 在 kotlin 中任何表达式都可以用标签（label）来标记，标签的格式为标识符后跟 @ 符号，例如：abc@ 、fooBar@ 都是有效的标签
     */
    private fun funBreak() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        loop@ for (it in list) {
            if (it == 8) {
                continue
            }
            if (it == 23) {
                break@loop
            }
            println("循环和跳转：value is $it")
        }
        println("循环和跳转：function end")
    }

    /**
     * 标签限制的 return 允许我们从外层函数返回，最重要的一个用途就是从 lambda 表达式中返回
     * 通常情况下使用隐式标签更方便，该标签与接受该 lambda 的函数同名
     */
    private fun funReturn1() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach {
            if (it == 8) {
                return
            }
            println("value is $it")
        }
        println("function end")

        //    value is 1
        //    value is 4
        //    value is 6
    }

    private fun funReturn2() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach {
            if (it == 8) {
                return@funReturn2
            }
            println("value is $it")
        }
        println("function end")

        //    value is 1
        //    value is 4
        //    value is 6
    }

    /**
     * funReturn3() 和 funReturn4() 中使用的局部返回类似于在常规循环中使用 continue
     */
    private fun funReturn3() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach {
            if (it == 8) {
                return@forEach
            }
            println("value is $it")
        }
        println("function end")

        //    value is 1
        //    value is 4
        //    value is 6
        //    value is 12
        //    value is 23
        //    value is 40
        //    function end
    }

    private fun funReturn4() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach loop@{
            if (it == 8) {
                return@loop
            }
            println("value is $it")
        }
        println("function end")

        //    value is 1
        //    value is 4
        //    value is 6
        //    value is 12
        //    value is 23
        //    value is 40
        //    function end
    }

    private fun funReturn5() {
        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) {
                // 局部返回到匿名函数的调用者，即 forEach 循环
                return
            }
            println("funReturn5:value is $value")
        })
        println("funReturn5:function end")

        //funReturn5:value is 1
        //funReturn5:value is 2
        //funReturn5:value is 4
        //funReturn5:value is 5
        //funReturn5:function end
    }

    open class View {
        open fun click() {

        }

        // 不能在子类中被重写
        fun longClick() {

        }
    }

    open class Button : View() {
        override fun click() {
            super.click()
        }
    }

    class CompatButton : Button() {
        override fun click() {
            super.click()
        }
    }

    /**
     * 编译器不允许不对 name 做 null 检查就直接调用其属性
     */
    private fun checkNull(name: String?): Boolean {
        //error，编译器不允许不对 name 做 null 检查就直接调用其属性
        //return name.isNotEmpty()
        return true
    }

    /**
     * 显式地进行 null 检查
     */
    private fun checkNull2(name: String?): Boolean {
        if (name != null) {
            return name.isNotEmpty()
        }
        return false
    }

    private fun checkNullSafe(name: String?) {
        if (name != null) {
            println("checkNullSafe:" + name.toUpperCase())
        } else {
            println("checkNullSafe:" + null)
        }
    }

    /**
     * 等同 checkNullSafe()
     */
    private fun checkNullSafe2(name: String?) {
        println("checkNullSafe2:" + name?.toUpperCase())
    }


    private fun checkNullElvis(name: String?) {
        if (name != null) {
            println("checkNullElvis:$name")
        } else {
            println("checkNullElvis:default")
        }
    }

    /**
     * 等同 checkNullElvis()
     */
    private fun checkNullElvis2(name: String?) {
        println("checkNullElvis2:" + (name ?: "default"))
    }

    /**
     * 安全转换运算符：as? 用于把值转换为指定的类型，如果值不适合该类型则返回 null
     */
    private fun checkSafeAs(any: Any?) {
        val result = any as? String
        //println(result ?: println("checkSafeAs: is not String"))
    }

    /**
     * 非空断言：!!
     * 非空断言用于把任何值转换为非空类型，如果对 null 值做非空断言，则会抛出异常
     */
    private fun checkNotNull(name: String?) {
        //println(name!!.length)
    }

    /**
     * 如果转换是不可能的，转换操作符 as 会抛出一个异常。因此，我们称之为不安全的转换操作符
     */
    private fun parserType(value: Any) {
        val tempValue = value as String
        println("parserType:value is String , length is ${tempValue.length}")
    }

}