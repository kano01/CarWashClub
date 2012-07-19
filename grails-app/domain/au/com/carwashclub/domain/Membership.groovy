package au.com.carwashclub.domain

import au.com.carwashclub.domain.magento.CustomerEntity

class Membership {

    def MembershipType membershipType
    def CustomerEntity customerEntity

    static hasMany = [vouchers: Voucher]
    static constraints = {
        customerEntity()
        membershipType()
    }

}
