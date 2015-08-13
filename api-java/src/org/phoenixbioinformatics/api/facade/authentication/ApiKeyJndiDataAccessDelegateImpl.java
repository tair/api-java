/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.ApiKeyDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * An implementation of the Partner data access delegate interface that uses
 * JNDI connections rather than JDBC, appropriate for use in a JEE container.
 * 
 * @author Robert J. Muller
 */
public class ApiKeyJndiDataAccessDelegateImpl extends
    AbstractApiKeyDataAccessDelegate {

  /** the JNDI business delegate */
  final ApiKeyDelegate delegate = ApiDelegateFactory.getJndiApiKeyDelegate();

  @Override
  public ApiKeyDelegate getDelegate() throws DelegateException {
    return delegate;
  }
}
