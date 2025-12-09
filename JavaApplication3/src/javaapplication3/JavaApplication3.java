package JavaApplication5;

import java.util.Scanner;


public class JavaApplication5 {
    
    static int Psayac = 0;
    static int Dsayac = 0;
    
    static String [] pZimmetAdiDizi = new String[100];
    static String [] pZimmetKoduDizi = new String[100];
    static String [] pZimmetliPersonelDizi = new String[100];
    static boolean [] aktifZimmetVarDizi = new boolean[100];
    static String [] dDepoAdiDizi = new String[100];
    static String [] dDepoKoduDizi = new String[100];
    
    

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
            System.out.println("-------------------------------------------");
            System.out.println("             ••• ANA MENU •••           ");
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

            switch (secim) {
                case 1 -> zimmetIslemiMenusu();
                case 2 -> zimmetleriGoruntuleMenusu();
                case 0 -> System.out.println("Cikis yapiliyor.");
                default -> System.out.println("Hatali secim.");
            }
        }
    }
    
    //ZİMMET İŞLEM MENÜSÜ 
    static void zimmetIslemiMenusu() { 
        Scanner s = new Scanner(System.in); 
        int secim = -1;
        
        while (secim != 0) {
            System.out.println("");
            System.out.println("-------------------------------------------");
            System.out.println("         ••• ZIMMET ISLEM MENUSU •••         ");
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
            
            switch (secim) {
                case 1 -> personeleZimmetYap(s);
                case 2 -> depoyaUrunTanimla(s);
                case 0 -> System.out.println("Ana menuye donuluyor.");
                default -> System.out.println("Hatali secim.");
            }
        }
    }

    // ZİMMET GÖRÜNTÜLEME MENÜSÜ 
    static void zimmetleriGoruntuleMenusu() { 
        Scanner s = new Scanner(System.in); 
        int secim = -1;
        
        while (secim != 0) {
            System.out.println("");
            System.out.println("-------------------------------------------");
            System.out.println("      ••• ZIMMET GORUNTULEME MENUSU •••      ");
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
            
            switch (secim) {
                case 1 -> personelZimmetGoruntule();
                case 2 -> depoBilgisiGoruntule();
                case 0 -> System.out.println("Ana menuye donuluyor.");
                default -> System.out.println("Hatali secim.");
            }
        }
    }

  

    //PERSONELE ZİMMET YAPMA
    static void personeleZimmetYap(Scanner s) { 
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("        ••• PERSONELE YENI ZIMMET •••        ");
        
        
        if(Psayac <= 100){
            
        System.out.print("Zimmetlenecek Personel Adini Girin (String): ");
        pZimmetliPersonel = s.nextLine(); 
        pZimmetliPersonelDizi[Psayac] = pZimmetliPersonel;
        
        
        System.out.print("Urun Adini Girin (String): ");
        pZimmetAdi = s.nextLine();
        pZimmetAdiDizi [Psayac] = pZimmetAdi;
        
        
        
        System.out.print("Urun Kodunu Girin (String/Harf-Sayi): ");
        pZimmetKodu = s.nextLine();
        pZimmetKoduDizi [Psayac] = pZimmetKodu;
        
        aktifZimmetVarDizi[Psayac] = aktifZimmetVar = true;
        System.out.println("Basarili: " + pZimmetliPersonel + " kisisine yeni zimmet basariyla yapildi.");
        
        
        Psayac++;
        }   
        
    }
    
    //DEPOYA ÜRÜN TANIMLA
    static void depoyaUrunTanimla(Scanner s) { 
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("        ••• DEPOYA URUN TANIMLAMA •••        ");
        
        if(Dsayac <= 100){
        
        System.out.print("Depo Urunu Adini Girin (String): ");
        dDepoAdi = s.nextLine();
        dDepoAdiDizi[Dsayac] = dDepoAdi; 
        
        System.out.print("Depo Urunu Kodunu Girin (String/Harf-Sayi): ");
        dDepoKodu = s.nextLine();
        dDepoKoduDizi[Dsayac] = dDepoKodu;
        
        System.out.println("Basarili: Depo urunu basariyla tanimlandi: " + dDepoAdi + " (" + dDepoKodu + ")");
        
        Dsayac++;
    }
        
      
}

    // PERSONEL ZİMMETİ GÖRÜNTÜLEME
    static void personelZimmetGoruntule() { 
        System.out.println("");
        System.out.println("        ••• PERSONEL ZIMMET DETAYI •••       ");
        
        if (aktifZimmetVar) {
            
            for(int i = 0; i <= Psayac - 1; i++){
            System.out.println("Personel: " + pZimmetliPersonelDizi[i]);
            System.out.println("Urun Adi: " + pZimmetAdiDizi[i]);
            System.out.println("Urun Kodu: " + pZimmetKoduDizi[i]);
            System.out.println("");
        }
                
         
    }
        
        else {
            System.out.println("»» Personel zimmeti bulunmamaktadir.");
        }
        
    }

    //DEPO GÖRÜNTÜLEME
    static void depoBilgisiGoruntule() { 
        System.out.println("");   
        System.out.println("             ••• DEPO DETAYI •••             ");
        System.out.println("");
        
       
        if (dDepoKodu.equals("0")) {
            System.out.println("»» Depoda tanimli ana urun bulunmamaktadir.");
        } else {
            for(int i = 0; i <= Dsayac - 1; i++){
                
                System.out.println("Urun Adi: " + dDepoAdiDizi[i]);
                System.out.println("Urun Kodu: " + dDepoKoduDizi[i]);
                System.out.println("");
            }
            
        }
    }
}
