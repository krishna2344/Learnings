package com.google.paymentexpress.jwt;

import java.util.ArrayList;

public class Selector {
  public static enum SelectorType {
    PAYMENT_INSTRUMENT, SHIPPING_ADDRESS
  }
  
  private String type;
  private String object_id;
  private Address shipping_address;
  private PaymentInstrument payment_instrument;
  private ArrayList<Exclude> exclude;
  
  public Selector(){
    
  }
  
  public Selector(SelectorType st){
    switch (st) {
      case PAYMENT_INSTRUMENT: type="PAYMENT_INSTRUMENT";
        break;
      case SHIPPING_ADDRESS: type="SHIPPING_ADDRESS";
        break;
    }
  }

  public ArrayList<Exclude> getExclude() {
    return exclude;
  }

  public void setExclude(ArrayList<Exclude> exclude) {
    this.exclude = exclude;
  }
  
  public void setType(String type){
    this.type = type;
  }
  
  public String getType(){
    return type;
  }

  public String getObject_id() {
    return object_id;
  }

  public void setObject_id(String object_id) {
    this.object_id = object_id;
  }

  public Address getShipping_address() {
    return shipping_address;
  }

  public void setShipping_address(Address shipping_address) {
    this.shipping_address = shipping_address;
  }

  public PaymentInstrument getPayment_instrument() {
    return payment_instrument;
  }

  public void setPayment_instrument(PaymentInstrument payment_instrument) {
    this.payment_instrument = payment_instrument;
  }
  
  
  
}
