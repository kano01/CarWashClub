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

    def String toString(){
        StringBuilder result = new StringBuilder();

        result.append(membershipType.name).append(" ").append(membershipType.optionsMatch);

        return result.toString();

    }

}
