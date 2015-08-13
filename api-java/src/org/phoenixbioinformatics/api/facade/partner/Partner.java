/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsPartner;
import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * An API DTO for the Partner object
 * 
 * @author Robert J. Muller
 */
public class Partner {
  /** unique string identifying partner */
  private String partnerId;
  /** unique display name for partner */
  private String name;
  /** URI for the resource to use as the partner logo */
  private String logoUri;
  /** URI for the resource to use as the partner terms of service (license) */
  private String termOfServiceUri;

  /**
   * Create a Partner object.
   * 
   * @param partnerId the unique identifier for a partner
   * @param name the display name for a partner
   * @param logoUri the URI for the resource used as a partner logo
   * @param termOfServiceUri the URI for the resource giving the terms of
   *          service (license) for the partner
   * @throws ApiException when a required data element is null
   */
  public Partner(String partnerId,
                 String name,
                 String logoUri,
                 String termOfServiceUri) throws ApiException {
    if (partnerId == null) {
      throw new ApiException("Partner id required");
    }
    this.partnerId = partnerId;
    this.name = name;
    this.logoUri = logoUri;
    this.termOfServiceUri = termOfServiceUri;
  }

  /**
   * Get the partnerId.
   * 
   * @return a partnerId
   */
  public String getPartnerId() {
    return partnerId;
  }

  /**
   * Set the partnerId.
   * 
   * @param partnerId a partnerId
   */
  public void setPartnerId(String partnerId) {
    this.partnerId = partnerId;
  }

  /**
   * Get the name.
   * 
   * @return a name
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name.
   * 
   * @param name a name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the logoUri.
   * 
   * @return a logoUri
   */
  public String getLogoUri() {
    return logoUri;
  }

  /**
   * Set the logoUri.
   * 
   * @param logoUri a logoUri
   */
  public void setLogoUri(String logoUri) {
    this.logoUri = logoUri;
  }

  /**
   * Get the termOfServiceUri.
   * 
   * @return a termOfServiceUri
   */
  public String getTermOfServiceUri() {
    return termOfServiceUri;
  }

  /**
   * Set the termOfServiceUri.
   * 
   * @param termOfServiceUri a termOfServiceUri
   */
  public void setTermOfServiceUri(String termOfServiceUri) {
    this.termOfServiceUri = termOfServiceUri;
  }

  /**
   * Get a list of Partner DTO objects based on a list of business objects.
   *
   * @param list the list of business objects
   * @return the list of DTO objects
   * @throws ApiException when there is a missing required data element
   */
  static public List<Partner> getDtoList(List<BsPartner> list)
      throws ApiException {
    List<Partner> newList = new ArrayList<Partner>();
    for (BsPartner bs : list) {
      Partner dto =
        new Partner(bs.getPartnerId(),
                    bs.getName(),
                    bs.getLogoUri(),
                    bs.getTermOfServiceUri());
      newList.add(dto);
    }
    return newList;
  }
}
