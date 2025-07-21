package repositories;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import models.Profissional;
import play.db.jpa.JPAApi;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import jakarta.persistence.EntityManager;

@Singleton
public class ProfissionalRepository {

    private final JPAApi jpaApi;

    @Inject
    public ProfissionalRepository(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    public List<Profissional> findAll() {
        return jpaApi.withTransaction((Function<EntityManager, List<Profissional>>) em ->
                em.createQuery("SELECT p FROM Profissional p", Profissional.class)
                        .getResultList()
        );
    }

    public Optional<Profissional> findById(Long id) {
        return jpaApi.withTransaction((Function<EntityManager, Optional<Profissional>>) em ->
                Optional.ofNullable(em.find(Profissional.class, id))
        );
    }

    public Profissional save(Profissional profissional) {
        return jpaApi.withTransaction((Function<EntityManager, Profissional>) em -> {
            em.persist(profissional);
            return profissional;
        });
    }

    public Profissional update(Profissional profissional) {
        return jpaApi.withTransaction((Function<EntityManager, Profissional>) em ->
                em.merge(profissional)
        );
    }

    public void delete(Profissional profissional) {
        jpaApi.withTransaction((Consumer<EntityManager>) em -> {
            Profissional merged = em.merge(profissional);
            em.remove(merged);
        });
    }
}
