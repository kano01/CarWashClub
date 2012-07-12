package au.com.carwashclub.magento



import org.junit.*
import grails.test.mixin.*

@TestFor(SalesFlatOrderController)
@Mock(SalesFlatOrder)
class SalesFlatOrderControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/salesFlatOrder/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.salesFlatOrderInstanceList.size() == 0
        assert model.salesFlatOrderInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.salesFlatOrderInstance != null
    }

    void testSave() {
        controller.save()

        assert model.salesFlatOrderInstance != null
        assert view == '/salesFlatOrder/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/salesFlatOrder/show/1'
        assert controller.flash.message != null
        assert SalesFlatOrder.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrder/list'


        populateValidParams(params)
        def salesFlatOrder = new SalesFlatOrder(params)

        assert salesFlatOrder.save() != null

        params.id = salesFlatOrder.id

        def model = controller.show()

        assert model.salesFlatOrderInstance == salesFlatOrder
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrder/list'


        populateValidParams(params)
        def salesFlatOrder = new SalesFlatOrder(params)

        assert salesFlatOrder.save() != null

        params.id = salesFlatOrder.id

        def model = controller.edit()

        assert model.salesFlatOrderInstance == salesFlatOrder
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrder/list'

        response.reset()


        populateValidParams(params)
        def salesFlatOrder = new SalesFlatOrder(params)

        assert salesFlatOrder.save() != null

        // test invalid parameters in update
        params.id = salesFlatOrder.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/salesFlatOrder/edit"
        assert model.salesFlatOrderInstance != null

        salesFlatOrder.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/salesFlatOrder/show/$salesFlatOrder.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        salesFlatOrder.clearErrors()

        populateValidParams(params)
        params.id = salesFlatOrder.id
        params.version = -1
        controller.update()

        assert view == "/salesFlatOrder/edit"
        assert model.salesFlatOrderInstance != null
        assert model.salesFlatOrderInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/salesFlatOrder/list'

        response.reset()

        populateValidParams(params)
        def salesFlatOrder = new SalesFlatOrder(params)

        assert salesFlatOrder.save() != null
        assert SalesFlatOrder.count() == 1

        params.id = salesFlatOrder.id

        controller.delete()

        assert SalesFlatOrder.count() == 0
        assert SalesFlatOrder.get(salesFlatOrder.id) == null
        assert response.redirectedUrl == '/salesFlatOrder/list'
    }
}
