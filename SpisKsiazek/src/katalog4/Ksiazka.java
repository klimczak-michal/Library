package katalog4;
import katalog4.Tytul_ksiazki;
import java.io.Serializable;

public class Ksiazka implements Serializable{
    private int numer;

    private Tytul_ksiazki mTytul_ksiazki;

    public Ksiazka() {
    }

    protected void finalize() {
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer_) {
        this.numer = numer_;
    }

    public boolean equals(Object ob) {
         return numer==((Ksiazka)ob).getNumer();    
    }

    public Tytul_ksiazki getTytul_ksiazki() {
        return mTytul_ksiazki;
    }

    public void setTytul_ksiazki(Tytul_ksiazki val) {
        this.mTytul_ksiazki = val;
    }

  public String toString() 			// your code here
  { 
    String pom = mTytul_ksiazki.toString();  
    pom+=" Numer: "+getNumer(); 
    return pom;  } 
 }

