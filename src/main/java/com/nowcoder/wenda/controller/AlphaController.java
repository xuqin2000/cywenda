package com.nowcoder.wenda.controller;

import com.nowcoder.wenda.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired //注入service，访问service处理业务
    private AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello springboot";
    }


    @RequestMapping("/find")
    @ResponseBody
    public String find() {
        return alphaService.find();
    }


    //Get请求传参方式1：拼接在路径后 @RequestParam
    @RequestMapping(path = "/getstudent", method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name) {
        System.out.println(id);
        System.out.println(name);
        return "student";
    }

    //Get请求传参方式2：拼接在下一路径 @PathVariable
    @RequestMapping(path = "/getstudents/{id}/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String show2(@PathVariable("id") int id, @PathVariable("name") String name) {
        System.out.println(id);
        System.out.println(name);
        return "student2";
    }

    //POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String savestudent( String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据 方式1： 使用ModelAndView，返回此类型
    @RequestMapping("/showview")
    public ModelAndView getuser(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","15");
        mav.setViewName("demo/view");
        return mav;
    }

    //响应HTML数据 方式2：使用model 返回String类型
    @RequestMapping("/showview2")
    public String getuser2(Model model){
        model.addAttribute("name","李四");
        model.addAttribute("age","17");
        return "demo/view";
    }

    //响应json数据
    @RequestMapping("/showjson")
    @ResponseBody
    public Map<String,Object> getjson(){
      Map<String,Object> map=new HashMap<>();
      map.put("name","张三");
      map.put("age","19");
      return map;
    }

    //响应json数据(多组值)
    @RequestMapping("/showjson2")
    @ResponseBody
    public List <Map<String,Object>> getjson2(){
        List <Map<String,Object>>list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age","19");
        list.add(map);

        map=new HashMap<>();
        map.put("name","李四");
        map.put("age","20");
        list.add(map);

        map=new HashMap<>();
        map.put("name","王五");
        map.put("age","22");
        list.add(map);
        return list;
    }

}
