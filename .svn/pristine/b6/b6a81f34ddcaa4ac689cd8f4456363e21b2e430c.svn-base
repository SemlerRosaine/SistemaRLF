package sistemarlf.modelo.oad;

import java.lang.Class;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import sistemarlf.modelo.entidades.Registro;
import sistemarlf.modelo.fabrica.FabricaEM;

/**
 *
 * @author Jhonnatan Ricardo Semler <jrsemler@utfpr.edu.br>
 */
public abstract class ImplIOAD<K extends Registro> implements IOAD<K> {

    protected EntityTransaction entityTransaction;
    protected EntityManager entityManager;
    private Class<K> classType;

    public ImplIOAD(Class<K> classType) {
        this.entityManager = FabricaEM.obterAdministradorEntidade();
        this.classType = classType;
    }

    @Override
    public Boolean salvar(K objeto) {
        Boolean retorno = false;
        if (objeto.getCodigo() != null && objeto.getCodigo() > 0) {
            retorno = alterar(objeto);
        } else {
            retorno = incluir(objeto);
        }
        return retorno;
    }

    private Boolean incluir(K objeto) {
        Boolean retorno = false;
        objeto.setAtivo(true);
        try {

            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(objeto);
            entityTransaction.commit();
            retorno = true;
        } catch (Exception e) {
            JOptionPane j = new JOptionPane();
            JOptionPane.showMessageDialog(j, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            entityTransaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return retorno;
    }

    private Boolean alterar(K objeto) {
        Boolean retorno = false;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(objeto);
            entityTransaction.commit();
            retorno = true;
        } catch (Exception e) {
            JOptionPane j = new JOptionPane();
            JOptionPane.showMessageDialog(j, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            entityTransaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return retorno;
    }

    public Boolean remove(K objeto) {
        if (validaExclusaoLogica(objeto)) {
            objeto.setAtivo(false);
            return alterar(objeto);
        } else {
            return false;
        }
    }

    public Boolean removeFisico(K objeto) {
        Boolean retorno = false;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.remove(entityManager.getReference(objeto.getClass(), objeto.getCodigo()));
            entityTransaction.commit();
            retorno = true;
        } catch (Exception e) {
            JOptionPane j = new JOptionPane();
            JOptionPane.showMessageDialog(j, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            entityTransaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return retorno;
    }

    public K carrega(K objeto) {
        K retorno = null;

        try {
            retorno = entityManager.find(classType, objeto.getCodigo());
        } catch (Exception e) {
            JOptionPane j = new JOptionPane();
            JOptionPane.showMessageDialog(j, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return retorno;
    }

    public List<K> carregaTodos() {
        List<K> objetos = null;
        try {
            Query query = entityManager.createQuery(
                    "select b from " + classType.getCanonicalName() + " b WHERE b.ativo is null or b.ativo=true");
            objetos = query.getResultList();

        } catch (Exception e) {
            JOptionPane j = new JOptionPane();
            JOptionPane.showMessageDialog(j, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return objetos;

    }

    public boolean validaExclusaoLogica(K objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
