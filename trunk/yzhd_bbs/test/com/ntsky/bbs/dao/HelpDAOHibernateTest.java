package com.ntsky.bbs.dao;

import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.util.page.Pagination;

public class HelpDAOHibernateTest extends BaseDAOCase {
    private Help help = null;
    private HelpDAO dao = null;

    public void setHelpDAO(HelpDAO dao) {
        this.dao = dao;
    }

    public void testFind(){
		assertNotNull(dao.find("from Help"));
    }
    
    public void testFindHelps(){
    	dao.findHelps("",new Pagination(0,20));
    }
    
    /*public void testGetRoleInvalid() throws Exception {
        role = dao.getRole("badrolename");
        assertNull(role);
    }

    public void testGetRole() throws Exception {
        role = dao.getRole(Constants.USER_ROLE);
        assertNotNull(role);
    }

    public void testUpdateRole() throws Exception {
        role = dao.getRole("user");
        log.info(role);
        role.setDescription("test descr");

        dao.saveRole(role);
        assertEquals(role.getDescription(), "test descr");
    }

    public void testAddAndRemoveRole() throws Exception {
        role = new Role("testrole");
        role.setDescription("new role descr");

        dao.saveRole(role);
        assertNotNull(role.getName());

        dao.removeRole("testrole");

        endTransaction();

        role = dao.getRole("testrole");
        assertNull(role);
    }*/
}
