package logica;

/**
 *
 * @author kei98
 */
public class Palindromo {
    private String fraseO;

    public Palindromo() {
    }
    

    public Palindromo(String fraseO) {
        this.fraseO = fraseO;
    }
    
    public boolean esPalindromo() {
        String frase = this.fraseO.replaceAll("\\s+", "");
        String fraseInv = "";
        
        for(int i = frase.length()-1; i > -1; i--){
            fraseInv += frase.charAt(i);
        }
        return frase.equalsIgnoreCase(fraseInv);
    }

    public String getFraseO() {
        return fraseO;
    }

    public void setFraseO(String fraseO) {
        this.fraseO = fraseO;
    }
    
}
