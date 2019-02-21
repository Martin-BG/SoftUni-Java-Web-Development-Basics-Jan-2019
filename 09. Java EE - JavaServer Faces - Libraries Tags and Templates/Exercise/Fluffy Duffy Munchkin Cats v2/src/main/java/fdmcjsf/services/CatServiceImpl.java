package fdmcjsf.services;

import fdmcjsf.domain.entities.Cat;
import fdmcjsf.domain.models.binding.CatBindingModel;
import fdmcjsf.repositories.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.logging.Logger;

public class CatServiceImpl extends BaseService<Cat, String, CatRepository> implements CatService {

    private static final Logger LOG = Logger.getLogger(CatServiceImpl.class.getName());

    @Inject
    protected CatServiceImpl(ModelMapper mapper,
                             Validator validator,
                             CatRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public boolean createCat(CatBindingModel model) {
        if (model == null) {
            return false;
        }

        return create(model);
    }
}
