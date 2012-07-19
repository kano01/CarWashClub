package au.com.carwashclub.domain

import au.com.carwashclub.domain.magento.CustomerEntity

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

    static constraints = {
        customer()
        supplier(nullable: true)
        membership(nullable: true)
        token()
        createdDate(nullable: true)
        usedDate(nullable: true)
        expiryDate(nullable: true)
    }
}
