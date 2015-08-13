package org.phoenixbioinformatics.api.facade.partner;


import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsPartner;
import org.phoenixbioinformatics.api.db.api.ApiFactory;

import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.db.pk.NaturalPrimaryKey;


/**
 * An abstract implementation of the Partner data access delegate interface that
 * provides production data access methods to the partner facade. Subclasses
 * must implement the actual delegate, permitting different kinds of delegate.
 * 
 * @author Robert J. Muller
 */
public abstract class AbstractPartnerDataAccessDelegate implements
    IPartnerDataAccessDelegate {

  public AbstractPartnerDataAccessDelegate() {
    super();
  }

  @Override
  public List<BsPartner> getAllObjects(int i) throws DelegateException {
    return getDelegate().getAllObjects(i);
  }

  @Override
  public List<BsPartner> getPartnerById(String partnerId)
      throws DelegateException {
    List<BsPartner> list = new ArrayList<BsPartner>(1);
    IPrimaryKey key = ApiFactory.getPartnerPrimaryKey(partnerId);
    list.add(getDelegate().getObject((NaturalPrimaryKey)key));
    return list;
  }

  @Override
  public List<BsPartner> getPartnerByName(String name) throws DelegateException {
    List<BsPartner> list = new ArrayList<BsPartner>(1);
    list.add(getDelegate().getPartnerByName(name));
    return list;
  }

  @Override
  public List<BsPartner> getPartnerByUri(String uri) throws DelegateException {
    List<BsPartner> list = new ArrayList<BsPartner>(1);
    list.add(getDelegate().getPartnerByUri(uri));
    return list;
  }

  @Override
  public void insert(Partner partner) throws DelegateException {
    List<BsPartner> list = new ArrayList<BsPartner>(1);
    list.add(getDelegate().createPartner(partner.getPartnerId(),
                                         partner.getName(),
                                         partner.getLogoUri(),
                                         partner.getTermOfServiceUri()));
    getDelegate().process(list);
  }

  @Override
  public void insert(List<Partner> list) throws DelegateException {
    List<BsPartner> bsList = new ArrayList<BsPartner>(list.size());
    if (bsList != null) {
      for (Partner partner : list) {
        bsList.add(getDelegate().createPartner(partner.getPartnerId(),
                                               partner.getName(),
                                               partner.getLogoUri(),
                                               partner.getTermOfServiceUri()));
      }
    }
    getDelegate().process(bsList);
  }

  @Override
  public void update(Partner partner) throws DelegateException {
    List<BsPartner> list = new ArrayList<BsPartner>(1);
    list.add(updateObject(partner));
    getDelegate().process(list);
  }

  @Override
  public void update(List<Partner> list) throws DelegateException {
    List<BsPartner> bsList = new ArrayList<BsPartner>(list.size());
    if (bsList != null) {
      for (Partner partner : list) {
        bsList.add(updateObject(partner));
      }
    }
    getDelegate().process(bsList);
  }

  @Override
  public void delete(Partner partner) throws DelegateException {
    getDelegate().delete(deleteObject(partner));
  }

  @Override
  public void delete(List<Partner> list) throws DelegateException {
    List<BsPartner> bsList = new ArrayList<BsPartner>(list.size());
    if (bsList != null) {
      for (Partner partner : list) {
        getDelegate().delete(deleteObject(partner));
      }
    }
  }

  /**
   * Update the data in an object from DTO data and return the updated object.
   *
   * @param partner the DTO
   * @return the updated business object
   */
  private BsPartner updateObject(Partner partner) {
    // Query the object for update.
    IPrimaryKey key = ApiFactory.getPartnerPrimaryKey(partner.getPartnerId());
    BsPartner businessObject = getDelegate().getObject((NaturalPrimaryKey)key);
    // Update the object's data.
    businessObject.setName(partner.getName());
    businessObject.setLogoUri(partner.getLogoUri());
    businessObject.setTermOfServiceUri(partner.getTermOfServiceUri());
    return businessObject;
  }

  /**
   * Mark an object for deletion, identifying the object by the key in a DTO.
   *
   * @param partner the DTO
   * @return the marked-for-delete business object
   */
  private BsPartner deleteObject(Partner partner) {
    // Query the object for delete.
    IPrimaryKey key = ApiFactory.getPartnerPrimaryKey(partner.getPartnerId());
    BsPartner businessObject = getDelegate().getObject((NaturalPrimaryKey)key);
    // Mark for delete.
    businessObject.delete();
    return businessObject;
  }
}