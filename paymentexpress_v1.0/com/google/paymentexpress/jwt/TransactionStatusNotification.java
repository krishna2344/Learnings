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

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * 
 * 
 * @author pying(peng ying)
 *
 */
public class TransactionStatusNotification implements JWTInterface {
  
  /**
   * 
   *
   */
  public enum stat {
    SUCCESS,
    FAILURE
  }
  
  public enum reas {
    BAD_CVC,
    BAD_CARD,
    DECLINED,
    OTHER
  }
  
  private final String aud = "Google";
  private final String typ = "google/paymentexpress/v1/transactionstatus";
 
  private String iss;
  private String merchantSecret;
  private Long iat;
  private Long exp;
  
  private String google_transaction_id;
  private String transaction_id;
  private String transaction_url;
  
  private String status;
  private String reason;
  private String detailed_reason;
  private String origin;
  
  /**
   * 
   * @param transactionId
   * @param status
   */
  public TransactionStatusNotification(String iss, String merchantSecret, String transactionId, stat status){
    this.iss = iss;
    this.merchantSecret = merchantSecret;
    setGoogle_transaction_id(transactionId);
    setStatus(status.toString());
  }
  
  public TransactionStatusNotification(String iss, String merchantSecret, String transactionId, stat status, reas reason, String details){
    this(iss, merchantSecret, transactionId, status);
    setGoogle_transaction_id(transactionId);
    setStatus(status.toString());  
    setReason(reason.toString());
    setDetailed_reason(details);
  }
  
  @Override
  public Long getIat() {
    return iat;
  }

  @Override
  public Long getExp() {
    return exp;
  }

  @Override
  public String getIssuer() {
    return iss;
  }

  @Override
  public String getAudience() {
    return aud;
  }

  @Override
  public String getType() {
    return typ;
  }

  @Override
  public HashMap<String, JsonElement> getContent() {
    HashMap<String, JsonElement> content = new HashMap<String, JsonElement>();
    content.put("google_transaction_id", new JsonPrimitive(getGoogle_transaction_id()));
    content.put("status", new JsonPrimitive(getStatus()));
    if (getReason() != null)
      content.put("reason", new JsonPrimitive(getReason()));
    if (getDetailed_reason() != null)
      content.put("detailed_reason", new JsonPrimitive(getDetailed_reason()));
    return content;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getDetailed_reason() {
    return detailed_reason;
  }

  public void setDetailed_reason(String detailed_reason) {
    this.detailed_reason = detailed_reason;
  }
  
  public String generateJWT(){
    String jwt = null;
    try {
      jwt = JWTGenerator.generate(this);
    } catch (InvalidKeyException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SignatureException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return jwt;
  }

  public String getGoogle_transaction_id() {
    return google_transaction_id;
  }

  public void setGoogle_transaction_id(String google_transaction_id) {
    this.google_transaction_id = google_transaction_id;
  }

  public String getTransaction_id() {
    return transaction_id;
  }

  public void setTransaction_id(String transaction_id) {
    this.transaction_id = transaction_id;
  }

  public String getTransaction_url() {
    return transaction_url;
  }

  public void setTransaction_url(String transaction_url) {
    this.transaction_url = transaction_url;
  }

  public String getMerchantSecret() {
    return this.merchantSecret;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

}
