package hh.view.data;

import hh.dao.PersonDao;
import hh.entity.Person;
import hh.entity.Pet;
import hh.util.Helper;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyPersonDataModel extends LazyDataModel<Person> {

    private static final long serialVersionUID = 842828358093359191L;

    private PersonDao personDao;
    private List<Person> persons;

    @Override
    public List<Person> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        personDao = Helper.findBean(PersonDao.class);

        if (sortField == null && filters.isEmpty())
            persons = personDao.getByRange(first, pageSize);
        else
            persons = personDao.getByRangeWithFilter(pageSize, filters);

        // set the total of players
        //if (getRowCount() <= 0) {
            setRowCount(personDao.getByRangeWithFilterCnt(filters).intValue());
        //}

        // set the page size
        setPageSize(pageSize);
        
        // Filter the pets
        for (Person p : persons) {
            for (Pet pp : p.getPets()) {
                if (pp.getId().getPetName().equals("Penny"))
                    p.getPets().remove(pp);
            }
        }

        return persons;
    }

    @Override
    public Object getRowKey(Person person) {
        return person.getId();
    }

    @Override
    public Person getRowData(String personId) {
        Integer id = Integer.valueOf(personId);

        for (Person p : persons) {
            if (id.equals(p.getId())) {
                return p;
            }
        }

        return null;
    }

}
