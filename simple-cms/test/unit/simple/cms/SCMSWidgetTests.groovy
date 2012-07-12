package simple.cms



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(SCMSWidget)
class SCMSWidgetTests {

    void testSomething() {
       def widget = new SCMSWidget(htmlId: "html1", cssClass: "htmlWidget")
	   assert widget.cssClass == "htmlWidget"
	   assert widget.htmlId == "html1"
    }
}
