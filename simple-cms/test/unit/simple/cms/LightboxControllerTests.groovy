package simple.cms



import org.junit.*
import grails.test.mixin.*

@TestFor(LightboxController)
@Mock(SCMSLightboxWidget)
class LightboxControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/SCMSLightboxWidget/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.SCMSLightboxWidgetInstanceList.size() == 0
        assert model.SCMSLightboxWidgetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.SCMSLightboxWidgetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.SCMSLightboxWidgetInstance != null
        assert view == '/SCMSLightboxWidget/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSLightboxWidget/show/1'
        assert controller.flash.message != null
        assert SCMSLightboxWidget.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSLightboxWidget/list'

        populateValidParams(params)
        def SCMSLightboxWidget = new SCMSLightboxWidget(params)

        assert SCMSLightboxWidget.save() != null

        params.id = SCMSLightboxWidget.id

        def model = controller.show()

        assert model.SCMSLightboxWidgetInstance == SCMSLightboxWidget
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSLightboxWidget/list'

        populateValidParams(params)
        def SCMSLightboxWidget = new SCMSLightboxWidget(params)

        assert SCMSLightboxWidget.save() != null

        params.id = SCMSLightboxWidget.id

        def model = controller.edit()

        assert model.SCMSLightboxWidgetInstance == SCMSLightboxWidget
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSLightboxWidget/list'

        response.reset()

        populateValidParams(params)
        def SCMSLightboxWidget = new SCMSLightboxWidget(params)

        assert SCMSLightboxWidget.save() != null

        // test invalid parameters in update
        params.id = SCMSLightboxWidget.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSLightboxWidget/edit"
        assert model.SCMSLightboxWidgetInstance != null

        SCMSLightboxWidget.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSLightboxWidget/show/$SCMSLightboxWidget.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSLightboxWidget.clearErrors()

        populateValidParams(params)
        params.id = SCMSLightboxWidget.id
        params.version = -1
        controller.update()

        assert view == "/SCMSLightboxWidget/edit"
        assert model.SCMSLightboxWidgetInstance != null
        assert model.SCMSLightboxWidgetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSLightboxWidget/list'

        response.reset()

        populateValidParams(params)
        def SCMSLightboxWidget = new SCMSLightboxWidget(params)

        assert SCMSLightboxWidget.save() != null
        assert SCMSLightboxWidget.count() == 1

        params.id = SCMSLightboxWidget.id

        controller.delete()

        assert SCMSLightboxWidget.count() == 0
        assert SCMSLightboxWidget.get(SCMSLightboxWidget.id) == null
        assert response.redirectedUrl == '/SCMSLightboxWidget/list'
    }
}
