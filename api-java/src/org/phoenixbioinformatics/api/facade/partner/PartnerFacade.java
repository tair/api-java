/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsPartner;
import org.phoenixbioinformatics.api.facade.ApiException;

import com.poesys.bs.delegate.DelegateException;


/**
 * The Partner API facade "real" implementation
 * 
 * @author Robert J. Muller
 */
public class PartnerFacade implements IPartnerFacade {
  /** the partner data access delegate that encapsulates data access operations */
  private final IPartnerDataAccessDelegate delegate;

  // error messages
  private static final String QUERY_ERROR = "Error getting partner list by id";
  private static final String QUERY_BY_NAME_ERROR = "Error getting partner list by name";
  private static final String QUERY_BY_URI_ERROR = "Error getting partner list by uri";
  private static final String CREATE_ERROR = "Error creating partner";
  private static final String UPDATE_ERROR = "Error updating partner";
  private static final String DELETE_ERROR = "Error deleting partner";

  /**
   * Create a PartnerFacade object. This sets a data access delegate that
   * encapsulates all data access operations, permitting fakes to do unit
   * testing.
   * 
   * @param delegate the data access delegate
   */
  public PartnerFacade(IPartnerDataAccessDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<Partner> getPartners() throws ApiException {
    List<Partner> partner;
    try {
      partner = getDtoList(delegate.getAllObjects(10));
    } catch (DelegateException e) {
      throw new ApiException(QUERY_ERROR, e);
    }
    return partner;
  }

  @Override
  public List<Partner> getPartnersById(String partnerId) throws ApiException {
    List<Partner> partner;
    try {
      partner = getDtoList(delegate.getPartnerById(partnerId));
    } catch (DelegateException e) {
      throw new ApiException(QUERY_ERROR, e);
    }
    return partner;
  }

  @Override
  public List<Partner> getPartnersByName(String name) throws ApiException {
    List<Partner> partner;
    try {
      partner = getDtoList(delegate.getPartnerByName(name));
    } catch (DelegateException e) {
      throw new ApiException(QUERY_BY_NAME_ERROR, e);
    }
    return partner;
  }

  @Override
  public List<Partner> getPartnersByUri(String uri) throws ApiException {
    List<Partner> partner;
    try {
      partner = getDtoList(delegate.getPartnerByUri(uri));
    } catch (DelegateException e) {
      throw new ApiException(QUERY_BY_URI_ERROR, e);
    }
    return partner;
  }

  @Override
  public void createPartner(Partner partner) throws ApiException {
    try {
      delegate.insert(partner);
    } catch (DelegateException e) {
      throw new ApiException(CREATE_ERROR, e);
    }
  }

  @Override
  public void updatePartner(Partner partner) throws ApiException {
    try {
      delegate.update(partner);
    } catch (DelegateException e) {
      throw new ApiException(UPDATE_ERROR, e);
    }
  }

  @Override
  public void deletePartner(Partner partner) throws ApiException {
    try {
      delegate.delete(partner);
    } catch (DelegateException e) {
      throw new ApiException(DELETE_ERROR, e);
    }
  }

  /**
   * Get a list of API DTOs by extracting data from a list of business objects.
   *
   * @param partners the list of business objects
   * @param list the list of DTOs; empty list if input list is null or empty
   * @throws ApiException when there is a missing required data element
   */
  private List<Partner> getDtoList(List<BsPartner> partners)
      throws ApiException {
    List<Partner> list = new ArrayList<Partner>(partners.size());
    if (partners != null && partners.get(0) != null) {
      for (BsPartner partner : partners) {
        list.add(new Partner(partner.getPartnerId(),
                             partner.getName(),
                             partner.getLogoUri(),
                             partner.getTermOfServiceUri()));
      }
    }
    return list;
  }
}
