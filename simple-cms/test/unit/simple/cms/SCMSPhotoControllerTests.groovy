package simple.cms



import org.junit.*
import grails.test.mixin.*

@TestFor(PhotoController)
@Mock(SCMSPhoto)
class SCMSPhotoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/SCMSPhoto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.photoInstanceList.size() == 0
        assert model.photoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.photoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.photoInstance != null
        assert view == '/SCMSPhoto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/SCMSPhoto/show/1'
        assert controller.flash.message != null
        assert SCMSPhoto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPhoto/list'

        populateValidParams(params)
        def SCMSPhoto = new SCMSPhoto(params)

        assert SCMSPhoto.save() != null

        params.id = SCMSPhoto.id

        def model = controller.show()

        assert model.photoInstance == SCMSPhoto
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPhoto/list'

        populateValidParams(params)
        def SCMSPhoto = new SCMSPhoto(params)

        assert SCMSPhoto.save() != null

        params.id = SCMSPhoto.id

        def model = controller.edit()

        assert model.photoInstance == SCMSPhoto
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPhoto/list'

        response.reset()

        populateValidParams(params)
        def SCMSPhoto = new SCMSPhoto(params)

        assert SCMSPhoto.save() != null

        // test invalid parameters in update
        params.id = SCMSPhoto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/SCMSPhoto/edit"
        assert model.photoInstance != null

        SCMSPhoto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/SCMSPhoto/show/$SCMSPhoto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        SCMSPhoto.clearErrors()

        populateValidParams(params)
        params.id = SCMSPhoto.id
        params.version = -1
        controller.update()

        assert view == "/SCMSPhoto/edit"
        assert model.photoInstance != null
        assert model.photoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/SCMSPhoto/list'

        response.reset()

        populateValidParams(params)
        def SCMSPhoto = new SCMSPhoto(params)

        assert SCMSPhoto.save() != null
        assert SCMSPhoto.count() == 1

        params.id = SCMSPhoto.id

        controller.delete()

        assert SCMSPhoto.count() == 0
        assert SCMSPhoto.get(SCMSPhoto.id) == null
        assert response.redirectedUrl == '/SCMSPhoto/list'
    }
}
