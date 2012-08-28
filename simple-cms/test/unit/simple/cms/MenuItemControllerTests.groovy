package simple.cms



import org.junit.*
import grails.test.mixin.*

@TestFor(MenuItemController)
@Mock(SCMSMenuItem)
class MenuItemControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/SCMSMenuItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.menuItemInstanceList.size() == 0
        assert model.menuItemInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.menuItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.menuItemInstance != null
        assert view == '/SCMSMenuItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSMenuItem/show/1'
        assert controller.flash.message != null
        assert SCMSMenuItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuItem/list'

        populateValidParams(params)
        def SCMSMenuItem = new SCMSMenuItem(params)

        assert SCMSMenuItem.save() != null

        params.id = SCMSMenuItem.id

        def model = controller.show()

        assert model.menuItemInstance == SCMSMenuItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuItem/list'

        populateValidParams(params)
        def SCMSMenuItem = new SCMSMenuItem(params)

        assert SCMSMenuItem.save() != null

        params.id = SCMSMenuItem.id

        def model = controller.edit()

        assert model.menuItemInstance == SCMSMenuItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuItem/list'

        response.reset()

        populateValidParams(params)
        def SCMSMenuItem = new SCMSMenuItem(params)

        assert SCMSMenuItem.save() != null

        // test invalid parameters in update
        params.id = SCMSMenuItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSMenuItem/edit"
        assert model.menuItemInstance != null

        SCMSMenuItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSMenuItem/show/$SCMSMenuItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSMenuItem.clearErrors()

        populateValidParams(params)
        params.id = SCMSMenuItem.id
        params.version = -1
        controller.update()

        assert view == "/SCMSMenuItem/edit"
        assert model.menuItemInstance != null
        assert model.menuItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuItem/list'

        response.reset()

        populateValidParams(params)
        def SCMSMenuItem = new SCMSMenuItem(params)

        assert SCMSMenuItem.save() != null
        assert SCMSMenuItem.count() == 1

        params.id = SCMSMenuItem.id

        controller.delete()

        assert SCMSMenuItem.count() == 0
        assert SCMSMenuItem.get(SCMSMenuItem.id) == null
        assert response.redirectedUrl == '/SCMSMenuItem/list'
    }
}
