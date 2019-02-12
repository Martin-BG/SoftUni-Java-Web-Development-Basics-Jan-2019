package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;

import java.util.Optional;

public interface TubeService extends Service<Tube, String> {

    <M extends Bindable<Tube>> boolean upload(M model);

    <M extends Viewable<Tube>> Optional<M> findByName(String name, Class<M> clazz);

    <M extends Viewable<Tube>> Optional<M> view(String id, Class<M> clazz);
}
