/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.PartnerDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * An implementation of the Partner data access delegate interface that uses
 * JNDI connections rather than JDBC, appropriate for use in a JEE container.
 * 
 * @author Robert J. Muller
 */
public class PartnerJndiDataAccessDelegateImpl extends
    AbstractPartnerDataAccessDelegate {

  /** the JNDI business delegate */
  final PartnerDelegate delegate =
    ApiDelegateFactory.getJndiPartnerDelegate();

  @Override
  public PartnerDelegate getDelegate() throws DelegateException {
    return delegate;
  }
}
