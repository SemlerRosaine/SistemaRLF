/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.oad.ordem;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import sistemarlf.modelo.entidades.caracteristica.Caracteristica;
import sistemarlf.modelo.entidades.ordem.OrdemProducao;
import sistemarlf.modelo.entidades.produto.Produto;
import sistemarlf.modelo.oad.ImplIOAD;

/**
 *
 * @author Jhonnatan
 */
public class OADOrdemProducao extends ImplIOAD<OrdemProducao> {

    public OADOrdemProducao() {
        super(OrdemProducao.class);
    }
    public List<OrdemProducao> CarregaTodosComItens(){
        List<OrdemProducao> ret = null;
        try{
            Query query = entityManager.createQuery(
                    "select o from OrdemProducao o");
            if (query.getResultList().size() > 0){
                ret = (List<OrdemProducao>) query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    //    finally {
     //       entityManager.close();
     //   }        
        return ret;
    }
    public OrdemProducao CarregaComItens(OrdemProducao o){
        OrdemProducao ret = null;
        try{
            Query query = entityManager.createQuery(
                    "select o from OrdemProducao o JOIN FETCH o.itens  WHERE o.codigo = :p GROUP BY o");
            query.setParameter("p", o.getCodigo());
            if (query.getResultList().size() > 0){
                ret = (OrdemProducao) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
        return ret;
    }    
}
