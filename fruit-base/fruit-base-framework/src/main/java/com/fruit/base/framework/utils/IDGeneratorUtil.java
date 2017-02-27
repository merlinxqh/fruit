package com.fruit.base.framework.utils;

import java.util.UUID;

/**
 * 返回UUID
 * @author leo
 *
 */
public class IDGeneratorUtil {
	
  public static String getCode(){
	  return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
