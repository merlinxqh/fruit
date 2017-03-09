package com.fruit.es.web.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fruit.es.model.EsResource;
import com.fruit.es.service.EsResourceService;

@Controller
public class LeoController {
   
   @Autowired
   private EsResourceService esResourceService;
   
   
   /**
    * 添加索引测试
    * @return
    */
   @RequestMapping(value="addIndex",method=RequestMethod.GET)
   public @ResponseBody JSONObject addIndex(){
	   EsResource es=new EsResource();
	   es.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	   es.setName("深圳市南山区和谐社会有你就好");
	   es.setTitle("美好社会,需要你我共同努力...title");
	   es.setPrice(123.45d);
	   es.setCreateDate(new Date());
	   this.esResourceService.persistObj(es, es.getId());
	   System.out.println(es.getId());
	   return new JSONObject();
   }
   
   @RequestMapping(value="find",method=RequestMethod.GET)
   public @ResponseBody JSONObject find(){
	   JSONObject json=new JSONObject();
	  List<EsResource> elist= esResourceService.findObjects(EsResource.class, "name", "南山区");
	  json.put("list", elist);
	   return json;
   }
}
