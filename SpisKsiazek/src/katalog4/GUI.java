package katalog4;

import java.io.FileInputStream;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import katalog4.Uchwyt;

public class GUI {
    
Uchwyt Uchwyt = new Uchwyt();

//wstawianie tyulow
public void Wstaw_tytul()
{
    String S1, S2, S3, S4, S5;
    S1 = JOptionPane.showInputDialog(null, "Podaj tytul");
    S2 = JOptionPane.showInputDialog(null, "Podaj nazwisko autora");
    S3 = JOptionPane.showInputDialog(null, "Podaj imie autora");
    S4 = JOptionPane.showInputDialog(null, "Podaj numer ISBN");
    S5 = JOptionPane.showInputDialog(null, "Podaj wydawnictwo");
    Uchwyt.dodaj_tytul(S1,S2,S3,S4,S5);//dopisanie danych na końcu danych
}
//wstawianie ksiazek
public void Wstaw_ksiazke()
{
  String S1, S2;
  S1 = JOptionPane.showInputDialog(null, "Podaj numer ISBN tytulu, do ktorego chcesz dodac ksiazke");
  S2 = JOptionPane.showInputDialog(null, "Podaj numer ksiazki");
  int numer = Integer.parseInt(S2);
  Uchwyt.dodaj_ksiazke(S1, numer);
}

//wyszukiwanie wg tytulu
public void Wyszukaj_tytul()
{
    String S1;
    S1 = JOptionPane.showInputDialog(null, "Podaj numer ISBN tytulu, ktorego chcesz znalezc");
    Object tytul = Uchwyt.Wyszukaj(S1);
            if (tytul != null) {
                JOptionPane.showMessageDialog(null, tytul.toString());}
}


//usuwanie tytulow
public void Usun_tytul()
{
   String S1;
   S1 = JOptionPane.showInputDialog(null, "Podaj numer ISBN tytulu, ktorego chcesz usunac"
           + "\nUsuniecie tytulu oznacza usuniecie dodanych ksiazek do tego tytulu!");
   Uchwyt.Usun_tytul(S1);
}

//usuwanie ksiazek
public void Usun_ksiazke()
{
       String S1, S2;
   S1 = JOptionPane.showInputDialog(null, "Podaj ISBN ksiazki ktora chcesz usunac");
   S2 = JOptionPane.showInputDialog(null, "Podaj numer ksiazki ktora chcesz usunac");
   int N = Integer.parseInt(S2);
   
   Uchwyt.Usun_ksiazke(S1, N);
}

//wyswietlanie tytulow
public void Wyswietl_tytuly()
{
    JOptionPane.showMessageDialog(null, Uchwyt.toString());
}

//wyswietlanie ksiazek danego tytulu
public void Wyswietl_ksiazki()
{
  String S1;  
  S1 = JOptionPane.showInputDialog(null, "Podaj ISBN by wyswitelic wszystkie ksiazki nalezace do niego");
  int N = Uchwyt.getTytul_ksiazki().indexOf(Uchwyt.Wyszukaj(S1));
  
  JOptionPane.showMessageDialog(null, Uchwyt.toString2(N));  
}

//zapis danych do plikow: jeden dla tyutlow, drugi dla ksiazek
public void Zapisz()
{
            try {
                FileOutputStream plik = new FileOutputStream("Ksiazki.obj");
                ObjectOutputStream strumienobiektow = new ObjectOutputStream(plik);
                Uchwyt.Zapisz(strumienobiektow);
                strumienobiektow.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Blad zapisu pliku obiektow");
            }
        }
    

//odczyt i wyrzucenie na ekran
public void Odczytaj()
{
        try {
            FileInputStream plik = new FileInputStream("Ksiazki.obj");
            ObjectInputStream strumienobiektow = new ObjectInputStream(plik);
            String s=Uchwyt.Odczytaj(strumienobiektow);
            JOptionPane.showMessageDialog(null, s);
            strumienobiektow.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Blad odczytu pliku obiektow ");
        }
    }
    
//Nie chroni przed zlym typem danych i przed brakiem danych w niektorych metodach
static public void main(String args[]){
    GUI baza = new GUI(); //referencja do tablicy, kt�ra jest tworzona w opcji 1
        char ch;
        String s;
        do {
            s = JOptionPane.showInputDialog(null, "Progam nie jest zabezpieczony przed\n"
                    + "złym formatem danych\n i naciskaniem Cancel w okienkach dialogowych\n"
                    + "Podaj wybor"
                    + "\n1 - Wstaw tytul,"
                    + "\n2 - Wstaw ksiazke"
                    + "\n3 - Wyszukaj&wyswietl dane tytul/u"
                    + "\n4 - Usun podany tytul"
                    + "\n5 - Usun podana ksiazke"
                    + "\n6 - Zapisz do pliku"
                    + "\n7 - Odczyt z pliku"
                    + "\n8 - Wyswietl tytuly"
                    + "\n9 - Wyswietl ksiazki podanego ISBN"
                    + "\nk - Koniec programu");
            ch = s.charAt(0);  //pobranie opcji
            switch (ch) {
                case '1':
                    baza.Wstaw_tytul();
                    break;
                case '2':
                    baza.Wstaw_ksiazke();
                    break;
                case '3':
                    baza.Wyszukaj_tytul();
                    break;
                case '4':
                    baza.Usun_tytul();
                    break;
                case '5':
                    baza.Usun_ksiazke();
                    break;
                case '6':
                    baza.Zapisz();
                    break;
                case '7':
                    baza.Odczytaj();
                    break;
                case '8':
                    baza.Wyswietl_tytuly();
                    break;
                case '9':
                    baza.Wyswietl_ksiazki();
                    break;                    
                case 'k':
                    JOptionPane.showMessageDialog(null, "Koniec programu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Zla opcja");
            }
        } while (ch != 'k');
        System.exit(0);    
}
}