package simple.cms



import org.junit.*
import grails.test.mixin.*

@TestFor(PageTemplateController)
@Mock(SCMSPageTemplate)
class SCMSPageTemplateControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/SCMSPageTemplate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pageTemplateInstanceList.size() == 0
        assert model.pageTemplateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pageTemplateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pageTemplateInstance != null
        assert view == '/SCMSPageTemplate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSPageTemplate/show/1'
        assert controller.flash.message != null
        assert SCMSPageTemplate.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPageTemplate/list'

        populateValidParams(params)
        def SCMSPageTemplate = new SCMSPageTemplate(params)

        assert SCMSPageTemplate.save() != null

        params.id = SCMSPageTemplate.id

        def model = controller.show()

        assert model.pageTemplateInstance == SCMSPageTemplate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPageTemplate/list'

        populateValidParams(params)
        def SCMSPageTemplate = new SCMSPageTemplate(params)

        assert SCMSPageTemplate.save() != null

        params.id = SCMSPageTemplate.id

        def model = controller.edit()

        assert model.pageTemplateInstance == SCMSPageTemplate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPageTemplate/list'

        response.reset()

        populateValidParams(params)
        def SCMSPageTemplate = new SCMSPageTemplate(params)

        assert SCMSPageTemplate.save() != null

        // test invalid parameters in update
        params.id = SCMSPageTemplate.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSPageTemplate/edit"
        assert model.pageTemplateInstance != null

        SCMSPageTemplate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSPageTemplate/show/$SCMSPageTemplate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSPageTemplate.clearErrors()

        populateValidParams(params)
        params.id = SCMSPageTemplate.id
        params.version = -1
        controller.update()

        assert view == "/SCMSPageTemplate/edit"
        assert model.pageTemplateInstance != null
        assert model.pageTemplateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPageTemplate/list'

        response.reset()

        populateValidParams(params)
        def SCMSPageTemplate = new SCMSPageTemplate(params)

        assert SCMSPageTemplate.save() != null
        assert SCMSPageTemplate.count() == 1

        params.id = SCMSPageTemplate.id

        controller.delete()

        assert SCMSPageTemplate.count() == 0
        assert SCMSPageTemplate.get(SCMSPageTemplate.id) == null
        assert response.redirectedUrl == '/SCMSPageTemplate/list'
    }
}
