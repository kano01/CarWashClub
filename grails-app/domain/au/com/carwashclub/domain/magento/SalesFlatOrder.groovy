package au.com.carwashclub.domain.magento

class SalesFlatOrder {

    String status

    String customerFirstname

    String customerLastname

    Date createdAt

    Date updatedAt

    CustomerEntity customer

    Date orderSent

    Date orderProcessed

    static hasMany = [saleFlatOrderItems: SalesFlatOrderItem]

    static mapping = {
        id column: "entity_id"
        version false
    }

    static constraints = {
        id()
        createdAt()
        customer()
        orderProcessed(nullable: true)
        orderSent(nullable: true)

    }

    public String toString() {
        return id;
    }
}
