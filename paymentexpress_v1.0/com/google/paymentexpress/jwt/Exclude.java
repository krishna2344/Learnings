package com.google.paymentexpress.jwt;

public class Exclude {
  private String object_id;
  
  public Exclude(String id){
    this.setObject_id(id);
  }

  public String getObject_id() {
    return object_id;
  }

  public void setObject_id(String object_id) {
    this.object_id = object_id;
  }
}
