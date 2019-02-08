package metube.services;

import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;

import java.util.List;

public interface TubeService {

    <T extends Bindable> void saveTube(T model);

    <T extends Viewable> T findByName(String name, Class<T> clazz);

    <T extends Viewable> List<T> findAll(Class<T> clazz);
}
