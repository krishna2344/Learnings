package com.google.paymentexpress.jwt;

import java.util.ArrayList;

public class Cart {

  private String total_price;
  private String currency;
  private ArrayList<LineItem> line_items;
  
  public Cart(){
    
  }
  
  public Cart(String currency){
    this.currency = currency;
  }
  
  public Cart(ArrayList<LineItem> items, String currency){
    this.line_items = items;
    this.currency = currency;
    updateTotal();
  }
  
  public String getTotal_price() {
    return total_price;
  }
  
  public void setTotal_price(String total_price) {
    this.total_price = total_price;
  }
  
  public String getCurrency() {
    return currency;
  }
  
  public void setCurrency(String currency) {
    this.currency = currency;
  }
  
  public ArrayList<LineItem> getLine_items() {
    return line_items;
  }
  
  public void setLine_items(ArrayList<LineItem> line_items) {
    this.line_items = line_items;
  }
  
  public void addItem(LineItem item){
    if(line_items == null)
      line_items = new ArrayList<LineItem>();
    line_items.add(item);
    updateTotal();
  }
  
  private void updateTotal(){
    Double total = 0.00;
    for(LineItem item: line_items){
      total += item.getTotal_price();
    }
    setTotal_price(total.toString());
  }
  
}
