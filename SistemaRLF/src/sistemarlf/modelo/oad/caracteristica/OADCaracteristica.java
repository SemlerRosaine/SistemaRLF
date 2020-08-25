/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.oad.caracteristica;

import java.util.List;
import javax.persistence.Query;
import sistemarlf.modelo.entidades.caracteristica.Caracteristica;
import sistemarlf.modelo.oad.ImplIOAD;

/**
 *
 * @author Jhonnatan
 */
public class OADCaracteristica extends ImplIOAD<Caracteristica> {

 
    public OADCaracteristica() {
        super(Caracteristica.class);
    }
    
    public List<Caracteristica> ListaPeloPai(Integer codPai){
        List<Caracteristica> objetos = null;
        try{
            Query query = entityManager.createQuery(
                    "select c from Caracteristica c WHERE pai_codigo = :ppai_codigo");
            query.setParameter("ppai_codigo", codPai);
            objetos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
        return objetos;
    }
   
}
