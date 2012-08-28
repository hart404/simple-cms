package simple.cms



import org.junit.*
import grails.test.mixin.*

@TestFor(MenuController)
@Mock(SCMSMenu)
class MenuControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/SCMSMenu/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.menuInstanceList.size() == 0
        assert model.menuInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.menuInstance != null
    }

    void testSave() {
        controller.save()

        assert model.menuInstance != null
        assert view == '/SCMSMenu/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSMenu/show/1'
        assert controller.flash.message != null
        assert SCMSMenu.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenu/list'

        populateValidParams(params)
        def SCMSMenu = new SCMSMenu(params)

        assert SCMSMenu.save() != null

        params.id = SCMSMenu.id

        def model = controller.show()

        assert model.menuInstance == SCMSMenu
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenu/list'

        populateValidParams(params)
        def SCMSMenu = new SCMSMenu(params)

        assert SCMSMenu.save() != null

        params.id = SCMSMenu.id

        def model = controller.edit()

        assert model.menuInstance == SCMSMenu
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenu/list'

        response.reset()

        populateValidParams(params)
        def SCMSMenu = new SCMSMenu(params)

        assert SCMSMenu.save() != null

        // test invalid parameters in update
        params.id = SCMSMenu.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSMenu/edit"
        assert model.menuInstance != null

        SCMSMenu.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSMenu/show/$SCMSMenu.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSMenu.clearErrors()

        populateValidParams(params)
        params.id = SCMSMenu.id
        params.version = -1
        controller.update()

        assert view == "/SCMSMenu/edit"
        assert model.menuInstance != null
        assert model.menuInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenu/list'

        response.reset()

        populateValidParams(params)
        def SCMSMenu = new SCMSMenu(params)

        assert SCMSMenu.save() != null
        assert SCMSMenu.count() == 1

        params.id = SCMSMenu.id

        controller.delete()

        assert SCMSMenu.count() == 0
        assert SCMSMenu.get(SCMSMenu.id) == null
        assert response.redirectedUrl == '/SCMSMenu/list'
    }
}
