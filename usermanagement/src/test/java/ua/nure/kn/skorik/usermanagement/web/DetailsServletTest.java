package ua.nure.kn.skorik.usermanagement.web;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.skorik.usermanagement.User;

public class DetailsServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
        createServlet(DetailsServlet.class);
	}

	@Test
	public void testOkButton() {
        addRequestParameter("okButton", "Ok");
        User nullUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNull("User must not exist in session scope", nullUser);
    }
}
