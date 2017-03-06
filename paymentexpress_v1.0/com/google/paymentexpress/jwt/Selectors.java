package com.google.paymentexpress.jwt;

public class Selectors {
  
  private Selector pay;
  private Selector ship;
  
  public Selectors(){}

  public Selectors(Selector pay, Selector ship){
    this.pay = pay;
    this.ship = ship;
  }
  
  public Selector getPay() {
    return pay;
  }

  public void setPay(Selector pay) {
    this.pay = pay;
  }

  public Selector getShip() {
    return ship;
  }

  public void setShip(Selector ship) {
    this.ship = ship;
  }
  
  
}
