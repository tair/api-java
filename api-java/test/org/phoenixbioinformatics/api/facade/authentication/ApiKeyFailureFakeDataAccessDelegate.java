/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.BsApiKey;
import org.phoenixbioinformatics.api.bs.api.ApiKeyDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * Implements the ApiKey data access delegate with fake operations to permit
 * unit testing of the ApiKey facade, with all methods returning exceptions.
 * 
 * @author Robert J. Muller
 */
public class ApiKeyFailureFakeDataAccessDelegate implements
    IApiKeyDataAccessDelegate {
  ApiKeyDelegate delegate = ApiDelegateFactory.getApiKeyDelegate();

  @Override
  public ApiKeyDelegate getDelegate() throws DelegateException {
    throw new DelegateException("Failed getDelegate()");
  }

  @Override
  public List<BsApiKey> getAllObjects(int i) throws DelegateException {
    throw new DelegateException("Failed getAllObjects()");
  }

  @Override
  public List<BsApiKey> getApiKeyById(BigInteger id) throws DelegateException {
    throw new DelegateException("Failed getApiKeyById()");
  }

  @Override
  public void process(ApiKey ApiKey) throws DelegateException {
    throw new DelegateException("Failed process() with single ApiKey");
  }

  @Override
  public void process(List<ApiKey> list) throws DelegateException {
    throw new DelegateException("Failed process() with multiple-ApiKey list");
  }
}
