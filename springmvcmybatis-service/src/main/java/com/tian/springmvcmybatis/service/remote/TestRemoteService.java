package com.tian.springmvcmybatis.service.remote;

import com.tian.springmvcmybatis.remote.ITestRemoteService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
@Service("testRemoteService")
public class TestRemoteService implements ITestRemoteService {
    public String print(String str) {
        System.out.println(str);
        return "the string receive from remote is: "+str;
    }
}
