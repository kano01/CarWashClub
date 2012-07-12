package au.com.carwashclub

import au.com.carwashclub.magento.CustomerEntity

class Membership {

    def MembershipType membershipType
    def CustomerEntity customerEntity

    static constraints = {
        customerEntity()
        membershipType()
    }

}
