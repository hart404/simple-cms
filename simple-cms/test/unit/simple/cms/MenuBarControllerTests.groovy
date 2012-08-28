package simple.cms



import org.junit.*
import grails.test.mixin.*

@TestFor(MenuBarController)
@Mock(SCMSMenuBar)
class MenuBarControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/SCMSMenuBar/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.menuBarInstanceList.size() == 0
        assert model.menuBarInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.menuBarInstance != null
    }

    void testSave() {
        controller.save()

        assert model.menuBarInstance != null
        assert view == '/SCMSMenuBar/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSMenuBar/show/1'
        assert controller.flash.message != null
        assert SCMSMenuBar.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuBar/list'

        populateValidParams(params)
        def SCMSMenuBar = new SCMSMenuBar(params)

        assert SCMSMenuBar.save() != null

        params.id = SCMSMenuBar.id

        def model = controller.show()

        assert model.menuBarInstance == SCMSMenuBar
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuBar/list'

        populateValidParams(params)
        def SCMSMenuBar = new SCMSMenuBar(params)

        assert SCMSMenuBar.save() != null

        params.id = SCMSMenuBar.id

        def model = controller.edit()

        assert model.menuBarInstance == SCMSMenuBar
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuBar/list'

        response.reset()

        populateValidParams(params)
        def SCMSMenuBar = new SCMSMenuBar(params)

        assert SCMSMenuBar.save() != null

        // test invalid parameters in update
        params.id = SCMSMenuBar.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSMenuBar/edit"
        assert model.menuBarInstance != null

        SCMSMenuBar.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSMenuBar/show/$SCMSMenuBar.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSMenuBar.clearErrors()

        populateValidParams(params)
        params.id = SCMSMenuBar.id
        params.version = -1
        controller.update()

        assert view == "/SCMSMenuBar/edit"
        assert model.menuBarInstance != null
        assert model.menuBarInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSMenuBar/list'

        response.reset()

        populateValidParams(params)
        def SCMSMenuBar = new SCMSMenuBar(params)

        assert SCMSMenuBar.save() != null
        assert SCMSMenuBar.count() == 1

        params.id = SCMSMenuBar.id

        controller.delete()

        assert SCMSMenuBar.count() == 0
        assert SCMSMenuBar.get(SCMSMenuBar.id) == null
        assert response.redirectedUrl == '/SCMSMenuBar/list'
    }
}
