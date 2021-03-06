/**命令模式
 * 定义: 将请求封装成对象, 以便使用不同的请求, 队列或者日志来参数化其它对象.
 * 命令模式也支持可撤销的操作.
 *
 * 命令可能将运算块打包(一个接收者和一组动作), 然后将它传来传去, 就像是一般的对象一样.
 * 现在, 即使在命令对象被创建许久以后, 运算依然可以被调用. 事实上,它甚至可以在不同的线
 * 程中被调用. 我们可以利用这样的特性来衍生出一些应用 , 例如: 日程安排. 线程池 , 工作队列等.
 *
 * 想像有一个工作队列: 你在某一端添加命令, 然后另一端是线程. 线程进行下面的动作: 从队列中取出一个命令,
 * 调用它的execute()方法,等待这个命令完成,然后将这个对象丢弃, 再取出下一个命令......
 * 请注意,工作队列类和进行计算的对象之间是完全松解耦的. 此刻线程可能在进行财务运算, 下一刻却在读取网络数据.
 * 工作队列对象不在乎到底做些什么, 它们只知道取出命令对象后, 调用它的execute()方法. 类似的,它们只要是实现了
 * 命令模式的对象, 就可以放入队列中, 当线程可用时就调用此对象的execute()方法.
 * Created by Administrator on 2016/12/12 0012.
 */
package designpattern.command;