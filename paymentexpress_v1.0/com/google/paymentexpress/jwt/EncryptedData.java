package com.google.paymentexpress.jwt;

/**
 * Bean to represent the Credit Card and CVV encrypted fields.
 * This class does not need to be generated manually use GSON
 * to parse from JSON to Java objects.
 * 
 * The encrypted session_data and field_data are part of Keyczar's
 * encryption protocol.
 * 
 * @author pying@google.com(peng ying)
 *
 */
public class EncryptedData {

  private String session_data;
  private String field_data;
  
  /**
   * Blank Constructor
   */
  public EncryptedData(){
  }

  /**
   * Get the encrypted session  data
   * @return string encrypted session data 
   */
  public String getSession_data() {
    return session_data;
  }

  /**
   * Set the encrypted session data
   * @param session_data String encrypted session data
   */
  public void setSession_data(String session_data) {
    this.session_data = session_data;
  }

  /**
   * Get the encrypted field data
   * @return string encrypted field data
   */
  public String getField_data() {
    return field_data;
  }

  /**
   * Set the encrypted field data
   * @param field_data String encrypted field data
   */
  public void setField_data(String field_data) {
    this.field_data = field_data;
  }
}
