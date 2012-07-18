package au.com.carwashclub.domain.magento

import  au.com.carwashclub.domain.Voucher

class CustomerEntity {

    Short entityTypeId
    Short attributeSetId
    Short websiteId
    String email
    Short groupId
    String incrementId
    Short storeId
    Date createdAt
    Date updatedAt
    Short isActive

    static mapping = {
        id column: "entity_id"
        version false
    }

    static hasMany = [vouchers1: Voucher]
    static constraints = {
        websiteId nullable: true
        email nullable: true
        incrementId nullable: true, maxSize: 50
        storeId nullable: true
        createdAt maxSize: 19
        updatedAt maxSize: 19
    }

    @Override
    public String toString() {
    StringBuilder result = new StringBuilder();

    result.append(this.email);

    return result.toString();
  }
}
