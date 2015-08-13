/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.phoenixbioinformatics.api.RandomString;
import org.phoenixbioinformatics.api.facade.ApiException;
import org.phoenixbioinformatics.api.facade.authentication.AuthenticationFacadeTest;


/**
 * Integration-test the Partner API facade class (PartnerFacade). This uses the
 * real delegates instead of the fakes.
 * 
 * @author Robert J. Muller
 */
public class PartnerFacadeIntegrationTest {
  private static Logger logger =
    Logger.getLogger(AuthenticationFacadeTest.class);
  private static final String TERMS_1 = "https://myterms.html";
  private static final String LOGO_1 = "https://mylogo.gif";
  private static final String PARTNER_ID_1 = "tair";
  private static final String PARTNER_ID_2 = "yfd";
  private static final String PARTNER_NAME_1 = "TAIR";
  private static final String PARTNER_URI_1 = "www.arabidopsis.org";

  private static final String PARTNER_ID_3 = RandomString.getString(30);
  private static final String PARTNER_NAME_3 = PARTNER_ID_3.toUpperCase();

  // Basic success tests

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#getPartners()}
   * .
   */
  @Test
  public void testGetPartners() {
    PartnerFacade api =
      new PartnerFacade(new PartnerJdbcDataAccessDelegateImpl());
    try {
      List<Partner> partners = api.getPartners();
      assertTrue("No partners list created", partners != null);
      // At least two default partners should be there.
      assertTrue("Wrong number of partners: " + partners.size(),
                 partners.size() >= 2);
     // Find the default partners.
      boolean found = false;
      for (Partner partner : partners) {
        if ((PARTNER_ID_1.equals(partner.getPartnerId()) || PARTNER_ID_2.equals(partner.getPartnerId()))) {
          found = true;
        }
      }
      assertTrue("Standard partners not found by query-all", found);
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
      new PartnerFacade(new PartnerJdbcDataAccessDelegateImpl());
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
      new PartnerFacade(new PartnerJdbcDataAccessDelegateImpl());
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
      new PartnerFacade(new PartnerJdbcDataAccessDelegateImpl());
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
   * 
   * @throws ApiException when the inserted partner could not be removed
   */
  @Test
  public void testCreatePartner() throws ApiException {
    PartnerFacade api =
      new PartnerFacade(new PartnerJdbcDataAccessDelegateImpl());
    Partner partner = null;
    try {
      partner = getNewPartner();
      api.createPartner(getNewPartner());
    } catch (ApiException e) {
      logger.error("Failed on creating partner", e);
      fail("Failed on creating partner");
    } finally {
      api.deletePartner(partner);
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.partner.PartnerFacade#updatePartner(org.phoenixbioinformatics.api.dto.Partner)}
   * .
   * 
   * @throws ApiException when the inserted partner could not be removed
   */
  @Test
  public void testUpdatePartner() throws ApiException {
    PartnerFacade api =
      new PartnerFacade(new PartnerJdbcDataAccessDelegateImpl());
    Partner partner = null;
    try {
      partner = getNewPartner();
      api.createPartner(partner);
      String originalName = partner.getName();
      partner.setName(RandomString.getString(30));
      api.updatePartner(partner);
      List<Partner> partners = api.getPartnersById(partner.getPartnerId());
      assertTrue("No partner list generated", partners != null);
      assertTrue("No partner queried", partners.size() > 0);
      assertTrue("Name not updated", !originalName.equals(partners.get(0)));
    } catch (ApiException e) {
      logger.error("Failed on updating partner", e);
      fail("Failed on updating partner");
    } finally {
      api.deletePartner(partner);
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
      new PartnerFacade(new PartnerJdbcDataAccessDelegateImpl());
    Partner partner = null;
    try {
      partner = getNewPartner();
      api.createPartner(partner);
      api.deletePartner(partner);
      List<Partner> partners = api.getPartnersById(partner.getPartnerId());
      assertTrue("No partner list generated", partners != null);
      assertTrue("Partner not deleted", partners.size() == 0);
    } catch (ApiException e) {
      logger.error("Failed on deleting partner", e);
      fail("Failed on deleting partner");
    }
  }

  /**
   * Get a Partner object suitable for inserting
   *
   * @return the partner object
   * @throws ApiException when a required parameter is null
   */
  private Partner getNewPartner() throws ApiException {
    return new Partner(PARTNER_ID_3, PARTNER_NAME_3, LOGO_1, TERMS_1);
  }
}
