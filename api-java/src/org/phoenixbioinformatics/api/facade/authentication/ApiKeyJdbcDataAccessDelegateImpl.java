/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.ApiKeyDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * An implementation of the Partner data access delegate interface that uses
 * JDBC connections rather than JNDI.
 * 
 * @author Robert J. Muller
 */
public class ApiKeyJdbcDataAccessDelegateImpl extends
    AbstractApiKeyDataAccessDelegate {

  /** the JDBC business delegate */
  private final ApiKeyDelegate delegate =
    ApiDelegateFactory.getApiKeyDelegate();

  @Override
  public ApiKeyDelegate getDelegate() throws DelegateException {
    return delegate;
  }
}
