/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.partner;


import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsPartner;
import org.phoenixbioinformatics.api.bs.api.PartnerDelegate;

import com.poesys.bs.delegate.DelegateException;


/**
 * An interface that abstracts the data access operations for the Partner API.
 * This allows for "fake" implementations for unit testing and real ones for
 * production.
 * 
 * @author Robert J. Muller
 */
public interface IPartnerDataAccessDelegate {

  /**
   * Get the API/DB partner delegate; used for integration tests and production
   *
   * @return a Partner business delegate
   * @throws DelegateException when there is a data access problem
   */
  PartnerDelegate getDelegate() throws DelegateException;

  /**
   * Get all the Partner objects.
   *
   * @param i the fetch count optimizer
   * @return a list of partner business objects
   * @throws DelegateException when there is a data access problem
   */
  List<BsPartner> getAllObjects(int i) throws DelegateException;

  /**
   * Get a partner by id, returning it in a list.
   *
   * @param id the id
   * @return a list of partners containing a single partner
   * @throws DelegateException when there is a data access problem
   */
  List<BsPartner> getPartnerById(String id) throws DelegateException;

  /**
   * Get a partner by name, returning it as a list.
   *
   * @param name the name
   * @return a list of partners containing a single partner
   * @throws DelegateException when there is a data access problem
   */
  List<BsPartner> getPartnerByName(String name) throws DelegateException;

  /**
   * Get a partner using a URI that matches one of the URI regexp patterns
   *
   * @param uri a URI (such as www.arabidopsis.org)
   * @return a list of partners containing a single partner
   * @throws DelegateException when there is a data access problem
   */
  List<BsPartner> getPartnerByUri(String uri) throws DelegateException;

  /**
   * Insert a partner.
   *
   * @param partner the partner to insert
   * @throws DelegateException when there is a data access problem
   */
  void insert(Partner partner) throws DelegateException;

  /**
   * Insert a list of partners.
   *
   * @param list the list of partners
   * @throws DelegateException when there is a data access problem
   */
  void insert(List<Partner> list) throws DelegateException;

  /**
   * Update a partner.
   *
   * @param partner the partner to update
   * @throws DelegateException when there is a data access problem
   */
  void update(Partner partner) throws DelegateException;

  /**
   * Update a list of partners.
   *
   * @param list the list of partners
   * @throws DelegateException when there is a data access problem
   */
  void update(List<Partner> list) throws DelegateException;

  /**
   * Delete a partner.
   *
   * @param partner the partner to delete
   * @throws DelegateException when there is a data access problem
   */
  void delete(Partner partner) throws DelegateException;

  /**
   * Delete a list of partners.
   *
   * @param list the list of partners
   * @throws DelegateException when there is a data access problem
   */
  void delete(List<Partner> list) throws DelegateException;
}
