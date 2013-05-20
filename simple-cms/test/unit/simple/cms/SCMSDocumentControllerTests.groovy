package simple.cms



import grails.test.mixin.*

import org.junit.*

@TestFor(DocumentController)
@Mock(SCMSDocument)
class SCMSDocumentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/document/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.SCMSDocumentInstanceList.size() == 0
        assert model.SCMSDocumentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.SCMSDocumentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.SCMSDocumentInstance != null
        assert view == '/SCMSDocument/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSDocument/show/1'
        assert controller.flash.message != null
        assert SCMSDocument.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSDocument/list'

        populateValidParams(params)
        def SCMSDocument = new SCMSDocument(params)

        assert SCMSDocument.save() != null

        params.id = SCMSDocument.id

        def model = controller.show()

        assert model.SCMSDocumentInstance == SCMSDocument
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSDocument/list'

        populateValidParams(params)
        def SCMSDocument = new SCMSDocument(params)

        assert SCMSDocument.save() != null

        params.id = SCMSDocument.id

        def model = controller.edit()

        assert model.SCMSDocumentInstance == SCMSDocument
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSDocument/list'

        response.reset()

        populateValidParams(params)
        def SCMSDocument = new SCMSDocument(params)

        assert SCMSDocument.save() != null

        // test invalid parameters in update
        params.id = SCMSDocument.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSDocument/edit"
        assert model.SCMSDocumentInstance != null

        SCMSDocument.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSDocument/show/$SCMSDocument.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSDocument.clearErrors()

        populateValidParams(params)
        params.id = SCMSDocument.id
        params.version = -1
        controller.update()

        assert view == "/SCMSDocument/edit"
        assert model.SCMSDocumentInstance != null
        assert model.SCMSDocumentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSDocument/list'

        response.reset()

        populateValidParams(params)
        def SCMSDocument = new SCMSDocument(params)

        assert SCMSDocument.save() != null
        assert SCMSDocument.count() == 1

        params.id = SCMSDocument.id

        controller.delete()

        assert SCMSDocument.count() == 0
        assert SCMSDocument.get(SCMSDocument.id) == null
        assert response.redirectedUrl == '/SCMSDocument/list'
    }
}
