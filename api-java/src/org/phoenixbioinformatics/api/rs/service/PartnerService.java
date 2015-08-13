/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.rs.service;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.phoenixbioinformatics.api.facade.ApiException;
import org.phoenixbioinformatics.api.facade.partner.IPartnerFacade;
import org.phoenixbioinformatics.api.facade.partner.Partner;
import org.phoenixbioinformatics.api.facade.partner.PartnerFacade;
import org.phoenixbioinformatics.api.facade.partner.PartnerFacadeSuccessFake;
import org.phoenixbioinformatics.api.facade.partner.PartnerJndiDataAccessDelegateImpl;
import org.phoenixbioinformatics.api.facade.partner.PartnerSuccessFakeDataAccessDelegate;


/**
 * The partners web service that implements the API for managing partner
 * software systems; the partners API does not require API authentication
 * 
 * @author Robert J. Muller
 */
@Path("/partners")
public class PartnerService extends AbstractWebService {
  /** serial version UID for serializable class */
  private static final long serialVersionUID = 1L;

  /** logger for this class */
  private static final Logger logger = Logger.getLogger(PartnerService.class);

  /** the partner API implementation */
  private IPartnerFacade partnerApi;

  // errors and messages
  private static final String GET_PARTNERS_ERROR = "Error getting partners";
  private static final String GET_PARTNER_BY_ID_ERROR =
    "Error getting partner by id";
  private static final String GET_PARTNER_BY_NAME_ERROR =
    "Error getting partner by name";
  private static final String GET_PARTNER_BY_URI_ERROR =
    "Error getting partner by URI";
  private static final String API_EXCEPTION_MSG = "API Exception: ";

  /**
   * Create a PartnerService object with the production partnerFacade using a
   * JNDI data access delegate. The REST web service RESTEASY implementation
   * automatically calls this constructor.
   */
  public PartnerService() {
    super();
    this.partnerApi =
      new PartnerFacade(new PartnerJndiDataAccessDelegateImpl());
  }

