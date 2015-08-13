/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.List;

import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * Implements the Partner Facade API with fake operations to permit unit testing
 * of clients, with all methods returning exceptions.
 * 
 * @author Robert J. Muller
 */
public class PartnerFacadeFailureFake implements IPartnerFacade {
  /** the partner data access delegate that encapsulates data access operations */
  @SuppressWarnings("unused")
  private final IPartnerDataAccessDelegate delegate;

  /**
   * Create a PartnerFacade object. This sets a data access delegate that
   * encapsulates all data access operations, permitting fakes to do unit
   * testing. This implementation should get a fake delegate, but basically this
   * implementation just returns DTOs and exceptions as required.
   * 
   * @param delegate the data access delegate
   */
  public PartnerFacadeFailureFake(IPartnerDataAccessDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<Partner> getPartners() throws ApiException {
    throw new ApiException("getPartners() failed");
  }

  @Override
  public List<Partner> getPartnersById(String partnerId) throws ApiException {
    throw new ApiException("getPartnersById() failed");
  }

  @Override
  public List<Partner> getPartnersByName(String name) throws ApiException {
    throw new ApiException("getPartnersByName() failed");
  }

  @Override
  public List<Partner> getPartnersByUri(String uri) throws ApiException {
    throw new ApiException("getPartnersByUri() failed");
  }

  @Override
  public void createPartner(Partner partner) throws ApiException {
    throw new ApiException("createPartner() failed");
  }

  @Override
  public void updatePartner(Partner partner) throws ApiException {
    throw new ApiException("updatePartner() failed");
  }

  @Override
  public void deletePartner(Partner partner) throws ApiException {
    throw new ApiException("deletePartner() failed");
  }
}
