/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsApiKey;
import org.phoenixbioinformatics.api.facade.ApiException;

import com.poesys.bs.delegate.DelegateException;


/**
 * The "real" implementation of the authentication API
 * @author Robert J. Muller
 */
public class AuthenticationFacade implements IAuthenticationFacade {
  /** the ApiKey data access delegate that encapsulates data access operations */
  private final IApiKeyDataAccessDelegate delegate;

  /** the business delegate to use to access data */
  // private ApiKeyDelegate delegate =
  // ApiDelegateFactory.getJndiApiKeyDelegate();

  /**
   * Create a ApiKeyFacade object. This sets a data access delegate that
   * encapsulates all data access operations, permitting fakes to do unit
   * testing.
   * 
   * @param delegate the data access delegate
   */
  public AuthenticationFacade(IApiKeyDataAccessDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<ApiKey> getApiKeys() throws ApiException {
    List<ApiKey> ApiKey;
    try {
      ApiKey = getDtoList(delegate.getAllObjects(10));
    } catch (DelegateException e) {
      throw new ApiException("Error getting ApiKey list by id", e);
    }
    return ApiKey;
  }

  @Override
  public List<ApiKey> getApiKeysById(BigInteger id)
      throws ApiException {
    List<ApiKey> ApiKey;
    try {
      ApiKey = getDtoList(delegate.getApiKeyById(id));
    } catch (DelegateException e) {
      throw new ApiException("Error getting ApiKey list by id", e);
    }
    return ApiKey;
  }

  @Override
  public void createApiKey(ApiKey ApiKey) throws ApiException {
    try {
      delegate.process(ApiKey);
    } catch (DelegateException e) {
      throw new ApiException("Error creating ApiKey", e);
    }
  }

  @Override
  public void updateApiKey(ApiKey ApiKey) throws ApiException {
    try {
      delegate.process(ApiKey);
    } catch (DelegateException e) {
      throw new ApiException("Error updating ApiKey", e);
    }
  }

  @Override
  public void deleteApiKey(ApiKey ApiKey) throws ApiException {
    try {
      delegate.process(ApiKey);
    } catch (DelegateException e) {
      throw new ApiException("Error deleting ApiKey", e);
    }
  }

  /**
   * Get a list of API DTOs by extracting data from a list of business objects.
   *
   * @param ApiKeys the list of business objects
   * @param list the list of DTOs; empty list if input list is null or empty
   */
  private List<ApiKey> getDtoList(List<BsApiKey> ApiKeys) {
    List<ApiKey> list = new ArrayList<ApiKey>(ApiKeys.size());
    if (list != null) {
      for (BsApiKey ApiKey : ApiKeys) {
        list.add(new ApiKey(ApiKey.getApiKey()));
      }
    }
    return list;
  }
}
