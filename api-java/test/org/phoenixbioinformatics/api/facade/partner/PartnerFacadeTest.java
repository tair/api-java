/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.phoenixbioinformatics.api.facade.ApiException;
import org.phoenixbioinformatics.api.facade.authentication.AuthenticationFacadeTest;


/**
 * Test the Partner API facade class (PartnerFacade).
 * 
 * @author Robert J. Muller
 */
public class PartnerFacadeTest {
  private static Logger logger =
    Logger.getLogger(AuthenticationFacadeTest.class);
  private static final String TERMS_1 = "https://myterms.html";
  private static final String LOGO_1 = "https://mylogo.gif";
  private static final String PARTNER_ID_1 = "tair";
  private static final String PARTNER_ID_2 = "yfd";
  private static final String PARTNER_NAME_1 = "TAIR";
  private static final String PARTNER_URI_1 = "www.arabidopsis.org";

  // Basic success tests

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartners()}
   * .
   */
  @Test
  public void testGetPartners() {
    PartnerFacade api =
      new PartnerFacade(new PartnerSuccessFakeDataAccessDelegate());
    try {
      List<Partner> partners = api.getPartners();
      assertTrue("No partners list created", partners != null);
      assertTrue("Wrong number of partners: " + partners.size(),
                 partners.size() == 2);
      for (Partner partner : partners) {
        if (!(PARTNER_ID_1.equals(partner.getPartnerId()) || PARTNER_ID_2.equals(partner.getPartnerId()))) {
          fail("Got wrong partner: " + partner.getPartnerId());
        }
      }
    } catch (ApiException e) {
      logger.error("Failed on getting all partners", e);
      fail("Failed on getting all partners");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartnersById(java.math.BigInteger)}
   * .
   */
  @Test
  public void testGetPartnersById() {
    PartnerFacade api =
      new PartnerFacade(new PartnerSuccessFakeDataAccessDelegate());
    try {
      List<Partner> partners = api.getPartnersById(PARTNER_ID_1);
      assertTrue("No partners list created", partners != null);
      assertTrue("Wrong number of partners: " + partners.size(),
                 partners.size() == 1);
      for (Partner partner : partners) {
        if (!(PARTNER_ID_1.equals(partner.getPartnerId()))) {
          fail("Got wrong partner: " + partner.getPartnerId() + ", expected "
               + PARTNER_ID_1);
        }
      }
    } catch (ApiException e) {
      logger.error("Failed on getting partner by id", e);
      fail("Failed on getting partner by id");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartnersByName(java.lang.String)}
   * .
   */
  @Test
  public void testGetPartnersByName() {
    PartnerFacade api =
      new PartnerFacade(new PartnerSuccessFakeDataAccessDelegate());
    try {
      List<Partner> partners = api.getPartnersByName(PARTNER_NAME_1);
      assertTrue("No partners list created", partners != null);
      assertTrue("Wrong number of partners: " + partners.size(),
                 partners.size() == 1);
      for (Partner partner : partners) {
        if (!(PARTNER_ID_1.equals(partner.getPartnerId()))) {
          fail("Got wrong partner: " + partner.getPartnerId() + ", expected "
               + PARTNER_ID_1);
        }
      }
    } catch (ApiException e) {
      logger.error("Failed on getting partner by name", e);
      fail("Failed on getting partner by name");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartnersByUri(java.lang.String)}
   * .
   */
  @Test
  public void testGetPartnersByUri() {
    PartnerFacade api =
      new PartnerFacade(new PartnerSuccessFakeDataAccessDelegate());
    try {
      List<Partner> partners = api.getPartnersByUri(PARTNER_URI_1);
      assertTrue("No partners list created", partners != null);
      assertTrue("Wrong number of partners: " + partners.size(),
                 partners.size() == 1);
      for (Partner partner : partners) {
        if (!(PARTNER_ID_1.equals(partner.getPartnerId()))) {
          fail("Got wrong partner: " + partner.getPartnerId() + ", expected "
               + PARTNER_ID_1);
        }
      }
    } catch (ApiException e) {
      logger.error("Failed on getting partner by URI", e);
      fail("Failed on getting partner by URI");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#createPartner(org.phoenixbioinformatics.api.dto.Partner)}
   * .
   */
  @Test
  public void testCreatePartner() {
    PartnerFacade api =
      new PartnerFacade(new PartnerSuccessFakeDataAccessDelegate());
    try {
      api.createPartner(getPartner());
    } catch (ApiException e) {
      logger.error("Failed on creating partner", e);
      fail("Failed on creating partner");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#updatePartner(org.phoenixbioinformatics.api.dto.Partner)}
   * .
   */
  @Test
  public void testUpdatePartner() {
    PartnerFacade api =
      new PartnerFacade(new PartnerSuccessFakeDataAccessDelegate());
    try {
      api.updatePartner(getPartner());
    } catch (ApiException e) {
      logger.error("Failed on updating partner", e);
      fail("Failed on updating partner");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#deletePartner(org.phoenixbioinformatics.api.dto.Partner)}
   * .
   */
  @Test
  public void testDeletePartner() {
    PartnerFacade api =
      new PartnerFacade(new PartnerSuccessFakeDataAccessDelegate());
    try {
      api.deletePartner(getPartner());
    } catch (ApiException e) {
      logger.error("Failed on deleting partner", e);
      fail("Failed on deleting partner");
    }
  }

  /**
   * Get a Partner object suitable for testing
   *
   * @return the partner object
   * @throws ApiException when a required parameter is null
   */
  private Partner getPartner() throws ApiException {
    return new Partner(PARTNER_ID_1, PARTNER_NAME_1, LOGO_1, TERMS_1);
  }

  // Basic failure tests

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartners()}
   * .
   */
  @Test
  public void testGetPartnersFailure() {
    PartnerFacade api =
      new PartnerFacade(new PartnerFailureFakeDataAccessDelegate());
    try {
      api.getPartners();
      fail("No exception raised by API on getPartners()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartnersById(java.math.BigInteger)}
   * .
   */
  @Test
  public void testGetPartnersByIdFailure() {
    PartnerFacade api =
      new PartnerFacade(new PartnerFailureFakeDataAccessDelegate());
    try {
      api.getPartnersById(PARTNER_ID_1);
      fail("No exception raised by API on getPartnersById()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartnersByName(java.lang.String)}
   * .
   */
  @Test
  public void testGetPartnersByNameFailure() {
    PartnerFacade api =
      new PartnerFacade(new PartnerFailureFakeDataAccessDelegate());
    try {
      api.getPartnersByName(PARTNER_NAME_1);
      fail("No exception raised by API on api.getPartnersByName()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartnersByUri(java.lang.String)}
   * .
   */
  @Test
  public void testGetPartnersByUriFailure() {
    PartnerFacade api =
      new PartnerFacade(new PartnerFailureFakeDataAccessDelegate());
    try {
      api.getPartnersByUri(PARTNER_URI_1);
      fail("No exception raised by API on getPartnersByUri()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#createPartner(org.phoenixbioinformatics.api.dto.Partner)}
   * .
   */
  @Test
  public void testCreatePartnerFailure() {
    PartnerFacade api =
      new PartnerFacade(new PartnerFailureFakeDataAccessDelegate());
    try {
      api.createPartner(getPartner());
      fail("No exception raised by API on createPartner()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#updatePartner(org.phoenixbioinformatics.api.dto.Partner)}
   * .
   */
  @Test
  public void testUpdatePartnerFailure() {
    PartnerFacade api =
      new PartnerFacade(new PartnerFailureFakeDataAccessDelegate());
    try {
      api.updatePartner(getPartner());
      fail("No exception raised by API on updatePartner()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#deletePartner(org.phoenixbioinformatics.api.dto.Partner)}
   * .
   */
  @Test
  public void testDeletePartnerFailure() {
    PartnerFacade api =
      new PartnerFacade(new PartnerFailureFakeDataAccessDelegate());
    try {
      api.deletePartner(getPartner());
      fail("No exception raised by API on deletePartner()");
    } catch (ApiException e) {
      // successful test
    }
  }
}
