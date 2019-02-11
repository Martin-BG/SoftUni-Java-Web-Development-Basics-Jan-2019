package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;

import java.util.Optional;

public interface TubeService extends Service<Tube, String> {

    <MODEL extends Bindable<Tube>> boolean upload(MODEL model);

    <MODEL extends Viewable<Tube>> Optional<MODEL> findByName(String name, Class<MODEL> clazz);
}
