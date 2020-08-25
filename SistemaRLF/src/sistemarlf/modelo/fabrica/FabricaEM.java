package sistemarlf.modelo.fabrica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaEM {

    public static EntityManagerFactory instancia;

    private FabricaEM() {
    }

    public static EntityManager obterAdministradorEntidade() {
        if (instancia == null) {
            instancia = Persistence.createEntityManagerFactory("SistemaRLFPU");
        }

        return instancia.createEntityManager();
    }
}