/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.List;

import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.BsPartner;
import org.phoenixbioinformatics.api.bs.api.PartnerDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * Implements the Partner data access delegate with fake operations to permit
 * unit testing of the Partner facade, with all methods returning exceptions.
 * 
 * @author Robert J. Muller
 */
public class PartnerFailureFakeDataAccessDelegate implements
    IPartnerDataAccessDelegate {
  PartnerDelegate delegate = ApiDelegateFactory.getPartnerDelegate();

  @Override
  public PartnerDelegate getDelegate() throws DelegateException {
    throw new DelegateException("Failed getDelegate()");
  }

  @Override
  public List<BsPartner> getAllObjects(int i) throws DelegateException {
    throw new DelegateException("Failed getAllObjects()");
  }

  @Override
  public List<BsPartner> getPartnerById(String id) throws DelegateException {
    throw new DelegateException("Failed getPartnerById()");
  }

  @Override
  public List<BsPartner> getPartnerByName(String name) throws DelegateException {
    throw new DelegateException("Failed getPartnerByName()");
  }

  @Override
  public List<BsPartner> getPartnerByUri(String uri) throws DelegateException {
    throw new DelegateException("Failed getPartnerByUri()");
  }

  @Override
  public void insert(Partner partner) throws DelegateException {
    throw new DelegateException("Failed insert() with single partner");
  }

  @Override
  public void insert(List<Partner> list) throws DelegateException {
    throw new DelegateException("Failed insert() with multiple-partner list");
  }

  @Override
  public void update(Partner partner) throws DelegateException {
    throw new DelegateException("Failed update() with single partner");
  }

  @Override
  public void update(List<Partner> list) throws DelegateException {
    throw new DelegateException("Failed update() with multiple-partner list");
  }

  @Override
  public void delete(Partner partner) throws DelegateException {
    throw new DelegateException("Failed delete() with single partner");
  }

  @Override
  public void delete(List<Partner> list) throws DelegateException {
    throw new DelegateException("Failed delete() with multiple-partner list");
  }
}
