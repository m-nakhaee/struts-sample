package controller.action;

import model.dao.PersonDao;
import model.entity.Person;
import controller.form.PersonForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class PersonAction extends Action {
    private PersonDao personDao;

    public PersonAction() {
        personDao = new PersonDao();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("resultPersons", null);
        Person person = getPersonInformation((PersonForm) form);
        List<Person> resultPersons = personDao.search(person);
        request.getSession().setAttribute("resultPersons", resultPersons);
        return mapping.findForward("success");
    }

    private Person getPersonInformation(PersonForm form) {
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }
}
