package com.fruit.pms.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fruit.growup.api.GrowUpPlanApiService;

@Controller
public class LeoController {
	
//	@Reference(version="1.0.0",interfaceClass=GrowUpPlanApiService.class,check=false,timeout=5000,retries=3)
	@Autowired
	private GrowUpPlanApiService growUpPlanApiService;
	
   
	@RequestMapping("dubbo")
	public @ResponseBody Object dubbo(){
		System.out.println("ddddddd");
		return growUpPlanApiService.getList();
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		Map<String, Object> table=new Hashtable<String, Object>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("key", "1");
		map.put("key2", "222");
		map.put(null, "abc");
		System.out.println(map.size());
		
//		map.putIfAbsent("", "");
		List<String> list=new ArrayList<String>();
		List<String> link=new LinkedList<String>();
//		String a="a";
//		String b="b";
//		String ab="ab";
//		System.out.println(a+b == ab);//a+b两个变量相加  需要在运行时才能确定其值, 到运行时jvm会为两者相加后产生一个新的对象
//		System.out.println("a"+"b" == ab);//在编译时 a+b 就变成 ab了
//		
//		final String afinal="a";
//		String result=afinal+"b";
//		System.out.println(result == ab);//result=afinal+”b”，afinal是个final的变量， result在编译时也已经被转变为了”ab”，和”ab”在常量池中同样为同一地址，因此result==ab为true。
//		String plus=a+"b";
//		System.out.println(plus == ab);
//		System.out.println(plus.intern() == ab);//这里的不同点在于调用了plus.intern()方法，这个方法的作用是获取plus指向的常量池地址，因此plus.intern()==ab为true。
	}
}
