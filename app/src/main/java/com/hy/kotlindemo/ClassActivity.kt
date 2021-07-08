package com.hy.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hy.kotlindemo.`class`.ViewSealed
import com.hy.kotlindemo.bean.PointJava
import javax.inject.Inject

/**
 * @author hy
 * @date 2021/7/8
 * desc: Kotlin 类
 **/
class ClassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        init()
    }

    private fun init() {
        /**
         * ==========类=========
         */
        // 类的概念就是把数据和处理数据的代码封装成一个单一的实体。在 Java 中，数据存储在一个私有字段中，通过提供访问器方法：getter 和 setter 来访问或者修改数据

        // Java
        val pointJava = PointJava(1, 2)
        // 使用 kotlin 来声明 Point 类则只需要一行代码，两者完全等同
        val pointKotlin = PointKotlin(1, 2)

        // kotlin 也使用关键字 class 来声明类，类声明由类名、类头（指定其类型参数、主构造函数等）以及由花括号包围的类体构成
        // 类头与类体都是可选的，如果一个类没有类体，可以省略花括号。此外，kotlin 中类默认是 public（公有的） 且 final （不可继承）的

        // kotlin 区分了主构造方法（在类体外部声明）和次构造方法（在类体内部声明），一个类可以有一个主构造函数和多个次构造函数，此外也允许在初始化代码块中 init 添加额外的初始化逻辑

        // 主构造函数
        // 主构造函数(constructor修饰)是类头的一部分，跟在类名（和可选的类型参数）后，主构造函数的参数可以是可变的（var）或只读的（val）
        val point = Point(1, 2)
        // 如果主构造函数没有任何注解或者可见性修饰符，可以省略 constructor 关键字
        val point2 = Point2(1, 2)
        val point22 = Point22(1, 2)
        // 如果构造函数有注解或可见性修饰符，则 constructor 关键字是必需的，并且这些修饰符在它前面
        val pointCons = PointCons(1, 2)

        // 主构造函数不能包含任何的代码，初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中
        // 初始化块包含了在类被创建时执行的代码，主构造函数的参数可以在初始化块中使用。如果需要的话，也可以在一个类中声明多个初始化语句块
        // 需要注意的是，构造函数的参数如果用 val/var 进行修饰，则相当于在类内部声明了一个同名的全局属性。如果不加 val/var 进行修饰，则构造函数只能在 init 函数块和全局属性初始化时进行引用

        // 此外，要创建一个类的实例不需要使用 Java 中的 new 关键字，像普通函数一样调用构造函数即可
        val pointInit = PointInit(1, 2)
        // 主构造函数的参数也可以在类体内声明的属性初始化器中使用
        val pointInit2 = PointInit(10, 20)

        // 次构造函数
        // 类也可以声明包含前缀 constructor 的次构造函数。如果类有一个主构造函数，每个次构造函数都需要直接委托给主构造函数或者委托给另一个次构造函数以此进行间接委托，用 this 关键字来进行指定即可
        val point33 = Point33(100)
        val point332 = Point33(100L)
        // 初始化块中的代码实际上会成为主构造函数的一部分，委托给主构造函数会作为次构造函数的第一条语句，因此所有初始化块中的代码都会在次构造函数体之前执行
        // 即使该类没有主构造函数，这种委托仍会隐式发生，并且仍会执行初始化块。如果一个非抽象类没有声明任何（主或次）构造函数，会默认生成一个不带参数的公有主构造函数

        // 属性
        // 在 Java 中，字段和其访问器的组合被称作属性。在 kotlin 中，属性是头等的语言特性，完全替代了字段和访问器方法
        // 在类中声明一个属性和声明一个变量一样是使用 val 和 var 关键字。val 变量只有一个 getter ，var 变量既有 getter 也有 setter
        val user = User()
        println("Kotlin属性访问：" + user.name)
        user.age = 18

        // 自定义访问器
        // 访问器的默认实现逻辑很简单：创建一个存储值的字段，以及返回属性值的 getter 和更新属性值的 setter。如果需要的话，也可以自定义访问器
        // 例如，以下就声明了三个带自定义访问器的属性
        val point44 = Point44(10, 10)
        // 如果仅需要改变一个访问器的可见性或者为其添加注解，那么可以定义访问器而不定义其实现
        val point55 = Point55(10, 10)
        point55.isEquals1
        //以下代码会报错
        //point44.isEquals1 = true

        // 延迟初始化
        // 一般地，非空类型的属性必须在构造函数中初始化，但像使用了 Dagger2 这种依赖注入框架的项目来说就十分的不方便了
        // 为了应对这种情况，可以用 lateinit 修饰符来标记该属性，用于告诉编译器该属性会在稍后的时间被初始化
        // 用 lateinit 修饰的属性或变量必须为非空类型，并且不能是原生类型
        val exampleLazy = ExampleLazy()
        // 如果访问了一个未经过初始化的 lateinit 变量，则会抛出一个包含具体原因（该变量未初始化）的异常信息
        // Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property point has not been initialized

        /**
         * ==========类的分类=========
         */
        // 抽象类
        // 声明为 abstract 的类内部可以包含没有实现体的成员方法，且该成员方法也用 abstract 标记，这种类称为抽象类，没有实现体的方法就称为抽象方法
        // 此外，我们并不需要用 open 标注一个抽象类或者抽象方法，因为这是默认声明的
        val dd = ExampleBase()

        // 数据类
        // 数据类是一种非常强大的类，可以避免重复创建 Java 中的用于保存状态但又操作非常简单的 POJO 的模版代码，它们通常只提供了用于访问它们属性的简单的 getter 和 setter
        // 定义一个新的数据类非常简单，例如
        val data = PointData(1, 2)
        // 数据类默认地为主构造函数中声明的所有属性生成了如下几个方法
        // getter、setter（需要是 var）
        // componentN() 按主构造函数的属性声明顺序进行对应
        // copy()
        // toString()
        // hashCode()
        // equals()

        // 为了确保生成的代码的一致性以及有意义的行为，数据类必须满足以下要求
        // 主构造函数需要包含一个参数
        // 主构造函数的所有参数需要标记为 val 或 var
        // 数据类不能是抽象、开放、密封或者内部的

        // 通过数据类可以简化很多的通用操作，可以很方便地进行：格式化输出变量值、映射对象到变量、对比变量之间的相等性、复制变量等操作
        val data1 = PointData(10, 20)
        val data2 = PointData(10, 20)
        println("data1 toString() : $data1")
        println("data2 toString() : $data2")
        val (x, y) = data1
        println("data1 x is $x,point1 y is $y")
        //在 kotlin 中，“ == ” 相当于 Java 的 equals 方法
        //而 “ === ” 相当于 Java 的 “ == ” 方法
        println("data1 == data2 : ${data1 == data2}")
        println("data1 === data2 : ${data1 === data2}")
        val data3 = data1.copy(y = 30)
        println("data3 toString() : $data3")

        // 密封类
        // Sealed 类（密封类）用于对类可能创建的子类进行限制，用 Sealed 修饰的类的直接子类只允许被定义在 Sealed 类所在的文件中（密封类的间接继承者可以定义在其他文件中）
        // 这有助于帮助开发者掌握父类与子类之间的变动关系，避免由于代码更迭导致的潜在 bug，且密封类的构造函数只能是 private 的

        // 例如，对于 viewSealed 类，其子类只能定义在与之同一个文件里，Sealed 修饰符修饰的类也隐含表示该类为 open 类，因此无需再显式地添加 open 修饰符
        //val viewSealed = ViewSealed()

        // 枚举类
        // kotlin 也提供了枚举的实现，相比 Java 需要多使用 class 关键字来声明枚举
        Day.FRIDAY
        // 枚举可以声明一些参数
        Day2.FRIDAY
        // 此外，枚举也可以实现接口
        Day3.MONDAY

        // 枚举也包含有一些共有函数
        val day = Day2.FRIDAY
        //获取值
        val value = day.index  //5
        //通过 String 获取相应的枚举值
        val value1 = Day.valueOf("SUNDAY") //SUNDAY
        //获取包含所有枚举值的数组
        val value2 = Day.values()
        //获取枚举名
        val value3 = Day.SUNDAY.name //SUNDAY
        //获取枚举声明的位置
        val value4 = Day.TUESDAY.ordinal //2

        // 嵌套类
        // 在 kotlin 中在类里面再定义的类默认是嵌套类，此时嵌套类不会包含对外部类的隐式引用
        val outer = Outer.Nested().foo1()

        // 内部类
        // 如果需要去访问外部类的成员，需要用 inner 修饰符来标注被嵌套的类，这称为内部类。内部类会隐式持有对外部类的引用
        val inner = Outer2().Nested().foo2()

        // 匿名内部类
        // 可以使用对象表达式来创建匿名内部类实例
        val viewTest = ViewTest()
        viewTest.setClickListener(object : OnClickListenerTest {

            override fun onClick() {

            }

        })

        // 内联类
        // 在有些时候，我们需要对原生类型进行包装以便提升程序的健壮性。
        // 例如，对于 sendEmail 方法的入参参数而言，我们无法严格限制入参参数的含义类型，有的开发者可能会将 delay 理解为以毫秒为单位，有的开发者可能又会理解为以分钟为单位
        fun sendEmail(delay: Long) {
            println(delay)
        }

        // 为了提升程序的健壮性，我们可以通过声明一个包装类来作为参数类型
        fun sendEmail(delay: Time) {
            println(delay.second)
        }
        sendEmail(Minute(10).toTime())
        // 这样，在代码源头上就限制了开发者能够传入的参数类型，开发者通过类名就能直接表达出自己希望的时间大小
        // 然而这种方式由于额外的堆内存分配问题，就引入了运行时的性能开销，新的包装类相对原生类型所需要的性能消耗要大得多，可是此时又需要考虑程序的健壮性和可读性，所以包装类也是需要的

        // 内联类（InlineClass）就是为了解决这两者的矛盾而诞生的。上述代码可以改为以下方式来实现
        fun sendEmail2(delay: Time2) {
            println(delay.second)
        }
        sendEmail2(Minute2(10).toTime())
        // 使用 inline 修饰的类就称为内联类，内联类必须含有唯一的一个属性在主构造函数中初始化，在运行时将使用这个唯一属性来表示内联类的实例，从而避免了包装类在运行时的额外开销

        /**
         * ==========接口=========
         */
        // 抽象方法与默认方法
        // kotlin 中的接口与 Java 8 中的类似，可以包含抽象方法的定义以及非抽象方法的实现
        // 不需要使用 default 关键字来标注有默认实现的非抽象方法，但在实现接口的抽象方法时需要使用 override 进行标注
        val viewClick = ViewClickable()
        viewClick.click()
        viewClick.longClick()

        // 如果一个类实现了多个接口，而接口包含带有默认实现且签名相同的方法，此时编译器就会要求开发者必须显式地实现该方法，可以选择在该方法中调用不同接口的相应实现
        val viewClickable2 = ViewClickable2()
        viewClickable2.click()
        viewClickable2.longClick()

        // SAM 接口
        // 对于以下例子，在 Kotlin 1.4 之前第二种写法是不支持的，我们必须完全实现 SelfRunnable 才可以调用 setRunnable 方法
        setRunnable(object : SelfRunnable {
            override fun run() {
                //println("hello,Kotlin")
            }
        })
        // 错误，Kotlin 1.4 之前不支持
        //    setRunnable {
        //        println("hello,Kotlin")
        //    }

        // 而在 Kotlin 1.4 之后，Kotlin 开始支持 SAM 转换。只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口，函数式接口可以有多个非抽象成员，但只能有一个抽象成员
        // SAM 转换即 Single Abstract Method Conversions，对于只有单个非默认抽象方法的接口，可以直接用 Lambda 来表示，前提是 Lambda 所表示的函数类型能够跟接口中的方法签名相匹配

        // 所以，在 Kotlin 1.4 之后，就支持直接以 Lambda 的方式来声明 SelfRunnable 的实现类，从而使得在方法调用上可以更加简洁，但这也要求 interface 同时使用 fun 关键字修饰
        setRunnable {
            //println("hello,Kotlin")
        }

        /**
         * ==========继承=========
         */
        // 在 kotlin 中所有类都有一个共同的超类 Any ，对于没有超类声明的类来说它就是默认超类
        // 需要注意的是， Any 并不是 java.lang.Object ，它除了 equals() 、 hashCode() 与 toString() 外没有其他属性或者函数

        // 要声明一个显式的超类，需要把父类名放到类头的冒号之后
        val subClass = SubClass()

        // 如果派生类有一个主构造函数，其基类型必须直接或间接调用基类的主构造函数
        val subClass2 = SubClass2("Kotlin")
        val subClass22 = SubClass22("Kotlin")

        // 覆盖方法
        // 与 Java 不同，kotlin 需要显式标注可覆盖的成员和覆盖后的成员
        val subClass3 = SubClass3()

        // 属性覆盖
        // 属性覆盖与方法覆盖类似。在超类中声明为 open 的属性，如果要进行覆盖则必须在派生类中重新声明且以 override 开头，并且它们必须具有兼容的类型
        // 每个声明的属性可以由具有初始化器的属性或者具有 getter 方法的属性覆盖
        val base = Base4()
        println(base.x) //10
        println(base.y) //100

        val base1: Base4 = SubClass4()
        println(base1.x) //100
        println(base1.y) //200

        val subClass4 = SubClass4()
        println(subClass4.x) //100
        println(subClass4.y) //200

        // 此外，也可以用一个 var 属性覆盖一个 val 属性，但反之则不行。因为一个 val 属性本质上声明了一个 getter 方法，而将其覆盖为 var 只是在子类中额外声明一个 setter 方法

        // 可以在主构造函数中使用 override 关键字作为属性声明的一部分
        val base5 = Base5()
        println(base5.str) //Base5
        val subClass5 = SubClass5("Kotlin")
        println(subClass5.str) //Kotlin

        // 调用超类实现
        // 派生类可以通过 super 关键字调用其超类的函数与属性访问器的实现
        val subClass6 = SubClass6()
        subClass6.fun1()

        // 对于内部类来说，其本身就可以直接调用调用外部类的函数
        val baseClass3 = BaseClass3()
        baseClass3.InnerClass().fun2()

        // 但如果想要在一个内部类中访问外部类的超类，则需要通过由外部类名限定的 super 关键字来实现
        val subClass44 = SubClass44()
        subClass44.fun1()
        subClass44.InnerClass().fun2()

        // 如果一个类从它的直接超类和实现的接口中继承了相同成员的多个实现， 则必须覆盖这个成员并提供其自己的实现来消除歧义
        // 为了表示采用从哪个超类型继承的实现，使用由尖括号中超类型名限定的 super 来指定，如 super< BaseClass >
        val subClass55 = SubClass55()
        subClass55.fun1()
    }

    class PointKotlin(val x: Int, val y: Int)

    class Point constructor(val x: Int, val y: Int) {

    }

    // 如果主构造函数没有任何注解或者可见性修饰符，可以省略 constructor 关键字
    class Point2(val x: Int, val y: Int) {

    }

    // 如果不包含类体，则可以省略花括号
    class Point22(val x: Int, val y: Int)

    class PointCons public @Inject constructor(val x: Int, val y: Int) {

    }

    class PointInit(val x: Int, val y: Int) {

        // 主构造函数的参数也可以在类体内声明的属性初始化器中使用
        private val localX = x + 1
        private val localY = y + 1

        init {
            println("PointInit:initializer blocks , x value is: $x , y value is: $y")
            println("PointInit:initializer blocks , localX value is: $localX , localY value is: $localY")
        }

    }

    class Point33(val x: Int, val y: Int) {

        init {
            println("Point33:initializer blocks , x value is: $x , y value is: $y")
        }

        constructor(base: Int) : this(base + 1, base + 1) {
            println("Point33:constructor(base: Int)")
        }

        constructor(base: Long) : this(base.toInt()) {
            println("Point33:constructor(base: Long)")
        }

    }

    class User() {
        val name: String = "Kotlin"
        var age: Int = 25
    }

    class Point44(val x: Int, val y: Int) {

        val isEquals1: Boolean
            get() {
                return x == y
            }

        val isEquals2
            get() = x == y

        var isEquals3 = false
            get() = x > y
            set(value) {
                field = !value
            }

    }

    class Point55(val x: Int, val y: Int) {

        var isEquals1: Boolean = false
            get() {
                return x == y
            }
            private set
    }

    class ExampleLazy {
        // 延迟初始化
        lateinit var point: Point

        var point2: Point

        constructor() {
            // 需要在构造函数中初始化
            point2 = Point(10, 20)
        }
    }

    abstract class BaseClass {
        abstract fun fun1()
    }

    class ExampleBase : BaseClass() {
        override fun fun1() {

        }
    }

    data class PointData(val x: Int, val y: Int)

    enum class Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    enum class Day2(val index: Int) {
        SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6)
    }


    interface OnChangedDayListener {
        fun onChangedDay()
    }

    enum class Day3(val index: Int) : OnChangedDayListener {
        SUNDAY(0) {
            override fun onChangedDay() {

            }
        },
        MONDAY(1) {
            override fun onChangedDay() {

            }
        }
    }

    class Outer {

        private val bar = 1

        class Nested {
            fun foo1() = 2
            // 错误
            //fun foo2() = bar
        }
    }

    class Outer2 {

        private val bar = 1

        // 用 inner 修饰符来标注被嵌套的类，这称为内部类
        inner class Nested {
            fun foo1() = 2
            fun foo2() = bar
        }
    }

    interface OnClickListenerTest {
        fun onClick()
    }

    class ViewTest {

        fun setClickListener(clickListener: OnClickListenerTest) {

        }

    }

    class Time(val second: Long)

    class Minute(private val count: Int) {

        fun toTime(): Time {
            return Time(count * 60L)
        }

    }

    inline class Time2(val second: Long)

    inline class Minute2(private val count: Int) {

        fun toTime(): Time2 {
            return Time2(count * 60L)
        }

    }

    interface Clickable {
        fun click()
        fun longClick() = println("interface--Clickable:longClicked")
    }

    interface Clickable2 {
        fun click()
        fun longClick() = println("interface--Clickable2:longClicked2")
    }

    class ViewClickable : Clickable {

        override fun click() {
            println("ViewClickable:clicked")
        }

    }

    class ViewClickable2 : Clickable, Clickable2 {

        override fun click() {
            println("ViewClickable:clicked")
        }

        override fun longClick() {
            super<Clickable>.longClick()
            super<Clickable2>.longClick()
        }

    }

    fun interface SelfRunnable {
        fun run()
    }

    private fun setRunnable(selfRunnable: SelfRunnable) {
        selfRunnable.run()
    }

    //  open 标注与 Java 中的 final 含义相反，用于允许其它类从这个类继承。默认情况下，kotlin 中所有的类都是 final
    open class Base()

    private class SubClass() : Base()

    open class Base2(val str: String)

    class SubClass2(private val strValue: String) : Base2(strValue)

    class SubClass22 : Base2 {

        constructor(strValue: String) : super(strValue)

        constructor(intValue: Int) : super(intValue.toString())

        constructor(doubleValue: Double) : this(doubleValue.toString())
    }

    open class Base3() {
        // 用 open 标注的函数才可以被子类重载
        open fun fun1() {

        }

        fun fun2() {

        }
    }

    class SubClass3() : Base3() {
        // 子类用 override 表示该函数是要对父类的同签名函数进行覆盖。标记为 override 的成员本身也是开放的，也就是说，它可以被子类覆盖
        override fun fun1() {
            super.fun1()
        }
    }

    open class Base4 {
        open val x = 10

        open val y: Int
            get() {
                return 100
            }
    }

    class SubClass4 : Base4() {
        override val x = 100
        override var y = 200
    }

    open class Base5 {
        open val str: String = "Base5"
    }

    class SubClass5(override val str: String) : Base5()

    open class BaseClass2 {
        open fun fun1() {
            println("BaseClass2 fun1")
        }
    }

    class SubClass6 : BaseClass2() {
        override fun fun1() {
            super.fun1()
            println("SubClass6 fun1")
        }
    }

    open class BaseClass3 {
        private fun fun1() {
            println("BaseClass3 fun1")
        }

        inner class InnerClass {
            fun fun2() {
                fun1()
            }
        }
    }

    open class BaseClass4 {
        open fun fun1() {
            println("BaseClass4 fun1")
        }
    }

    class SubClass44 : BaseClass4() {

        override fun fun1() {
            println("SubClass44 fun1")
        }

        inner class InnerClass {

            fun fun2() {
                super@SubClass44.fun1()
            }

        }

    }

    open class BaseClass5 {
        open fun fun1() {
            println("SubClass55 fun1")
        }
    }

    interface BaseInterface {
        // 接口成员默认就是 open 的
        fun fun1() {
            println("BaseInterface fun1")
        }
    }

    class SubClass55() : BaseClass5(), BaseInterface {
        override fun fun1() {
            //调用 SubClass55 的 fun1() 函数
            super<BaseClass5>.fun1()
            //调用 BaseInterface 的 fun1() 函数
            super<BaseInterface>.fun1()
        }
    }

}