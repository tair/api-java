/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.List;

import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * The facade-pattern interface for the partner API
 * 
 * @author Robert J. Muller
 */
public interface IPartnerFacade {
  /**
   * Get all the partners.
   *
   * @return a partner
   */
  List<Partner> getPartners() throws ApiException;

  /**
   * Get a partner specified by a partner id.
   *
   * @param partnerId the id string
   * @return a list of partners with one partner
   * @throws ApiException when there is a problem with the API call
   */
  List<Partner> getPartnersById(String partnerId) throws ApiException;

  /**
   * Get a partner specified by a name.
   *
   * @param name the name
   * @return a list of partners with one partner
   * @throws ApiException when there is a problem with the API call
   */
  List<Partner> getPartnersByName(String name) throws ApiException;

  /**
   * Get a partner specified by a URI; the URI matches a regular expression
   * pattern that represents a class of URIs used only by the partner.
   *
   * @param uri the URI
   * @return a list of partners with one partner
   * @throws ApiException when there is a problem with the API call
   */
  List<Partner> getPartnersByUri(String uri) throws ApiException;

  /**
   * Create a partner from input data.
   *
   * @param partner the DTO
   * @throws ApiException when there is a problem with the API call
   */
  void createPartner(Partner partner) throws ApiException;

  /**
   * Update a partner from input data, which should contain the key for the
   * partner and all fields set with desired values.
   *
   * @param partner the DTO representing the updated object data
   * @throws ApiException when there is a problem with the API call
   */
  void updatePartner(Partner partner) throws ApiException;

  /**
   * Delete a partner specified by a partner DTO containing the key that
   * identifies the partner.
   *
   * @param partner the partner DTO identifying the partner to delete
   * @throws ApiException when there is a problem with the API call
   */
  void deletePartner(Partner partner) throws ApiException;
}
