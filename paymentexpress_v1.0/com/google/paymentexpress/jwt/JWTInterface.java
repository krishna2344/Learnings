package com.google.paymentexpress.jwt;

import java.util.HashMap;

import com.google.gson.JsonElement;

public interface JWTInterface {

  Long getIat();
  Long getExp();
  String getIssuer();
  String getAudience();
  String getType();
  String getMerchantSecret();
  HashMap<String, JsonElement> getContent();
}
