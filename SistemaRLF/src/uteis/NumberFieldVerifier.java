/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import javax.swing.JTextField;  
import java.text.DecimalFormat;  
import java.text.DecimalFormatSymbols;  
import java.util.Locale;  
public class NumberFieldVerifier extends javax.swing.InputVerifier implements java.awt.event.ActionListener {  
    private boolean allowzero;  
    static DecimalFormat df;  
    public NumberFieldVerifier(boolean allowzero){  
        this.allowzero=allowzero;  
        df=new DecimalFormat("#,##0.00",new DecimalFormatSymbols(new Locale("pt","BR")) );  
    }  
      
    public boolean shouldYieldFocus(javax.swing.JComponent input) {  
        ((JTextField) input).setText(((JTextField) input).getText().replace("R$ ", ""));            
        if(verify(input)){  
            return true;  
        }else{  
            //java.awt.Toolkit.getDefaultToolkit().beep();  
             javax.swing.JOptionPane.showMessageDialog(null, "valor inválido");  
            return false;  
        }  
    }  
      
    public boolean verify(javax.swing.JComponent input) {  
        Double valor;  

        try{  
            valor=  df.parse( ((JTextField) input).getText() ).doubleValue();  
            ((JTextField) input).setText(java.text.NumberFormat.getCurrencyInstance().format(valor));
        }catch (java.text.ParseException pe){return false;}  
        if(! allowzero){  //ñ permite zero  
            if( valor==0){return false;}  
        }  
        return checkField(valor);  
    }  
    public boolean checkField(Double valor){  
        boolean valid;  
        if(valor>0){formata();valid=true;}else{valid=false;}  
        return valid;  
    }  
    public void formata(){
     
    }  


    public static String SetValorFormatado(Double valor) {  
        String ret;
        ret = java.text.NumberFormat.getCurrencyInstance().format(valor);
        return ret;  
    }      
    public static Double GetValorFormatado(String input) {
        
        Double retorno = 0.0;
        String valorComVirgula = input.replace("R$ ", "");   
        try{
             retorno = df.parse( valorComVirgula).doubleValue();
        }finally {  
             return retorno;  
        }  
    }
      
    public void actionPerformed(java.awt.event.ActionEvent e) {  
        JTextField campo= (JTextField) e.getSource();  
        shouldYieldFocus(campo);  
        campo.selectAll();  
    }  
     public void CampoDinheiroFocusGained(java.awt.event.FocusEvent evt) {                                               
        JTextField t = (JTextField)evt.getSource();
        t.setText(t.getText().replace("R$ ", ""));      
        t.selectAll();
        // TODO add your handling code here:
    }        
}