package au.com.carwashclub.domain

import au.com.carwashclub.domain.magento.CustomerEntity
import au.com.carwashclub.domain.magento.SalesFlatOrderItem

class Voucher {

    String token

    String pin

    Date createdDate

    Date expiryDate

    Date usedDate

    def beforeInsert() {
        createdDate = new Date()
    }

    Supplier supplier
    CustomerEntity customer
    Membership membership
    SalesFlatOrderItem salesItem

    static constraints = {
        customer()
        token()
        pin()
        supplier(nullable: true)
        membership(nullable: true)
        createdDate(nullable: true)
        usedDate(nullable: true)
        expiryDate(nullable: true)
        salesItem(nullable: true)
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.token);

        return result.toString();
  }
}
