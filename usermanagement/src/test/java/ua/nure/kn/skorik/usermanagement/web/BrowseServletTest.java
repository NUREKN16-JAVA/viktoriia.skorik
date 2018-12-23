package ua.nure.kn.skorik.usermanagement.web;

import java.util.Collections;
import java.util.Date;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.skorik.usermanagement.User;

public class BrowseServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
    public void testBrowse() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }

}
