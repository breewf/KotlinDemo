package com.hy.kotlindemo.bean;

/**
 * @author hy
 * @date 2021/7/8
 * desc: 在 Java 中以下的示例代码是很常见的，通过构造函数把参数赋值给有着相同名称的字段，通过 getter 来获取属性值
 **/
public class PointJava {

    private final int x;

    private final int y;

    public PointJava(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }
}
