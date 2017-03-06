package com.google.paymentexpress.jwt;

import java.util.ArrayList;

/**
 * Payment Instrument Bean
 * 
 * @author pying@google.com(peng ying)
 *
 */
public class PaymentInstrument {

  private String descriptive_name;
  private String type;
  private ArrayList<String> supported_currency;
  private String last_four_digits;
  private Integer expiration_month;
  private Integer expiration_year;
  private Address billing_address;
  private EncryptedData full_card_number;
  private EncryptedData cvc;
  
  public PaymentInstrument(){
  }

  public String getDescriptive_name() {
    return descriptive_name;
  }

  public void setDescriptive_name(String descriptive_name) {
    this.descriptive_name = descriptive_name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ArrayList<String> getSupported_currency() {
    return supported_currency;
  }

  public void setSupported_currency(ArrayList<String> supported_currency) {
    this.supported_currency = supported_currency;
  }

  public String getLast_four_digits() {
    return last_four_digits;
  }

  public void setLast_four_digits(String last_four_digits) {
    this.last_four_digits = last_four_digits;
  }

  public Integer getExpiration_month() {
    return expiration_month;
  }

  public void setExpiration_month(Integer expiration_month) {
    this.expiration_month = expiration_month;
  }

  public Integer getExpiration_year() {
    return expiration_year;
  }

  public void setExpiration_year(Integer expiration_year) {
    this.expiration_year = expiration_year;
  }

  public Address getBilling_address() {
    return billing_address;
  }

  public void setBilling_address(Address billing_address) {
    this.billing_address = billing_address;
  }

  public EncryptedData getFull_cart_number() {
    return full_card_number;
  }

  public void setFull_cart_number(EncryptedData full_cart_number) {
    this.full_card_number = full_cart_number;
  }

  public EncryptedData getCvc() {
    return cvc;
  }

  public void setCvc(EncryptedData cvc) {
    this.cvc = cvc;
  }
}
