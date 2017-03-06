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
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.paymentexpress.jwt.util.GsonHelper;

/**
 * Full Wallet Request Java object representation
 * https://devsite.googleplex.com/payment-express/api-reference#jwt-requests-and-responses
 * 
 * @author pying(peng ying)
 *
 */
public class FullWalletRequest implements JWTInterface {
  
  private final String aud = "Google";
  private final String typ = "google/paymentexpress/v1/full/request";
  
  private String iss;
  private String merchantSecret;
  
  private Long iat;
  private Long exp;
  
  private String transaction_id;
  private String transaction_url;
  private String google_transaction_id;
  private String origin;
  
  private Cart cart;
  
  /**
   * Constructor that takes Merchant Id and Secret as parameters
   * 
   * @param iss Merchant Id to be used as issuer
   * @param merchantSecret Merchant Secret
   */
  public FullWalletRequest(String iss, String merchantSecret){
    this.iss = iss;
    this.merchantSecret = merchantSecret;
  }
  
  /**
   * Constructor that takes Merchant Id, Secret, items sold and Google Transaction Id
   * @param iss Merchant Id to be used as issuer
   * @param merchantSecret Merchant Secret
   * @param cart cart of items purchased
   * @param google_id Google Transaction Id
   */
  public FullWalletRequest(String iss, String merchantSecret, Cart cart, String google_id){
    this(iss, merchantSecret);
    this.cart = cart;
    this.google_transaction_id = google_id;
  }
  
  /**
   * Getter for Transaction Id
   * 
   * @return transaction id
   */
  public String getTransaction_id() {
    return transaction_id;
  }

  /**
   * Setter for Transaction Id
   * 
   * @param transaction_id Transaction Id
   */
  public void setTransaction_id(String transaction_id) {
    this.transaction_id = transaction_id;
  }

  /**
   * Getter for expiration time
   * 
   * @return expiration time
   */
  public Long getExp() {
    return exp;
  }

  /**
   * Setter for expiration time
   * 
   * @param exp time for expiration of purchase
   */
  public void setExp(Long exp) {
    this.exp = exp;
  }

  /**
   * Getter for issued at time
   * 
   * @return issued at time
   */
  public Long getIat() {
    return iat;
  }

  /**
   * Setter for issued at time
   * 
   * @param iat issued at time
   */
  public void setIat(Long iat) {
    this.iat = iat;
  }

  /**
   * Getter for transaction URL which will be used to define a URL for customers
   * to review receipts
   * 
   * @return transaction URL
   */
  public String getTransaction_url() {
    return transaction_url;
  }

  /**
   * Setter for transaction URL which will be used to define a URL for customers
   * to review receipts
   * 
   * @param transaction_url transaction URL
   */
  public void setTransaction_url(String transaction_url) {
    this.transaction_url = transaction_url;
  }

  /**
   * Getter for issuer
   * 
   * @return issuer
   */
  public String getIssuer() {
    return iss;
  }
  
  /**
   * Getter for origin
   * 
   * @return origin url
   */
  public String getOrigin() {
    return origin;
  }

  /**
   * Setter for origin
   * 
   * @param origin origin url
   */
  public void setOrigin(String origin) {
    this.origin = origin;
  }
  
  /**
   * Getter for audience
   * 
   * @return JWT audience
   */
  public String getAudience() {
    return aud;
  }

  /**
   * Getter for type
   * 
   * @return JWT type
   */
  public String getType() {
    return typ;
  }

  /**
   * Getter for Cart
   * 
   * @return cart of items
   */
  public Cart getCart(){
    return cart;
  }
  
  /**
   * Setter for Cart
   * 
   * @param cart Cart of items
   */
  public void setCart(Cart cart){
    this.cart = cart;
  }
  
  /**
   * Converts Object to HashMap to add in JWT payload
   * 
   * @return HashMap of JWT content
   */
  public HashMap<String, JsonElement> getContent() {
    Gson gson = GsonHelper.getGson();
    JsonObject obj = gson.toJsonTree(getCart()).getAsJsonObject();
    
    HashMap<String, JsonElement> content = new HashMap<String, JsonElement>();
    content.put("origin", new JsonPrimitive(getOrigin()));
    content.put("cart", obj);
    if(getTransaction_id() != null)
      content.put("transaction_id", new JsonPrimitive(getTransaction_id()));
    if(getTransaction_url() != null)
      content.put("transaction_url", new JsonPrimitive(getTransaction_url()));
    content.put("google_transaction_id", new JsonPrimitive(getGoogle_transaction_id()));
    return content;
  }
  
  /**
   * Generate the JSON web token String from the defined values.
   * 
   * @return JWT String
   */
  public String generateJWT(){
    String jwt = null;
    try {
      jwt = JWTGenerator.generate(this);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (SignatureException e) {
      e.printStackTrace();
    }
    return jwt;
  }

  /**
   * Getter for Transaction Id
   * 
   * @return transaction id
   */
  public String getGoogle_transaction_id() {
    return google_transaction_id;
  }
  
  /**
   * Setter for Transaction Id
   * 
   * @param transaction_id Transaction Id
   */
  public void setGoogle_transaction_id(String google_transaction_id) {
    this.google_transaction_id = google_transaction_id;
  }
  
  /**
   * Getter for Merchant Secret
   * 
   * @return Merchant Secret
   */
  public String getMerchantSecret() {
    return merchantSecret;
  }
  
  /**
   * Setter for Merchant Secret
   * 
   * @param merchantSecret Merchant Secret
   */
  public void setMerchantSecret(String merchantSecret) {
    this.merchantSecret = merchantSecret;
  }
}
