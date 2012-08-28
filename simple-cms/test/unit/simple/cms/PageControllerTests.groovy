package simple.cms



import org.junit.*
import grails.test.mixin.*

@TestFor(PageController)
@Mock(SCMSPage)
class PageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/SCMSPage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pageInstanceList.size() == 0
        assert model.pageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pageInstance != null
        assert view == '/SCMSPage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSPage/show/1'
        assert controller.flash.message != null
        assert SCMSPage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPage/list'

        populateValidParams(params)
        def SCMSPage = new SCMSPage(params)

        assert SCMSPage.save() != null

        params.id = SCMSPage.id

        def model = controller.show()

        assert model.pageInstance == SCMSPage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPage/list'

        populateValidParams(params)
        def SCMSPage = new SCMSPage(params)

        assert SCMSPage.save() != null

        params.id = SCMSPage.id

        def model = controller.edit()

        assert model.pageInstance == SCMSPage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPage/list'

        response.reset()

        populateValidParams(params)
        def SCMSPage = new SCMSPage(params)

        assert SCMSPage.save() != null

        // test invalid parameters in update
        params.id = SCMSPage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSPage/edit"
        assert model.pageInstance != null

        SCMSPage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSPage/show/$SCMSPage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSPage.clearErrors()

        populateValidParams(params)
        params.id = SCMSPage.id
        params.version = -1
        controller.update()

        assert view == "/SCMSPage/edit"
        assert model.pageInstance != null
        assert model.pageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPage/list'

        response.reset()

        populateValidParams(params)
        def SCMSPage = new SCMSPage(params)

        assert SCMSPage.save() != null
        assert SCMSPage.count() == 1

        params.id = SCMSPage.id

        controller.delete()

        assert SCMSPage.count() == 0
        assert SCMSPage.get(SCMSPage.id) == null
        assert response.redirectedUrl == '/SCMSPage/list'
    }
}