  @GET
  @Produces("application/json")
  public Response getPartnersJson(@QueryParam("partnerId") String partnerId,
                                  @QueryParam("name") String name,
                                  @QueryParam("uri") String uri,
                                  @QueryParam("fake") Boolean fake) {
    Response response = null;

    // Default fake setting to false.
    setFake(fake != null ? fake : false);

    try {
      // Decide which query to execute based on inputs.
      if (partnerId != null) {
        response = getPartnerById(partnerId, response);
      } else if (name != null) {
        response = getPartnerByName(name, response);
      } else if (uri != null) {
        response = getPartnerByUri(uri, response);
      } else {
        response = getAllPartners(response);
      }
    } catch (Exception e) {
      logger.error(GET_PARTNERS_ERROR, e);
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  /**
   * If a flag is set to true, set the api to the fake implementation.
   *
   * @param fake whether to use a fake API implementation
   */
  private void setFake(Boolean fake) {
    // Reset to a fake-based test if the fake flag is set true by the URI.
    if (fake != null && fake) {
      partnerApi =
        new PartnerFacadeSuccessFake(new PartnerSuccessFakeDataAccessDelegate());
    }
  }

  /**
   * Get a partner in an array by the partner id.
   *
   * @param partnerId the partner id to query
   * @param response the response object
   * @return the built response
   */
  private Response getPartnerById(String partnerId, Response response) {
    List<Partner> dtos;
    try {
      dtos = partnerApi.getPartnersById(partnerId);
      response = getPartnerStatus(dtos);
    } catch (ApiException e) {
      logger.error(GET_PARTNER_BY_ID_ERROR, e);
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  /**
   * Get a partner in an array by the partner name.
   *
   * @param name the name to query
   * @param response the response object
   * @return the built response
   */
  private Response getPartnerByName(String name, Response response) {
    List<Partner> dtos;
    try {
      dtos = partnerApi.getPartnersByName(name);
      response = getPartnerStatus(dtos);
    } catch (ApiException e) {
      logger.error(GET_PARTNER_BY_NAME_ERROR, e);
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  /**
   * Get a partner in an array by the input URI that matches to a regular
   * expression pattern.
   *
   * @param uri the input URI to match
   * @param response the response object
   * @return the built response
   */
  private Response getPartnerByUri(String uri, Response response) {
    List<Partner> dtos;
    try {
      dtos = partnerApi.getPartnersByUri(uri);
      response = getPartnerStatus(dtos);
    } catch (ApiException e) {
      logger.error(GET_PARTNER_BY_URI_ERROR, e);
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  /**
   * Get all the partners in the database.
   *
   * @param response the response object
   * @return the built response
   */
  private Response getAllPartners(Response response) {
    List<Partner> dtos;
    try {
      dtos = partnerApi.getPartners();
      response = getPartnerStatus(dtos);
    } catch (ApiException e) {
      logger.error(GET_PARTNERS_ERROR, e);
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  /**
   * Given a list of DTOs, determine the response status and build the JSON
   * entity. The list should have at least one partner; if it doesn't, the
   * method sets the status to NOT_FOUND and generates no JSON.
   *
   * @param dtos a list of Partner DTOs
   * @return the built response
   */
  private Response getPartnerStatus(List<Partner> dtos) {
    Response response;
    if (dtos == null || dtos.size() == 0) {
      // No partners found, set response and return empty JSON
      response = Response.status(Response.Status.NOT_FOUND).build();
    } else {
      response = Response.ok(generateJson(dtos, logger)).build();
    }
    return response;
  }

  /**
   * Insert a new Partner object.
   *
   * @param partnerId the unique identifier for the partner (required)
   * @param name the unique name for the partner (required)
   * @param logoUri the URI for the partner's logo resource (optional)
   * @param termOfServiceUri the URI for the partner's site terms of service
   *          (optional)
   * @return the response
   */
  @POST
  @Produces("application/json")
  public Response insertPartnerJson(@FormParam("partnerId") String partnerId,
                                    @FormParam("name") String name,
                                    @FormParam("logoUri") String logoUri,
                                    @FormParam("termOfServiceUri") String termOfServiceUri,
                                    @QueryParam("fake") Boolean fake) {
    Response response = null;

    // Default fake setting to false.
    setFake(fake != null ? fake : false);

    logger.info("Form params: " + partnerId + ", " + name + ", " + logoUri
                + ", " + termOfServiceUri);

    try {
      Partner partner = new Partner(partnerId, name, logoUri, termOfServiceUri);
      partnerApi.createPartner(partner);
      List<Partner> partners = new ArrayList<Partner>(1);
      partners.add(partner);
      response =
        Response.status(Response.Status.CREATED).entity(generateJson(partners,
                                                                     logger)).build();
    } catch (ApiException e) {
      addMessage(API_EXCEPTION_MSG + e.getMessage());
      response =
        Response.status(Response.Status.BAD_REQUEST).entity(getMessagesAsJson(logger)).build();
    } catch (Exception e) {
      addMessage(e.getMessage());
      response =
        Response.status(Response.Status.BAD_REQUEST).entity(getMessagesAsJson(logger)).build();
    }
    return response;
  }

  /**
   * Update an existing Partner object. Note that all parameters are form
   * parameters.
   *
   * @param partnerId the unique identifier for the partner (required as query
   *          param)
   * @param fake whether to use the fake delegate
   * @param name the unique name for the partner (required as form param)
   * @param logoUri the URI for the partner's logo resource (optional as form
   *          param)
   * @param termOfServiceUri the URI for the partner's site terms of service
   *          (optional as form param)
   * @return the response
   */
  @PUT
  @Produces("application/json")
  public Response updatePartnerJson(@FormParam("partnerId") String partnerId,
                                    @FormParam("fake") Boolean fake,
                                    @FormParam("name") String name,
                                    @FormParam("logoUri") String logoUri,
                                    @FormParam("termOfServiceUri") String termOfServiceUri) {
    Response response = null;

    // Default fake setting to false.
    setFake(fake != null ? fake : false);

    logger.info("Form params: " + partnerId + ", " + name + ", " + logoUri
                + ", " + termOfServiceUri);

    try {
      Partner partner = new Partner(partnerId, name, logoUri, termOfServiceUri);
      partnerApi.updatePartner(partner);
      response =
        Response.status(Response.Status.OK).entity(getMessagesAsJson(logger)).build();
    } catch (ApiException e) {
      addMessage(API_EXCEPTION_MSG + e.getMessage());
      response =
        Response.status(Response.Status.BAD_REQUEST).entity(getMessagesAsJson(logger)).build();
    } catch (Exception e) {
      addMessage(e.getMessage());
      response =
        Response.status(Response.Status.BAD_REQUEST).entity(getMessagesAsJson(logger)).build();
    }
    return response;
  }

  /**
   * Delete a partner identified by partner id.
   *
   * @param partnerId the partner id identifying the partner to delete
   * @param fake whether to use the fake delegate
   * @return the response
   */
  @DELETE
  @Produces("application/json")
  public Response deletePartnerJson(@QueryParam("partnerId") String partnerId,
                                    @QueryParam("fake") Boolean fake) {
    Response response = null;

    // Default fake setting to false.
    setFake(fake != null ? fake : false);

    try {
      // Create a DTO with only the id being used to identify the partner to
      // delete.
      Partner partner = new Partner(partnerId, "None", null, null);
      partnerApi.deletePartner(partner);
    } catch (ApiException e) {
      addMessage(API_EXCEPTION_MSG + e.getMessage());
      response =
        Response.status(Response.Status.BAD_REQUEST).entity(getMessagesAsJson(logger)).build();
    } catch (Exception e) {
      addMessage(e.getMessage());
      response =
        Response.status(Response.Status.BAD_REQUEST).entity(getMessagesAsJson(logger)).build();
    }
    return response;
  }

}
