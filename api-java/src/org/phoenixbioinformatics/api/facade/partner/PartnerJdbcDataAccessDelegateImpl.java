/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.PartnerDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * An implementation of the Partner data access delegate interface that uses
 * JDBC connections rather than JNDI.
 * 
 * @author Robert J. Muller
 */
public class PartnerJdbcDataAccessDelegateImpl extends
    AbstractPartnerDataAccessDelegate {

  /** the JDBC business delegate */
  private final PartnerDelegate delegate =
    ApiDelegateFactory.getPartnerDelegate();

  @Override
  public PartnerDelegate getDelegate() throws DelegateException {
    return delegate;
  }
}
