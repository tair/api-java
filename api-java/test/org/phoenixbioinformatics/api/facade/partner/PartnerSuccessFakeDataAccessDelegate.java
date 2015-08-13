/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.ApiDelegateFactory;
import org.phoenixbioinformatics.api.bs.api.BsPartner;
import org.phoenixbioinformatics.api.bs.api.PartnerDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * Implements the Partner data access delegate with fake operations to permit
 * unit testing of the Partner facade, with all methods returning data or
 * succeeding without error.
 * 
 * @author Robert J. Muller
 */
public class PartnerSuccessFakeDataAccessDelegate implements
    IPartnerDataAccessDelegate {
  PartnerDelegate delegate = ApiDelegateFactory.getPartnerDelegate();

  @Override
  public PartnerDelegate getDelegate() throws DelegateException {
    return delegate;
  }

  @Override
  public List<BsPartner> getAllObjects(int i) throws DelegateException {
    List<BsPartner> partners = new ArrayList<BsPartner>();
    partners.add(delegate.createPartner("tair",
                                        "TAIR",
                                        "https://www.arabidopsis.org/i/logo2.gif",
                                        "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    partners.add(delegate.createPartner("yfd",
                                        "Your Favorite Database",
                                        "https://www.yfd.org/i/logo.gif",
                                        "https://www.yfd.org/terms_of_use"));
    return partners;
  }

  @Override
  public List<BsPartner> getPartnerById(String id) throws DelegateException {
    List<BsPartner> partners = new ArrayList<BsPartner>();

    if (id.equalsIgnoreCase("tair")) {
      partners.add(delegate.createPartner("tair",
                                          "TAIR",
                                          "https://www.arabidopsis.org/i/logo2.gif",
                                          "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    } else if (id.equalsIgnoreCase("yfd")) {
      partners.add(delegate.createPartner("yfd",
                                          "Your Favorite Database",
                                          "https://www.yfd.org/i/logo.gif",
                                          "https://www.yfd.org/terms_of_use"));
    }

    return partners;
  }

  @Override
  public List<BsPartner> getPartnerByName(String name) throws DelegateException {
    List<BsPartner> partners = new ArrayList<BsPartner>();

    if (name.equalsIgnoreCase("TAIR")) {
      partners.add(delegate.createPartner("tair",
                                          "TAIR",
                                          "https://www.arabidopsis.org/i/logo2.gif",
                                          "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    } else if (name.equalsIgnoreCase("Your Favorite Database")) {
      partners.add(delegate.createPartner("yfd",
                                          "Your Favorite Database",
                                          "https://www.yfd.org/i/logo.gif",
                                          "https://www.yfd.org/terms_of_use"));
    }

    return partners;
  }

  @Override
  public List<BsPartner> getPartnerByUri(String uri) throws DelegateException {
    List<BsPartner> partners = new ArrayList<BsPartner>();

    if (uri.equalsIgnoreCase("www.arabidopsis.org")) {
      partners.add(delegate.createPartner("tair",
                                          "TAIR",
                                          "https://www.arabidopsis.org/i/logo2.gif",
                                          "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    } else if (uri.equalsIgnoreCase("www.yfd.org")) {
      partners.add(delegate.createPartner("yfd",
                                          "Your Favorite Database",
                                          "https://www.yfd.org/i/logo.gif",
                                          "https://www.yfd.org/terms_of_use"));
    }

    return partners;
  }

  @Override
  public void insert(Partner partner) throws DelegateException {
  }

  @Override
  public void insert(List<Partner> list) throws DelegateException {
  }

  @Override
  public void update(Partner partner) throws DelegateException {
  }

  @Override
  public void update(List<Partner> list) throws DelegateException {
  }

  @Override
  public void delete(Partner partner) throws DelegateException {
  }

  @Override
  public void delete(List<Partner> list) throws DelegateException {
  }
}
