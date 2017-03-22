package com.fruit.es.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fruit.base.framework.utils.IDGeneratorUtil;
import com.fruit.cache.util.RedisUtil;
import com.fruit.es.model.EsResource;
import com.fruit.es.service.EsResourceService;

@Controller
public class LeoController {
   
   @Autowired
   private EsResourceService esResourceService;
   
   @Autowired
   private RedisUtil redisUtil;
   
   
   /**
    * 添加索引测试
    * @return
    */
   @RequestMapping(value="addIndex",method=RequestMethod.GET)
   public @ResponseBody JSONObject addIndex(String name,String title){
	   EsResource es=new EsResource();
	   es.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	   es.setName(name);
	   es.setTitle(title);
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
   
   @RequestMapping(value="redis",method=RequestMethod.GET)
   public @ResponseBody JSONObject redis(){
	   JSONObject json=new JSONObject();
	  EsResource es=new EsResource();
	  es.setName("dafsdfasdfasdf");
	  es.setId(IDGeneratorUtil.getCode());
	  redisUtil.set("redis_test", "test redis data");
	  json.put("list", es);
	   return json;
   }
   
   @RequestMapping(value="gc/{count}",method=RequestMethod.GET)
   public @ResponseBody JSONObject gc(@PathVariable int count){
	  JSONObject json=new JSONObject();
	  for(int i=0;i<count;i++){
		  EsResource es=new EsResource();
		  es.setName("测试name:"+i);
		  System.out.println(JSONObject.toJSONString(es));
	  }
	  return json;
   }

   
   
   
   /**
    * 搜索
    * @return
    */
   @RequestMapping(value="search",method=RequestMethod.GET)
   public @ResponseBody JSONObject search(String keywords){
	   JSONObject json=new JSONObject();
	   Map<String,Object> param=new HashMap<>();
	   param.put("sort", "createDate");
	   param.put("keywords", keywords);
	   json.put("resData", this.esResourceService.searchResource(param));
	   return json;
   }
}
