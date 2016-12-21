/**
 * 模版方法:
 *
 * 定义:
 * 在一个方法中定义一个算法的骨架, 而将一些步骤延迟到子类中去实现.
 * 模版方法使得子类在不改变算法结构的情况下,可以重新定义其中的某些步骤.
 *
 * 由抽象父类主导一切, 它拥有算法,而且保护这个算法(final不允许被重写)
 * 对子类来说,父类的存在,可以将代码最大化的进行复用.
 * 算法只存在一个地方,便于修改.
 * 这个模版方法提供了一个框架, 可以让其它的饮料插入进来,新的饮料只要实现自己的方法就可以了.
 * 对于父类来说只关注于算法,具体的实现由子类去完成 .
 * Created by Administrator on 2016/12/17 0017.
 */
package designpattern.model;