/**
 * java自带的动态代理有一个缺点就是所要代理的类必需实现一个接口.在实际使用中就会受限制.
 * 幸运的是,我们可以通过cglib来实现无接口类的动态代理. 但是要记住的一点是cglib不能代理final方法
 * Created by tianxiong on 2019/3/18.
 */
package designpattern.cglibproxy;