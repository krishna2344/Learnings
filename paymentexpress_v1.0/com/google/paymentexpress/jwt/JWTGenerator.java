/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.paymentexpress.jwt;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.HashMap;

import org.joda.time.Instant;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;

/**
 * Helper class to convert Java Objects to JSON Web Tokens
 * 
 * @author pying@google.com (Peng Ying)
 *
 */
public class JWTGenerator {
  
  /**
   * Default Constructor
   */
  private JWTGenerator(){
  }
  
  /**
   * 
   * @param target
   * @return 
   * @throws InvalidKeyException
   * @throws SignatureException
   */
  public static String generate(JWTInterface target) throws InvalidKeyException, SignatureException{
    Calendar cal = Calendar.getInstance();
    HmacSHA256Signer signer = new HmacSHA256Signer(target.getIssuer(), null, target.getMerchantSecret().getBytes());
    
    JsonToken token = new JsonToken(signer);
    token.setAudience(target.getAudience());
    token.setParam("typ", target.getType());
    token.setIssuedAt(target.getIat() != null ? new Instant(target.getIat()) : new Instant(cal.getTimeInMillis()));
    //token.setExpiration(target.getExp() != null? new Instant(target.getExp()) : new Instant(cal.getTimeInMillis() + 6000000L));
    
    JsonObject payload = token.getPayloadAsJsonObject();
    
    HashMap<String, JsonElement> params = target.getContent();
    
    for(String key:params.keySet()){
      payload.add(key, params.get(key));
    }
    
    return token.serializeAndSign();
  }
}
