/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.oad.item;

import java.util.List;
import javax.persistence.Query;
import sistemarlf.modelo.entidades.item.Item;
import sistemarlf.modelo.entidades.pedido.Pedido;
import sistemarlf.modelo.entidades.produto.Produto;
import sistemarlf.modelo.oad.ImplIOAD;

/**
 *
 * @author Jhonnatan
 */
public class OADItem extends ImplIOAD<Item> {

    public OADItem() {
        super(Item.class);
    }
    public List<Item> ListaPeloPedido(Pedido p){
        List<Item> objetos = null;
        try{
            Query query = entityManager.createQuery(
                    "select i from Item i WHERE pedido = :p and situacao = '1'");
            query.setParameter("p", p);
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        

        return objetos;
    } 
    public List<Pedido> ListaPedidosComProduto(Produto p){
        List<Pedido> objetos = null;
        try{
            Query query = entityManager.createQuery(
                    "select i.pedido from Item i WHERE i.produto = :p order by i.pedido");
            query.setParameter("p", p);
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        

        return objetos;
    }     
    public List<Item> ListaPeloProduto(Produto p){
        List<Item> objetos = null;
        try{
            Query query = entityManager.createQuery(
                    "select i from Item i WHERE produto = :p and situacao = '1' order by i.tamanho, i.cor, i.pedido");
            query.setParameter("p", p);
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        

        return objetos;
    } 

}
