/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.List;

import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * Implements the ApiKey Facade API with fake operations to permit unit testing
 * of clients, with all methods returning exceptions.
 * 
 * @author Robert J. Muller
 */
public class AuthenticationFacadeFailureFake implements IAuthenticationFacade {
  /** the ApiKey data access delegate that encapsulates data access operations */
  @SuppressWarnings("unused")
  private final IApiKeyDataAccessDelegate delegate;

  /**
   * Create a AuthenticationFacade object. This sets a data access delegate that
   * encapsulates all data access operations, permitting fakes to do unit
   * testing. This implementation should get a fake delegate, but basically this
   * implementation just returns DTOs and exceptions as required.
   * 
   * @param delegate the data access delegate
   */
  public AuthenticationFacadeFailureFake(IApiKeyDataAccessDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<ApiKey> getApiKeys() throws ApiException {
    throw new ApiException("getApiKeys() failed");
  }

  @Override
  public List<ApiKey> getApiKeysById(BigInteger id) throws ApiException {
    throw new ApiException("getApiKeysById() failed");
  }

  @Override
  public void createApiKey(ApiKey ApiKey) throws ApiException {
    throw new ApiException("createApiKey() failed");
  }

  @Override
  public void updateApiKey(ApiKey ApiKey) throws ApiException {
    throw new ApiException("updateApiKey() failed");
  }

  @Override
  public void deleteApiKey(ApiKey ApiKey) throws ApiException {
    throw new ApiException("deleteApiKey() failed");
  }
}
