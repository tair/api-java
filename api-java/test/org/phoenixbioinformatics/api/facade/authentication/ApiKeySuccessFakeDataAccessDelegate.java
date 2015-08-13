/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.BsApiKey;
import org.phoenixbioinformatics.api.bs.api.ApiKeyDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * Implements the ApiKey data access delegate with fake operations to permit
 * unit testing of the ApiKey facade, with all methods returning data or
 * succeeding without error.
 * 
 * @author Robert J. Muller
 */
public class ApiKeySuccessFakeDataAccessDelegate implements
    IApiKeyDataAccessDelegate {
  ApiKeyDelegate delegate = ApiDelegateFactory.getApiKeyDelegate();

  @Override
  public ApiKeyDelegate getDelegate() throws DelegateException {
    return delegate;
  }

  @Override
  public List<BsApiKey> getAllObjects(int i) throws DelegateException {
    List<BsApiKey> ApiKeys = new ArrayList<BsApiKey>();
    Random r = new Random();
    ApiKeys.add(delegate.createApiKey(BigInteger.ZERO,
                                      new UUID(r.nextLong(), r.nextLong()).toString()));
    ApiKeys.add(delegate.createApiKey(BigInteger.ZERO,
                                      new UUID(r.nextLong(), r.nextLong()).toString()));
    return ApiKeys;
  }

  @Override
  public List<BsApiKey> getApiKeyById(BigInteger id) throws DelegateException {
    List<BsApiKey> apiKeys = new ArrayList<BsApiKey>();
    Random r = new Random();
    apiKeys.add(delegate.createApiKey(BigInteger.ZERO,
                                      new UUID(r.nextLong(), r.nextLong()).toString()));

    return apiKeys;
  }

  @Override
  public void process(ApiKey ApiKey) throws DelegateException {
  }

  @Override
  public void process(List<ApiKey> list) throws DelegateException {
  }
}
