/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * Implements the Partner Facade API with fake operations to permit unit testing
 * of clients, with all methods returning successfully. Keep this source file in
 * the src directory, as it gets used by the web service, which must set the
 * fake as part of its operational code because the test harness can't call it
 * directly and pass in the fake implementation.
 * 
 * @author Robert J. Muller
 */
public class PartnerFacadeSuccessFake implements IPartnerFacade {
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
  public PartnerFacadeSuccessFake(IPartnerDataAccessDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<Partner> getPartners() throws ApiException {
    List<Partner> partners = new ArrayList<Partner>();
    partners.add(new Partner("tair",
                             "TAIR",
                             "https://www.arabidopsis.org/i/logo2.gif",
                             "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    partners.add(new Partner("yfd",
                             "Your Favorite Database",
                             "https://www.yfd.org/i/logo.gif",
                             "https://www.yfd.org/terms_of_use"));
    return partners;
  }

  @Override
  public List<Partner> getPartnersById(String partnerId) throws ApiException {
    List<Partner> partners = new ArrayList<Partner>();

    if (partnerId.equalsIgnoreCase("tair")) {
      partners.add(new Partner("tair",
                               "TAIR",
                               "https://www.arabidopsis.org/i/logo2.gif",
                               "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    } else if (partnerId.equalsIgnoreCase("yfd")) {
      partners.add(new Partner("yfd",
                               "Your Favorite Database",
                               "https://www.yfd.org/i/logo.gif",
                               "https://www.yfd.org/terms_of_use"));
    }

    return partners;
  }

  @Override
  public List<Partner> getPartnersByName(String name) throws ApiException {
    List<Partner> partners = new ArrayList<Partner>();

    if (name == "TAIR") {
      partners.add(new Partner("tair",
                               "TAIR",
                               "https://www.arabidopsis.org/i/logo2.gif",
                               "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    } else if (name.equalsIgnoreCase("Your Favorite Database")) {
      partners.add(new Partner("yfd",
                               "Your Favorite Database",
                               "https://www.yfd.org/i/logo.gif",
                               "https://www.yfd.org/terms_of_use"));
    }

    return partners;
  }

  @Override
  public List<Partner> getPartnersByUri(String uri) throws ApiException {
    List<Partner> partners = new ArrayList<Partner>();

    if (uri.equalsIgnoreCase("www.arabidopsis.org")) {
      partners.add(new Partner("tair",
                               "TAIR",
                               "https://www.arabidopsis.org/i/logo2.gif",
                               "https://www.arabidopsis.org/doc/about/tair_terms_of_use/417"));
    } else if (uri.equalsIgnoreCase("www.yfd.org")) {
      partners.add(new Partner("yfd",
                               "Your Favorite Database",
                               "https://www.yfd.org/i/logo.gif",
                               "https://www.yfd.org/terms_of_use"));
    }

    return partners;
  }

  @Override
  public void createPartner(Partner partner) throws ApiException {
  }

  @Override
  public void updatePartner(Partner partner) throws ApiException {
  }

  @Override
  public void deletePartner(Partner partner) throws ApiException {
  }
}
