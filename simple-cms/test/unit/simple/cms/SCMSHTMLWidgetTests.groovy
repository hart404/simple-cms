package simple.cms



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(SCMSHTMLWidget)
class SCMSHTMLWidgetTests {

    void testConstructor() {
       def widget = new SCMSHTMLWidget(htmlText: "Default html text", htmlId: "html1", cssClass: "htmlWidget")
	   assert widget.cssClass == "htmlWidget"
	   assert widget.htmlId == "html1"
	   assert widget.htmlText == "Default html text"
    }
}
