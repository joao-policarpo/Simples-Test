package repositories;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import models.Contatos;
import play.db.jpa.JPAApi;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Singleton
public class ContatoRepository {

    private final JPAApi jpaApi;

    @Inject
    public ContatoRepository(JPAApi jpaApi) {this.jpaApi = jpaApi;
    }

    public List<Contatos> findAll() {
        return jpaApi.withTransaction((Function<EntityManager, List<Contatos>>) em ->
                em.createQuery("SELECT c FROM Contatos c", Contatos.class).getResultList()
        );
    }

    public Optional<Contatos> findById(Long id) {
        return jpaApi.withTransaction((Function<EntityManager, Optional<Contatos>>) em ->
                Optional.ofNullable(em.find(Contatos.class, id))
        );
    }

    public Contatos save(Contatos contato) {
        return jpaApi.withTransaction((Function<EntityManager, Contatos>) em -> {
            em.persist(contato);
            return contato;
        });
    }

    public Contatos update(Contatos contato) {
        return jpaApi.withTransaction((Function<EntityManager, Contatos>) em ->
                em.merge(contato)
        );
    }

    public void delete(Contatos contato) {
        jpaApi.withTransaction((Consumer<EntityManager>) em -> {
            Contatos merged = em.merge(contato);
            em.remove(merged);
        });
    }
}
