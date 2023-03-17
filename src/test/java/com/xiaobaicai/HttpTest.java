package com.xiaobaicai;

import com.xiaobaicai.util.HttpUtl;
import org.junit.Test;

/**
 * @author liguang
 * @date 2023/3/17 星期五 2:17 下午
 */
public class HttpTest {

    @Test
    public void test(){
        String gptResp3_5 = HttpUtl.getChatGPT_3_5_Turbo("this is a test!");
        System.out.println(gptResp3_5);
    }

}
