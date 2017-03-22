package com.tian.springmvcmybatis.controller.test;

import com.tian.springmvcmybatis.controller.BaseController;
import com.tian.springmvcmybatis.service.common.ResponseData;
import com.tian.springmvcmybatis.service.IUserService;
import com.tian.springmvcmybatis.service.common.ConsumerService;
import com.tian.springmvcmybatis.service.common.ProducerService;
import com.tian.springmvcmybatis.service.common.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试用的一些方法入口
 * Created by Administrator on 2016/11/28 0028.
 */
@Controller("testController")
@RequestMapping("test")
public class TestController extends BaseController{
    @Autowired
    private IUserService userService;
    //队列名gzframe.demo
    @Resource(name="demoQueueDestination")
    private Destination demoQueueDestination;
    @Autowired
    private ProducerService producerService;
    @Autowired
    private ConsumerService consumerService;
    /**
     * 测试getBean
     * @param request
     * @return
     */
    @RequestMapping("test_get_bean")
    @ResponseBody
    public ResponseData testGetBean(HttpServletRequest request){
        ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        TestController o = (TestController)ac2.getBean("testController");
        System.out.println(o.toString());
        return success;
    }

    /**
     * 测试事务控制(单个数据源)
     * @return
     */
    @RequestMapping("test_trancation")
    @ResponseBody
    public ResponseData testTrancation(){
        userService.testTranscation();
        return success;
    }

    /**
     * 测试事务控制(多个数据源)
     * @return
     */
    @RequestMapping("test_trancation2")
    @ResponseBody
    public ResponseData testTrancation2(){
        userService.testTranscation2();
        return success;
    }

    /**
     * 测试事务控制(从数据源)
     * @return
     */
    @RequestMapping("test_trancation3")
    @ResponseBody
    public ResponseData testTransaction3(){
        userService.testTransaction3();
        return success;
    }

    /**
     * 测试Session相关的一些功能
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("test_session")
    @ResponseBody
    public ResponseData testSession(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getSession());
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttributeNames());
        System.out.println(request.getSession().toString());
        return successData.setData(request.getSession().getId());
    }

    /**
     * 测试通过activemq发送消息
     * @param message
     * @return
     */
    @RequestMapping("test_send_message")
    @ResponseBody
    public ResponseData testSendMessage(String message){
        producerService.sendMessage(demoQueueDestination,message);
        return successData.setData("成功发送消息到:"+demoQueueDestination.toString()+message);
    }

    /**
     * 测试通过activemq接收消息
     *
     * 在没有配置监听器的时候,消息需要主动去获取
     * @return
     * @throws Exception
     */
    @RequestMapping("test_receive_message")
    @ResponseBody
    public ResponseData testReceiveMessage() throws Exception{
        TextMessage textMessage = consumerService.receive(demoQueueDestination);
        if(textMessage == null){
            return successData.setData("没有消息了");
        }
        System.out.println("从通道"+demoQueueDestination.toString()+"收到消息:"+textMessage.getText());
        return successData.setData("从通道"+demoQueueDestination.toString()+"收到消息:"+textMessage.getText());
    }

    /**
     * 测试通过request获取的BufferReader都有哪些内容
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("test_bufferReader")
    @ResponseBody
    public ResponseData testBufferReader(HttpServletRequest request,HttpServletResponse response) throws Exception{
        BufferedReader bufferedReader = request.getReader();
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
        }
        return successData.setData(stringBuffer.toString());
    }

    @RequestMapping("testPush")
    @ResponseBody
    public ResponseData testPush(HttpServletRequest request) throws IOException {
        System.out.println("请示参数"+request.getReader().readLine());

        return success;
    }

    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getResource();
//        jedis.psubscribe();
        List<String> list = new ArrayList();
        String[] strArr = list.toArray(new String[1]);
    }
}
