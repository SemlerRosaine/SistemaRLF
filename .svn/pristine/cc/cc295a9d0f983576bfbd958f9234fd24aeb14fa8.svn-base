/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prototipo;

/**
 *
 * @author Wine
 */
class ProtClassProdtPedido {
    private int CodProdtPedido;
    private String DescProdtPedido;
    private String TamProdtPedido;
    private String CorProdtPedido;
    private double QtdeProdtPedido;
    private double VlrUnitProdtPedido;
    private double VlrTotalProdtPedido;
    
    public int getCodProdtPedido() {
        return CodProdtPedido;
    }

    public void setCodProdtPedido(int CodProdtPedido) {
        this.CodProdtPedido = CodProdtPedido;
    }

    public String getDescProdtPedido() {
        return DescProdtPedido;
    }

    public void setDescProdtPedido(String DescProdtPedido) {
        this.DescProdtPedido = DescProdtPedido;
    }

    public String getTamProdtPedido() {
        return TamProdtPedido;
    }

    public void setTamProdtPedido(String TamProdtPedido) {
        this.TamProdtPedido = TamProdtPedido;
    }

    public String getCorProdtPedido() {
        return CorProdtPedido;
    }

    public void setCorProdtPedido(String CorProdtPedido) {
        this.CorProdtPedido = CorProdtPedido;
    }

    public double getQtdeProdtPedido() {
        return QtdeProdtPedido;
    }

    public void setQtdeProdtPedido(double QtdeProdtPedido) {
        this.QtdeProdtPedido = QtdeProdtPedido;
        setVlrTotalProdtPedido();
    }

    public double getVlrUnitProdtPedido() {
        return VlrUnitProdtPedido;
    }

    public void setVlrUnitProdtPedido(double VlrUnitProdtPedido) {
        this.VlrUnitProdtPedido = VlrUnitProdtPedido;
        setVlrTotalProdtPedido();
    }

    public double getVlrTotalProdtPedido() {
        return VlrTotalProdtPedido;
    }

    private void setVlrTotalProdtPedido() {
        if (VlrUnitProdtPedido > 0 && QtdeProdtPedido > 0){
            VlrTotalProdtPedido = VlrUnitProdtPedido * QtdeProdtPedido;
        }
            
    }

    public Object[] getLinha() {
        Object linha[] = {getCodProdtPedido(), getDescProdtPedido(),getTamProdtPedido(),getCorProdtPedido(),getQtdeProdtPedido(),getVlrUnitProdtPedido(), getVlrTotalProdtPedido()};
        
        return linha;
    }
    

         
    
}
