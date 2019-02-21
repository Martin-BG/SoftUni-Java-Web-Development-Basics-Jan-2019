package fdmcjsf.repositories;

import fdmcjsf.domain.entities.Cat;

import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class CatRepositoryImpl extends BaseCrudRepository<Cat, String> implements CatRepository {

    private static final Logger LOG = Logger.getLogger(CatRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }
}
