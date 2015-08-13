/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsApiKey;


/**
 * An API DTO for the API Key object
 * 
 * @author Robert J. Muller
 */
public class ApiKey {
  /** unique string identifying partner */
  private String apiKey;

  /**
   * Create an API Key object.
   * 
   * @param partnerId the unique identifier for a partner
   * @param name the display name for a partner
   * @param logoUri the URI for the resource used as a partner logo
   * @param termOfServiceUri the URI for the resource giving the terms of
   *          service (license) for the partner
   */
  public ApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * Get the API key.
   * 
   * @return a key
   */
  public String getApiKey() {
    return apiKey;
  }

  /**
   * Set the API key.
   * 
   * @param apiKey a key
   */
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  static public List<ApiKey> getDtoList(List<BsApiKey> list) {
    List<ApiKey> newList = new ArrayList<ApiKey>();
    for (BsApiKey bs : list) {
      ApiKey dto = new ApiKey(bs.getApiKey());
      newList.add(dto);
    }
    return newList;
  }
}
