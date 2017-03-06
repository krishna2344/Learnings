package com.google.paymentexpress.jwt;

/**
 * Address Bean
 * 
 * @author pying@google.com
 *
 */

public class Address {

  private String name;
  private String address1;
  private String address2;
  private String country_code;
  private String city;
  private String state;
  private String postal_code;
  private String phone_number;
  private Boolean post_box;
  private String company_name;
  
  public Address() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCountry_code() {
    return country_code;
  }

  public void setCountry_code(String country_code) {
    this.country_code = country_code;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostal_code() {
    return postal_code;
  }

  public void setPostal_code(String postal_code) {
    this.postal_code = postal_code;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public Boolean getPost_box() {
    return post_box;
  }

  public void setPost_box(Boolean post_box) {
    this.post_box = post_box;
  }

  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(String company_name) {
    this.company_name = company_name;
  }
}
