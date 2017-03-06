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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.paymentexpress.jwt.util.GsonHelper;

import net.oauth.jsontoken.JsonToken;

/**
 * Masked Wallet Response Bean Representation
 * https://devsite.googleplex.com/payment-express/api-reference
 * 
 * @author pying(peng ying)
 *
 */
public class MaskedWalletResponse {
  private String iss;
  private String aud;
  private Long iat;
  private String typ;
  private String google_transaction_id;
  private String transaction_id;
  private String email;
  private Selectors selection;
  
  private final String SELECTION = "selection";
  
  /**
   * Constructor that takes a the decoded JWT body to convert
   * 
   * @param jwt JSON Web Token body 
   */
  public MaskedWalletResponse(JsonToken jwt){
    this.iat = jwt.getIssuedAt().getMillis();
    this.iss = jwt.getIssuer();
    this.aud = jwt.getAudience();
    this.typ = jwt.getParamAsPrimitive("typ").getAsString();
    this.google_transaction_id = jwt.getParamAsPrimitive("google_transaction_id").getAsString();
    if(jwt.getParamAsPrimitive("transaction_id") != null)
      this.transaction_id = jwt.getParamAsPrimitive("transaction_id").getAsString();
    this.email = jwt.getParamAsPrimitive("email").getAsString();
    JsonObject payload = jwt.getPayloadAsJsonObject();
    JsonObject selection = payload.getAsJsonObject(SELECTION);
    Gson gson = GsonHelper.getGson();
    this.selection = gson.fromJson(selection, Selectors.class);
  }
  
  /**
   * Getter for Issuer
   * 
   * @return Issuer in this case Google
   */
  public String getIss() {
    return iss;
  }

  /**
   * Setter for Issuer used by Gson to set issuer
   * 
   * @param iss sets the issuer 
   */
  public void setIss(String iss){
    this.iss = iss;
  }
  
  /**
   * Getter for Audience
   * 
   * @return Audience in this case your Merchant Id
   */
  public String getAud() {
    return aud;
  }

  public void setAud(String aud){
    this.aud = aud;
  }
  
  /**
   * Getter for Issued At Time
   * 
   * @return when then JWT was created
   */
  public Long getIat() {
    return iat;
  }

  public void setIat(Long iat){
    this.iat = iat;
  }
  /**
   * Getter for type of message
   * 
   * @return Type of 
   */
  public String getTyp() {
    return typ;
  }

  /**
   * 
   * @param typ
   */
  public void setTyp(String typ){
    this.typ = typ;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Selectors getSelection() {
    return selection;
  }

  public void setSelection(Selectors selection) {
    this.selection = selection;
  }
  
  
}
