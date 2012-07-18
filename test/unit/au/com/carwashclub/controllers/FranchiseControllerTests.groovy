package au.com.carwashclub.controllers

import grails.test.mixin.*
import au.com.carwashclub.domain.Franchise
import au.com.carwashclub.controllers.FranchiseController

@TestFor(FranchiseController)
@Mock(Franchise)
class FranchiseControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/franchise/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.franchiseInstanceList.size() == 0
        assert model.franchiseInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.franchiseInstance != null
    }

    void testSave() {
        controller.save()

        assert model.franchiseInstance != null
        assert view == '/franchise/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/franchise/show/1'
        assert controller.flash.message != null
        assert Franchise.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/franchise/list'


        populateValidParams(params)
        def franchise = new Franchise(params)

        assert franchise.save() != null

        params.id = franchise.id

        def model = controller.show()

        assert model.franchiseInstance == franchise
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/franchise/list'


        populateValidParams(params)
        def franchise = new Franchise(params)

        assert franchise.save() != null

        params.id = franchise.id

        def model = controller.edit()

        assert model.franchiseInstance == franchise
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/franchise/list'

        response.reset()


        populateValidParams(params)
        def franchise = new Franchise(params)

        assert franchise.save() != null

        // test invalid parameters in update
        params.id = franchise.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/franchise/edit"
        assert model.franchiseInstance != null

        franchise.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/franchise/show/$franchise.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        franchise.clearErrors()

        populateValidParams(params)
        params.id = franchise.id
        params.version = -1
        controller.update()

        assert view == "/franchise/edit"
        assert model.franchiseInstance != null
        assert model.franchiseInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/franchise/list'

        response.reset()

        populateValidParams(params)
        def franchise = new Franchise(params)

        assert franchise.save() != null
        assert Franchise.count() == 1

        params.id = franchise.id

        controller.delete()

        assert Franchise.count() == 0
        assert Franchise.get(franchise.id) == null
        assert response.redirectedUrl == '/franchise/list'
    }
}
