package com.google.paymentexpress.jwt;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.paymentexpress.jwt.util.GsonHelper;

import net.oauth.jsontoken.JsonToken;


public class FullWalletResponse {
  private String iss;
  private String aud;
  private Long iat;
  private String typ;
  private String google_transaction_id;
  private String transaction_id;
  private Selectors selection;
  
  private final String SELECTION = "selection";
  
  public FullWalletResponse(){
    
  }
  
  public FullWalletResponse(JsonToken jwt){
    this.setIat(jwt.getIssuedAt().getMillis());
    this.setIss(jwt.getIssuer());
    this.setAud(jwt.getAudience());
    this.setTyp(jwt.getParamAsPrimitive("typ").getAsString());
    this.setGoogle_transaction_id(jwt.getParamAsPrimitive("google_transaction_id").getAsString());
    if(jwt.getParamAsPrimitive("transaction_id") != null)
      this.setTransaction_id(jwt.getParamAsPrimitive("transaction_id").getAsString());
    JsonObject payload = jwt.getPayloadAsJsonObject();
    JsonObject selection = payload.getAsJsonObject(SELECTION);
    Gson gson = GsonHelper.getGson();
    this.setSelection(gson.fromJson(selection, Selectors.class));
  }

  public String getIss() {
    return iss;
  }

  public void setIss(String iss) {
    this.iss = iss;
  }

  public String getAud() {
    return aud;
  }

  public void setAud(String aud) {
    this.aud = aud;
  }

  public Long getIat() {
    return iat;
  }

  public void setIat(Long iat) {
    this.iat = iat;
  }

  public String getTyp() {
    return typ;
  }

  public void setTyp(String typ) {
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

  public Selectors getSelection() {
    return selection;
  }

  public void setSelection(Selectors selection) {
    this.selection = selection;
  }
  
}
