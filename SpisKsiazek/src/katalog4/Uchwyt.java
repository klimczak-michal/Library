/*
 * Uchwyt.java
 *
 * Created on 2 marzec 2007, 22:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package katalog4;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Uchwyt implements Serializable{
    private ArrayList<Tytul_ksiazki> mTytul_ksiazki = new ArrayList();

    public Uchwyt() {
    }

    public void dodaj_tytul(String a, String b, String c, String d, String e) {
        Tytul_ksiazki tytul_ksiazki=new Tytul_ksiazki();
        tytul_ksiazki.setTytul(a);
        tytul_ksiazki.setNazwisko(b);
        tytul_ksiazki.setImie(c);
        tytul_ksiazki.setISBN(d);
        tytul_ksiazki.setWydawnictwo(e);
        addTytul_ksiazki(tytul_ksiazki);
    }

    public ArrayList<Tytul_ksiazki> getTytul_ksiazki() {
        return mTytul_ksiazki;
    }

    public void setTytul_ksiazki(ArrayList<Tytul_ksiazki> val) {
        mTytul_ksiazki = val;
    }

    public void addTytul_ksiazki(Tytul_ksiazki tytul_ksiazki) {
        if (! mTytul_ksiazki.contains(tytul_ksiazki)) 
              mTytul_ksiazki.add(tytul_ksiazki); 
    }

    public void dodaj_ksiazke(String ISBN_, int numer_) {
      Tytul_ksiazki pom=new Tytul_ksiazki();
      pom.setISBN(ISBN_);
      int idx=mTytul_ksiazki.indexOf(pom);
      if (idx!=-1)
          { Tytul_ksiazki pom1=mTytul_ksiazki.get(idx);  
            pom1.dodaj_ksiazke(numer_);}
            //System.out.println(pom1.getKsiazka().toString()); }  linia tymczasowsa

    }
    
public Tytul_ksiazki Wyszukaj(String ISBN_)
    {
        Tytul_ksiazki Pom = null;
        for (int i=0; i<mTytul_ksiazki.size();i++)
        {
          if (mTytul_ksiazki.get(i).Szukaj_ISBN(ISBN_))  
          {return mTytul_ksiazki.get(i);}
        }
        return Pom;
    }
    
    public Ksiazka WyszukajKs(String ISBN_, int N)
    { 
        int i=-1;
        int pom = mTytul_ksiazki.indexOf(Wyszukaj(ISBN_));
        do{
        i++;
        if(mTytul_ksiazki.get(pom).Szukaj_Ksiazki(i, N));
        {return mTytul_ksiazki.get(pom).getKsiazke(i);}
        }while(true);
    }
    
public void Usun_tytul(String ISBN_)
    {          
        Tytul_ksiazki Pom = new Tytul_ksiazki();
        Pom.setISBN(ISBN_);
        mTytul_ksiazki.remove(Pom);  /*usuwa pierwszy napotkany (taki sam) obiekt, 
        ale ISBN jest unikatowy, wiec nie usunie zlego obiektu*/
    }
    
    public void Usun_ksiazke(String ISBN, int numer)
    {   
      Tytul_ksiazki pom= Wyszukaj(ISBN);//wyszukuje numer
      if(pom!=null) //jezeli znajdzie tytul o danym ISBN :
        pom.Usun_ksiazke(numer); //to usuwa ksiazke wg podanego numeru
    }
    
    public String toString()
    {
        Iterator itr = mTytul_ksiazki.iterator();
        String s = "";
        
        for (;itr.hasNext()==true;) {
        Object element = itr.next();
        s+=element.toString()+"\n";
        }
        return s;
    }
    
    public String toString2(int N)
    {
    String s=mTytul_ksiazki.get(N).toString(); 
    Iterator itr = mTytul_ksiazki.get(N).getKsiazka().iterator();   
        for (;itr.hasNext()==true;) {
        Object element = itr.next();
        s+=element.toString()+"\n";
        }
    return s;
    }
    /*
     public static void main(String t[]) // your code here 
     { Uchwyt ap=new Uchwyt();
       ap.dodaj_tytul("1","1","1","1","1");       
       ap.dodaj_tytul("2","2","2","2","2");       
       ap.dodaj_tytul("2","2","2","2","2");         
       String lan=ap.getTytul_ksiazki().toString();   
       System.out.println(lan);  
       
       ap.dodaj_ksiazke("1",1);       
       ap.dodaj_ksiazke("1",2);  
       ap.dodaj_ksiazke("1",2);        
       ap.dodaj_ksiazke("2",1);     
      }
}
*/
    
    public String Odczytaj(ObjectInputStream strumienobiektow) throws IOException, ClassNotFoundException 
    {
        mTytul_ksiazki.clear();
        Tytul_ksiazki pom;
        int liczba = ((Integer) strumienobiektow.readObject()).intValue();
        for (int i = 0; i < liczba; i++) {
        pom = (Tytul_ksiazki)strumienobiektow.readObject();
        mTytul_ksiazki.add(pom);
        }
    return toString(); //tworzy string z odczytanych i wpisanych danych do listy
    }

    public void Zapisz(ObjectOutputStream strumienobiektow) throws IOException {
       strumienobiektow.writeObject(mTytul_ksiazki.size());
        for (int i = 0; i < mTytul_ksiazki.size(); i++) {
            strumienobiektow.writeObject(mTytul_ksiazki.get(i));
        } //To change body of generated methods, choose Tools | Templates.
    }
}
