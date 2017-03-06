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
 * Masked Wallet Request Java object representation
 * https://devsite.googleplex.com/payment-express/api-reference#jwt-requests-and-responses
 * 
 * @author pying(peng ying)
 *
 */
public class MaskedWalletRequest implements JWTInterface {
  
  //Enum to define the type of data to request
  //At a higher level vs in selectors for easier comprehension
  public enum Select {
    PAY, SHIP, PAY_SHIP
  }
  
  private final String aud = "Google";
  private final String typ = "google/paymentexpress/v1/masked/request";
  
  private String iss;
  private String merchantSecret;
  private Long iat;
  private Long exp;
  
  private Selectors selectors;
  private String transaction_id;
  private String google_transaction_id;
  private String origin;
  
  /**
   * Constructor 
   * @param s Select Enum to define the type of information being requested
   */
  public MaskedWalletRequest(String iss, String merchantSecret, Select s){
    this.iss = iss;
    this.merchantSecret = merchantSecret;
    Selectors selectors = getSelectors(s);
    setSelectors(selectors);
  }

  /**
   * Constructor for a MaskedWalletRequest where a GoogleTransactionId exists
   * 
   * @param s Select Enum to define the type of information being requested
   * @param googleTransactionId Google provided id to tie purchases together
   */
  public MaskedWalletRequest(String iss, String merchantSecret, Select s, String googleTransactionId){
    this(iss, merchantSecret, s);
    setGoogle_transaction_id(googleTransactionId);
  }
  
  /**
   * Helper function to generate the Selectors for the MaskedWalletRequest
   * PAY for just payment Selectors
   * SHIP for just shipping Selectors
   * PAY_SHIP for both Selectors
   * 
   * @param s Enum value of selectors to create
   * @return Selectors for MaskedWalletRequest
   */
  private Selectors getSelectors(Select s) {
    Selector pay = null,ship = null;
    switch (s) {
      case PAY:
        pay = new Selector(Selector.SelectorType.PAYMENT_INSTRUMENT);
        break;
      case SHIP:
        ship =  new Selector(Selector.SelectorType.SHIPPING_ADDRESS);
        break;
      default:
        pay = new Selector(Selector.SelectorType.PAYMENT_INSTRUMENT);
        ship = new Selector(Selector.SelectorType.SHIPPING_ADDRESS);   
    }
    return new Selectors(pay, ship);
    
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
   * Getter for Selectors
   * 
   * @return Selectors for Masked Wallet Request
   */
  public Selectors getSelectors() {
    return selectors;
  }
  
  /**
   * Setter for Selectors
   * 
   * @param selectors Pay/Ship Selectors
   */
  public void setSelectors(Selectors selectors) {
    this.selectors = selectors;
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
   * @param origin url of the site
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
   * Converts Object to HashMap to add in JWT payload
   * 
   * @return HashMap of JWT content
   */
  public HashMap<String, JsonElement> getContent(){
    Gson gson = GsonHelper.getGson();
    JsonObject obj = gson.toJsonTree(getSelectors()).getAsJsonObject();
    
    HashMap<String, JsonElement> content = new HashMap<String,JsonElement>();
    content.put("origin", new JsonPrimitive(getOrigin()));
    if(getTransaction_id() != null)
      content.put("transaction_id", new JsonPrimitive(getTransaction_id()));
    content.put("selectors", obj);
    if(getGoogle_transaction_id() != null) {
      content.put("google_transaction_id", new JsonPrimitive(getGoogle_transaction_id()));
    }
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
   * Getter for Google Transaction Id
   * 
   * @return Google Transaction Id
   */
  public String getGoogle_transaction_id() {
    return google_transaction_id;
  }

  /**
   * Setter for Google Transaction Id
   * @param google_transaction_id Google Transaction id
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
