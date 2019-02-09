package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;

import java.util.List;

public interface TubeService {

    <T extends Bindable<Tube>> void saveTube(T model);

    <T extends Viewable<Tube>> T findByName(String name, Class<T> clazz);

    <T extends Viewable<Tube>> List<T> findAll(Class<T> clazz);
}
