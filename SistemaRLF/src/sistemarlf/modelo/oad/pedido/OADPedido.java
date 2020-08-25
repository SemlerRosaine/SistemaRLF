/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.oad.pedido;

import java.util.List;
import javax.persistence.Query;
import sistemarlf.modelo.entidades.item.Item;
import sistemarlf.modelo.entidades.pedido.Pedido;
import sistemarlf.modelo.oad.ImplIOAD;

/**
 *
 * @author Jhonnatan
 */
public class OADPedido extends ImplIOAD<Pedido> {

    public OADPedido() {
        super(Pedido.class);
    }
    public List<Item> getItensPedido(Pedido ped){
        List<Item> objetos = null;
        try{
            Query query = entityManager.createQuery(
                    "select i from Item i WHERE i.pedido.codigo = :pCodPed");
            query.setParameter("pCodPed", ped.getCodigo());
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        

        return objetos;
    }   

}
