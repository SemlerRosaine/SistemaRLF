/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.oad.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import sistemarlf.modelo.entidades.cliente.Cliente;
import sistemarlf.modelo.oad.ImplIOAD;

/**
 *
 * @author Jhonnatan
 */
public class OADCliente extends ImplIOAD<Cliente> {

    public OADCliente() {
        super(Cliente.class);
    }

    public Cliente buscaClientePorCPF(String cpfCnpj) {
        return this.buscaClientePorCPFCNPJ(cpfCnpj, true);
    }

    public Cliente buscaClientePorCPFCNPJ(String cpfCnpj, Boolean ativo) {
        List<Cliente> objetos = null;
        try {
            Query query = entityManager.createQuery(
                    "select c from Cliente c WHERE c.cpfCnpj LIKE :cpf_cnpj AND c.ativo = :ativo ORDER BY c.codigo DESC");
            query.setParameter("cpf_cnpj", cpfCnpj);
            query.setParameter("ativo", ativo);

            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (objetos.size() > 0) ? objetos.get(0) : null;
    }

    public List<Cliente> listaAtivos(Integer codigo, Boolean ativo) {
        List<Cliente> objetos = new ArrayList<Cliente>();
        try {
            Query query = entityManager.createQuery(
                    "select c from Cliente c WHERE codigo = :codigo AND ativo=:ativo");
            query.setParameter("codigo", codigo);
            query.setParameter("ativo", ativo);
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objetos;
    }

    public List<Cliente> listaPeloNome(String nome, Boolean ativo) {
        List<Cliente> objetos = null;
        try {
            Query query = entityManager.createQuery(
                    "select c from Cliente c WHERE nomeRazaoSocial like :pnome AND ativo=:ativo");
            query.setParameter("pnome", "%" + nome + "%");
            query.setParameter("ativo", ativo);
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return objetos;
    }

    public List<Cliente> listaPeloNome(String parmNome) {
        return listaPeloNome(parmNome, true);
    }

    @Override
    public boolean validaExclusaoLogica(Cliente objeto) {
        boolean ret;
        try {
            Query query = entityManager.createQuery(
                    "select p from Pedido p WHERE (p.ativo is NULL OR p.ativo=true)  AND p.cliente.codigo = :pcodclie");
            query.setParameter("pcodclie", objeto.getCodigo());
            ret = query.getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
//        } finally {
//            entityManager.close();
        }
        return ret;
    }
}
