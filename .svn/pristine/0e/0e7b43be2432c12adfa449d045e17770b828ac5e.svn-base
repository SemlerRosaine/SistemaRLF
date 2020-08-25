/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.oad.produto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import sistemarlf.modelo.entidades.caracteristica.Caracteristica;
import sistemarlf.modelo.entidades.produto.Produto;
import sistemarlf.modelo.oad.ImplIOAD;

/**
 *
 * @author Jhonnatan
 */
public class OADProduto extends ImplIOAD<Produto> {

    public OADProduto() {
        super(Produto.class);
    }
    public List<Produto> ListaPeloNome(String parmNome){
        List<Produto> objetos = null;
        try{
            Query query = entityManager.createQuery(
                    "select p from Produto p WHERE descricao like :pnome");
            query.setParameter("pnome", "%"+parmNome+"%");
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        

        return objetos;
    }     
    public Produto CarregaComCaracteristicas(Produto p){
        try{
            Query query = entityManager.createQuery(
                    "select p from Produto p join fetch p.caracteristicas where p.codigo = :pcodigo");
            query.setParameter("pcodigo", p.getCodigo());
            if (query.getResultList().size() > 0)
                p = (Produto) query.getSingleResult();
            else{
                p.setCaracteristicas(new ArrayList<Caracteristica>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
        return p;
    }

    public Produto CarregaPelaReferencia(Produto p){
        try{
            Query query = entityManager.createQuery(
                    "select p from Produto p where p.CodigoReferencia = :pcodigo");
            query.setParameter("pcodigo", p.getCodigoReferencia());
            if (query.getResultList().size() > 0)
                p = (Produto) query.getSingleResult();
            else{
                p = null;
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
        return p;
    }    
    public Integer ContaReferencia(Integer ref){
        Integer ret = 0;
        try{
            Query query = entityManager.createQuery(
                    "select p from Produto p where p.CodigoReferencia = :pcodigo");
            query.setParameter("pcodigo", ref);
            ret = query.getResultList().size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
        return ret;
    }        
    public List<Produto> VerificaReferenciasNulas(){
        List<Produto> ret = null;
        try{
            Query query = entityManager.createQuery(
                    "select p from Produto p where p.CodigoReferencia is null");
            ret = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        

        return ret;
    }   
    @Override
    public boolean validaExclusaoLogica(Produto objeto) {
        boolean ret;
        try{
            Query query = entityManager.createQuery(
                    "select i from Item i WHERE (i.ativo is NULL OR i.ativo=true) AND i.produto.codigo = :pcodprod");
            query.setParameter("pcodprod", objeto.getCodigo());
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
