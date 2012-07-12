package au.com.carwashclub.magento



import org.junit.*
import grails.test.mixin.*

@TestFor(SalesFlatOrderItemController)
@Mock(SalesFlatOrderItem)
class SalesFlatOrderItemControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/salesFlatOrderItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.salesFlatOrderItemInstanceList.size() == 0
        assert model.salesFlatOrderItemInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.salesFlatOrderItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.salesFlatOrderItemInstance != null
        assert view == '/salesFlatOrderItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/salesFlatOrderItem/show/1'
        assert controller.flash.message != null
        assert SalesFlatOrderItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrderItem/list'


        populateValidParams(params)
        def salesFlatOrderItem = new SalesFlatOrderItem(params)

        assert salesFlatOrderItem.save() != null

        params.id = salesFlatOrderItem.id

        def model = controller.show()

        assert model.salesFlatOrderItemInstance == salesFlatOrderItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrderItem/list'


        populateValidParams(params)
        def salesFlatOrderItem = new SalesFlatOrderItem(params)

        assert salesFlatOrderItem.save() != null

        params.id = salesFlatOrderItem.id

        def model = controller.edit()

        assert model.salesFlatOrderItemInstance == salesFlatOrderItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrderItem/list'

        response.reset()


        populateValidParams(params)
        def salesFlatOrderItem = new SalesFlatOrderItem(params)

        assert salesFlatOrderItem.save() != null

        // test invalid parameters in update
        params.id = salesFlatOrderItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/salesFlatOrderItem/edit"
        assert model.salesFlatOrderItemInstance != null

        salesFlatOrderItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/salesFlatOrderItem/show/$salesFlatOrderItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        salesFlatOrderItem.clearErrors()

        populateValidParams(params)
        params.id = salesFlatOrderItem.id
        params.version = -1
        controller.update()

        assert view == "/salesFlatOrderItem/edit"
        assert model.salesFlatOrderItemInstance != null
        assert model.salesFlatOrderItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrderItem/list'

        response.reset()

        populateValidParams(params)
        def salesFlatOrderItem = new SalesFlatOrderItem(params)

        assert salesFlatOrderItem.save() != null
        assert SalesFlatOrderItem.count() == 1

        params.id = salesFlatOrderItem.id

        controller.delete()

        assert SalesFlatOrderItem.count() == 0
        assert SalesFlatOrderItem.get(salesFlatOrderItem.id) == null
        assert response.redirectedUrl == '/salesFlatOrderItem/list'
    }
}
