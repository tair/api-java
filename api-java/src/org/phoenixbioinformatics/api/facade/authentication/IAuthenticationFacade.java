/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.List;

import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * The facade-pattern interface for the authentication API
 * 
 * @author Robert J. Muller
 */
public interface IAuthenticationFacade {
  /**
   * Get all the API keys.
   *
   * @return a ApiKey
   */
  List<ApiKey> getApiKeys() throws ApiException;

  /**
   * Get a key specified by its id.
   *
   * @param id the id
   * @return a list of keys with one key
   * @throws ApiException when there is a problem with the API call
   */
  List<ApiKey> getApiKeysById(BigInteger id) throws ApiException;

  /**
   * Create an ApiKey from input data.
   *
   * @param ApiKey the DTO
   * @throws ApiException when there is a problem with the API call
   */
  void createApiKey(ApiKey ApiKey) throws ApiException;

  /**
   * Update an ApiKey from input data, which should contain the id for the
   * ApiKey and all fields set with desired values.
   *
   * @param ApiKey the DTO representing the updated object data
   * @throws ApiException when there is a problem with the API call
   */
  void updateApiKey(ApiKey ApiKey) throws ApiException;

  /**
   * Delete an ApiKey specified by an ApiKey DTO containing the id that
   * identifies the ApiKey.
   *
   * @param ApiKey the ApiKey DTO identifying the ApiKey to delete
   * @throws ApiException when there is a problem with the API call
   */
  void deleteApiKey(ApiKey ApiKey) throws ApiException;
}
