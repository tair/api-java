/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * Implements the ApiKey Facade API with fake operations to permit unit testing
 * of clients, with all methods returning successfully.
 * 
 * @author Robert J. Muller
 */
public class AuthenticationFacadeSuccessFake implements IAuthenticationFacade {
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
  public AuthenticationFacadeSuccessFake(IApiKeyDataAccessDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<ApiKey> getApiKeys() throws ApiException {
    return getApiKeyList();
  }

  /**
   * Get a randomly-generated API key list containing two keys.
   *
   * @return
   */
  private List<ApiKey> getApiKeyList() {
    Random r = new Random();
    List<ApiKey> apiKeys = new ArrayList<ApiKey>();
    apiKeys.add(new ApiKey(new UUID(r.nextLong(), r.nextLong()).toString()));
    apiKeys.add(new ApiKey(new UUID(r.nextLong(), r.nextLong()).toString()));
    return apiKeys;
  }

  @Override
  public List<ApiKey> getApiKeysById(BigInteger id) throws ApiException {
    Random r = new Random();
    List<ApiKey> apiKeys = new ArrayList<ApiKey>();
    ApiKey key = new ApiKey(new UUID(r.nextLong(), r.nextLong()).toString());
    apiKeys.add(key);
    return apiKeys;
  }

  @Override
  public void createApiKey(ApiKey ApiKey) throws ApiException {
  }

  @Override
  public void updateApiKey(ApiKey ApiKey) throws ApiException {
  }

  @Override
  public void deleteApiKey(ApiKey ApiKey) throws ApiException {
  }
}
