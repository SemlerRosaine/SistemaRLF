/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Rosaine
 */
public class Formato {  
      
    public static JFormattedTextField getFormatado(String formato) {  
        JFormattedTextField campoFormatado = null;  
        MaskFormatter format = new MaskFormatter();  
             
        format.setPlaceholderCharacter('_');  
        format.setValueContainsLiteralCharacters(false);  
          
        try {  
            format.setMask(formato);  
            campoFormatado = new JFormattedTextField(format);  
        } catch (ParseException ex) {  
            ex.printStackTrace();  
        }  
        return campoFormatado;  
    }  
  
    public static JFormattedTextField getTelefone() {  
        return getFormatado("(##) ####-####");  
    }  
      
    public static JFormattedTextField getCNPJ() {  
        return getFormatado("##.###.###/####-##");  
    }  

    public static JFormattedTextField getMoeda() {  
        return getFormatado("R$ #.###.###.##0,00");  
    }  
}  