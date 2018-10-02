package com.bootshop.controller.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * testing for find or set redis
 * @author Guowei Cui
 * @date 8/13/2018 2:32 PM
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate template;


    //For testing1.
    @RequestMapping(value = "/setsession", method = RequestMethod.GET)
    public Map<String, Object> setSession (HttpServletRequest request, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("tessssss ", request.getRequestURL()+"test");   // 设置session值
        map.put("request Url", request.getRequestURL());
//
//        ValueOperations<String, String> ops = this.template.opsForValue();
//        String key = "spring.boot.redis.test";
//        if (!this.template.hasKey(key)) {
//            ops.set(key, "foo");
//        }
//        System.out.println("Found key " + key + ", value=" + ops.get(key));
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        session.setAttribute("test","1234567");
        System.out.println(session.getAttribute("test"));
        map.put("httpSession.id:",uid);

        Object o = request.getSession().getAttribute("springboot");
        if(o == null){
            o = "spring boot with port"+request.getLocalPort()+"generate";
            request.getSession().setAttribute("springboot", o);
        }

        System.out.println("port=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o);

        return map;
    }


    @RequestMapping(value = "/getsession", method = RequestMethod.GET)
    public Object getSettion (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());  // get value
        map.put("message", request.getSession().getAttribute("map"));
        return map;
    }
}
