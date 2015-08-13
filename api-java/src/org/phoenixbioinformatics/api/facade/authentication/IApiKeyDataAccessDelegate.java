/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsApiKey;
import org.phoenixbioinformatics.api.bs.api.ApiKeyDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * An interface that abstracts the data access operations for the ApiKey API.
 * This allows for "fake" implementations for unit testing and real ones for
 * production.
 * 
 * @author Robert J. Muller
 */
public interface IApiKeyDataAccessDelegate {

  /**
   * Get the API/DB ApiKey delegate; used for integration tests and production
   *
   * @return a ApiKey business delegate
   * @throws DelegateException when there is a data access problem
   */
  ApiKeyDelegate getDelegate() throws DelegateException;

  /**
   * Get all the ApiKey objects.
   *
   * @param i the fetch count optimizer
   * @return a list of ApiKey business objects
   * @throws DelegateException when there is a data access problem
   */
  List<BsApiKey> getAllObjects(int i) throws DelegateException;

  /**
   * Get an ApiKey by id, returning it in a list.
   *
   * @param id the id
   * @return a list of ApiKeys containing a single ApiKey
   * @throws DelegateException when there is a data access problem
   */
  List<BsApiKey> getApiKeyById(BigInteger id) throws DelegateException;

  /**
   * Process an ApiKey, taking actions appropriate to its status.
   *
   * @param ApiKey the ApiKey to process
   * @throws DelegateException when there is a data access problem
   */
  void process(ApiKey ApiKey) throws DelegateException;

  /**
   * Process a list of ApiKeys, taking actions appropriate to the status of
   * each.
   *
   * @param list the list of ApiKeys
   * @throws DelegateException when there is a data access problem
   */
  void process(List<ApiKey> list) throws DelegateException;
}
