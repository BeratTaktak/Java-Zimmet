package JavaApplication3;

import java.util.Scanner;


public class JavaApplication3 {
    


    static String pZimmetAdi = "Yok";
    static String pZimmetKodu = "0"; 
    static String pZimmetliPersonel = "Yok";
    static boolean aktifZimmetVar = false; 
    
    static String dDepoAdi = "Yok"; 
    static String dDepoKodu = "0"; 

   
    public static void main(String[] args) {
        anaMenuyuGoster(); 
    }
    
    //ANA MENÜ
    static void anaMenuyuGoster() { 
        Scanner s = new Scanner(System.in); 
        int secim = -1;
        
        while (secim != 0) { 
            System.out.println("");
            System.out.println("ANA MENU");
            System.out.println("-------------------------");
            System.out.println("1. Zimmet Islemi Yap");
            System.out.println("2. Zimmetleri Goruntule");
            System.out.println("0. Cikis");
            System.out.print("Seciminiz: ");

            if (s.hasNextInt()) {
                secim = s.nextInt(); 
            } else {
                System.out.println("HATA: Lutfen sadece rakam girin.");
                s.next(); 
                continue;
            }
            s.nextLine(); 

            if (secim == 1) {
                zimmetIslemiMenusu(); 
            } else if (secim == 2) {
                zimmetleriGoruntuleMenusu();
            } else if (secim == 0) {
                System.out.println("Cikis yapiliyor.");
            } else {
                System.out.println("Hatali secim.");
            }
        }
    }
    
    //ZİMMET İŞLEM MENÜSÜ 
    static void zimmetIslemiMenusu() { 
        Scanner s = new Scanner(System.in); 
        int secim = -1;
        
        while (secim != 0) {
            System.out.println("");
            System.out.println("ZIMMET ISLEM MENUSU");
            System.out.println("-------------------------");
            System.out.println("1. Personele Zimmet Yap"); 
            System.out.println("2. Depoya Urun Tanimla");   
            System.out.println("0. Geri Don");
            System.out.print("Seciminiz: ");
            
            if (s.hasNextInt()) {
                secim = s.nextInt(); 
            } else {
                System.out.println("HATA: Lutfen sadece rakam girin.");
                s.next(); 
                continue;
            }
            s.nextLine(); 
            
            if (secim == 1) {
                personeleZimmetYap(s);
            } else if (secim == 2) {
                depoyaUrunTanimla(s);
            } else if (secim == 0) {
                System.out.println("Ana menuye donuluyor.");
            } else {
                System.out.println("Hatali secim.");
            }
        }
    }

    // ZİMMET GÖRÜNTÜLEME MENÜSÜ 
    static void zimmetleriGoruntuleMenusu() { 
        Scanner s = new Scanner(System.in); 
        int secim = -1;
        
        while (secim != 0) {
            System.out.println("");
            System.out.println("ZIMMET GORUNTULEME MENUSU");
            System.out.println("-------------------------");
            System.out.println("1. Personel Zimmetini Goruntule"); 
            System.out.println("2. Depo Bilgisini Goruntule");   
            System.out.println("0. Geri Don");
            System.out.print("Seciminiz: ");
            
            if (s.hasNextInt()) {
                secim = s.nextInt(); 
            } else {
                System.out.println("HATA: Lutfen sadece rakam girin.");
                s.next(); 
                continue;
            }
            s.nextLine(); 
            
            if (secim == 1) {
                personelZimmetGoruntule();
            } else if (secim == 2) {
                depoBilgisiGoruntule();
            } else if (secim == 0) {
                System.out.println("Ana menuye donuluyor.");
            } else {
                System.out.println("Hatali secim.");
            }
        }
    }

  

    //PERSONELE ZİMMET YAPMA
    static void personeleZimmetYap(Scanner s) { 
        System.out.println("");
        System.out.println("PERSONELE YENI ZIMMET");
        System.out.println("-------------------------");
        
        if (aktifZimmetVar) {
            System.out.println("UYARI: Eski zimmet kaydi (" + pZimmetliPersonel + ") silinerek yeni kayit olusturuluyor.");
        }

        System.out.print("Zimmetlenecek Personel Adini Girin (String): ");
        pZimmetliPersonel = s.nextLine();
        
        System.out.print("Urun Adini Girin (String): ");
        pZimmetAdi = s.nextLine();
        
        System.out.print("Urun Kodunu Girin (String/Harf-Sayi): ");
        pZimmetKodu = s.nextLine();
        
        aktifZimmetVar = true;
        System.out.println("Basarili: " + pZimmetliPersonel + " kisisine yeni zimmet basariyla yapildi.");
    }
    
    //DEPOYA ÜRÜN TANIMLA
    static void depoyaUrunTanimla(Scanner s) { 
        System.out.println("");
        System.out.println("DEPOYA URUN TANIMLAMA");
        System.out.println("-------------------------");
        
        System.out.print("Depo Urunu Adini Girin (String): ");
        dDepoAdi = s.nextLine();
        
        System.out.print("Depo Urunu Kodunu Girin (String/Harf-Sayi): ");
        dDepoKodu = s.nextLine();
        
        System.out.println("Basarili: Depo urunu basariyla tanimlandi: " + dDepoAdi + " (" + dDepoKodu + ")");
    }

    // PERSONEL ZİMMETİ GÖRÜNTÜLEME
    static void personelZimmetGoruntule() { 
        System.out.println("");
        System.out.println("PERSONEL ZIMMET DETAYI");
        System.out.println("-------------------------");
        
        if (aktifZimmetVar) {
            System.out.println("Personel: " + pZimmetliPersonel);
            System.out.println("Urun Adi: " + pZimmetAdi);
            System.out.println("Urun Kodu: " + pZimmetKodu);
            
            Scanner s = new Scanner(System.in);
            System.out.println("");
            System.out.println("Zimmeti kaldirmak (iade almak) ister misiniz? (1: Evet, 0: Hayir)");
            System.out.print("Secim: ");
            
            if (s.hasNextInt()) {
                int secim = s.nextInt();
                if (secim == 1) {
                    aktifZimmetVar = false;
                    pZimmetliPersonel = "Yok";
                    pZimmetAdi = "Yok";
                    pZimmetKodu = "0";
                    System.out.println("Basarili: Zimmet basariyla kaldirildi.");
                }
                
            }   
            
            else {
                s.next(); 
            }
            
        } 
        
        else {
            System.out.println("Personel zimmeti bulunmamaktadir.");
        }
        
    }

    //DEPO GÖRÜNTÜLEME
    static void depoBilgisiGoruntule() { 
        System.out.println("");
        System.out.println("DEPO TANIM DETAYI");
        System.out.println("-------------------------");
        
       
        if (dDepoKodu == "0") {
            System.out.println("Depoda tanimli ana urun bulunmamaktadir.");
        } else {
            System.out.println("Urun Adi: " + dDepoAdi);
            System.out.println("Urun Kodu: " + dDepoKodu);
        }
    }
}