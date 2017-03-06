package com.google.paymentexpress.jwt;

public class LineItem {
  private String description;
  private Integer quantity;
  private Double unit_price;
  private Double total_price;
  private String currency;
  private String role;
  
  public LineItem(){
    
  }
  
  public LineItem(String desc, Integer quantity, Double price, String currency){
    this.description = desc;
    this.quantity = quantity;
    this.unit_price = price;
    this.total_price = quantity * price;
    this.currency = currency;
  }
  
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Integer getQuantity() {
    return quantity;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
  public Double getUnit_price() {
    return unit_price;
  }
  public void setUnit_price(Double unit_price) {
    this.unit_price = unit_price;
  }
  public Double getTotal_price() {
    return total_price;
  }
  public void setTotal_price(Double total_price) {
    this.total_price = total_price;
  }
  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
}
