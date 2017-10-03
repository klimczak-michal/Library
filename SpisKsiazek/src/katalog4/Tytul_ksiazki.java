package katalog4;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

public class Tytul_ksiazki implements Serializable {
    private String wydawnictwo;

    private String ISBN;

    private String tytul;

    private String nazwisko;

    private String imie;

    private ArrayList<Ksiazka> mKsiazka=new java.util.ArrayList();      

    public Tytul_ksiazki() {
    }

    public String getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(String e) {
        this.wydawnictwo = e;
    }

    public String getTytul() {
        return tytul;
    }

    public void setISBN(String ISBN_) {
        this.ISBN = ISBN_;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setTytul(String a) {
        this.tytul = a;
    }

    public String getImie() {
        return imie;
    }

    public void setNazwisko(String b) {
        this.nazwisko = b;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setImie(String c) {
        this.imie = c;
    }

    public String toString() {
      String pom="Tytul: "+getTytul();
      pom+=" Autor: "+getNazwisko() +" "+getImie();
      pom+=" ISBN: "+getISBN();
      pom+=" Wydawnictwo: "+getWydawnictwo()+"\n"; 
      return pom;  
    }

    public boolean equals(Object ob) {
       boolean a=ISBN.equals(((Tytul_ksiazki)ob).getISBN());
       //System.out.println(a);//linia tymczasowa
       return a; 
    }

    public ArrayList<Ksiazka> getKsiazka() {
        return mKsiazka;
    }
    
    public Ksiazka getKsiazke(int N)
    {
       return mKsiazka.get(N);
    }

    public void setKsiazka(ArrayList<Ksiazka> val) {
        mKsiazka = val;
    }

    public void dodaj_ksiazke(int numer_) {
     Ksiazka nowa= new Ksiazka();
     if (nowa != null)
      { nowa.setNumer(numer_);
        addKsiazka(nowa);}

    }

    public void addKsiazka(Ksiazka nowa) {
       if (!mKsiazka.contains(nowa)) 
       { mKsiazka.add(nowa);
         nowa.setTytul_ksiazki(this);} 
    }
 
    public void Usun_ksiazke(int numer)
    {   
      mKsiazka.remove(numer); //numer jest unikatowy wiec usunie pierwsze
      //i jedyne wystapienie numeru
    }  
    
    public boolean Szukaj_ISBN(String ISBN_)
    {
        
        return ISBN==ISBN_;
    }
    
    public boolean Szukaj_Ksiazki(int numer, int N)
    {
        return mKsiazka.get(numer).getNumer() == N;
    }
    
}
