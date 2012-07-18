package au.com.carwashclub.controllers.magento

import au.com.carwashclub.domain.magento.CustomerEntity

class CustomerEntityController {

    static allowedMethods = [save: "GET", update: "GET", delete: "GET"]

    static scaffold = CustomerEntity
}
