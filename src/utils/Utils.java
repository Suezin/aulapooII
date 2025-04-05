package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Utils {

    public static String calcularHash(String senha) {
        String hashSHA1 = "";
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            sha1.update(senha.getBytes());
            byte[] digest = sha1.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));

            }
            hashSHA1 = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritimo SHA1 não encontrado ! ");

        }
        return hashSHA1;
    }
    
    public static Date converterStringToDate(String texto){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null ;
        
        try {
            data = formato.parse(texto);
            
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(null , "Erro ao converter a data ");
        }
     return data;   
    }
    
    public static String converterDateToString(Date data){
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         String texto = "";
        try {
            texto = formato.format(data);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null , "Erro ao formatar a data ");
        }
     return texto;  
    }
}