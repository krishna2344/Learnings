package com.google.paymentexpress.jwt.util;

import com.google.gson.Gson;

/**
 * Gson factory that points to a static Gson instance
 * 
 * @author pying@google.com(peng ying)
 *
 */
public class GsonHelper {
  private static final Gson gson = new Gson();
  
  /**
   * Can't Initialize this class
   */
  private GsonHelper(){}
  
  /**
   * get the static Gson instance
   * @return Gson gson instance
   */
  public static Gson getGson(){
    return gson;
  }
}
