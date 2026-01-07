package Zimmet;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Zimmet{

    
    static int Psayac = 0; // Personel sayacı
    static int Dsayac = 0; // Depo sayacı
    
    
    static String [] pZimmetAdiDizi = new String[100];
    static String [] pZimmetKoduDizi = new String[100];
    static String [] pZimmetliPersonelDizi = new String[100];
    static long [] pZimmetliPersonelTcDizi = new long [100]; // LONG TANIMLANDI ÇÜNKÜ TC İNT İÇİNE SIĞMIYOR    
    static boolean [] aktifZimmetVarDizi = new boolean[100];
    
    static String [] dDepoAdiDizi = new String[100];
    static String [] dDepoKoduDizi = new String[100];
    

    
    public static void main(String[] args) throws IOException {
        
        verileriOku(); 
        
        anaMenuyuGoster(); 
    }
    
    
    
    //ANA MENÜ
    
    static void anaMenuyuGoster() throws IOException { 
        
        Scanner s = new Scanner(System.in); 
        int secim = -1;
        
        while (secim != 0) { 
            
            System.out.println("");
            System.out.println("-------------------------------------------");
            System.out.println("             ••• ANA MENÜ •••             ");
            System.out.println("1. Zimmet İşlemi Yap");
            System.out.println("2. Zimmetleri Görüntüle");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            if (s.hasNextInt()) {
                secim = s.nextInt(); 
            } 
            else {
                
                System.out.println("HATA: Lütfen sadece rakam girin.");
                s.next();
                
                continue;
            }
            
            s.nextLine();

            switch (secim) {
                case 1 -> zimmetIslemiMenusu();
                case 2 -> zimmetleriGoruntuleMenusu();
                case 0 -> {
                    verileriKaydet();
                    System.out.println("Çıkış yapılıyor.");
                }
                default -> System.out.println("Hatalı seçim.");
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
            System.out.println("           ••• ZİMMET İŞLEM MENÜSÜ •••           ");
            System.out.println("1. Personele Zimmet Yap"); 
            System.out.println("2. Depoya Ürün Tanımla");   
            System.out.println("0. Geri Dön");
            System.out.print("Seçiminiz: ");
            
            if (s.hasNextInt()) {
                
                secim = s.nextInt(); 
            } 
            else {
                
                System.out.println("HATA: Lütfen sadece rakam girin.");
                s.next(); 
                
                continue;
            }
            
            s.nextLine();
            
            switch (secim) {
                case 1 -> personeleZimmetYap(s);
                case 2 -> depoyaUrunTanimla(s);
                case 0 -> System.out.println("Ana Menüye Dönülüyor.");
                default -> System.out.println("Hatalı Seçim.");
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
            System.out.println("         ••• ZİMMET GÖRÜNTÜLEME MENÜSÜ •••       ");
            System.out.println("1. Personel Zimmeti Görüntüle"); 
            System.out.println("2. Depo Bilgisini Görüntüle");   
            System.out.println("0. Geri Dön");
            System.out.print("Seçiminiz: ");
            
            if (s.hasNextInt()) {
                
                secim = s.nextInt(); 
            } 
            else {
                
                System.out.println("HATA: Lütfen sadece rakam girin.");
                s.next();
                
                continue;
            }
            
            s.nextLine(); 
            
            switch (secim) {
                case 1 -> personelZimmetGoruntule(s);
                case 2 -> depoBilgisiGoruntule();
                case 0 -> System.out.println("Ana Menüye Dönülüyor.");
                default -> System.out.println("Hatalı seçim.");
            }
        }
    }

    
    
    //PERSONELE ZİMMET YAPMA
    
    static void personeleZimmetYap(Scanner s) { 
        
        if(Psayac >= 100){
            
             System.out.println("HATA: Maksimum zimmet sınırına ulaşıldı (100).");
             
             return;
        }

        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("          ••• PERSONELE YENİ ZİMMET •••          ");
        
        
        // PERSONEL ADI GİRİŞİ(BOŞLUK KONTROLÜ)
        boolean adGecerli = false;
        while (!adGecerli) {
            
            System.out.print("Zimmetlenecek Personel Adını Girin: ");
            String adGiris = s.nextLine();
            
            String temizGiris = adGiris.replace('|', ' ').trim(); 

            if (temizGiris.length() == 0) { 
                
                System.out.println("HATA: Personel Adı boşluk veya boş bırakılamaz.");
            }
            else {
                
                pZimmetliPersonelDizi[Psayac] = temizGiris;
                adGecerli = true;
            }
        }
        
        
        long Tc = 0;
        boolean tcGecerli = false;
        
        while(!tcGecerli){
            
            System.out.print("Personel TC Kimlik Numarası Girin (11 rakam):");
            
            
            if(s.hasNextLong()){
                
             Tc = s.nextLong();
             s.nextLine(); 
             
             
                //TC ZATEN KULLANIMDAMI KONTROL NOKTASI
                boolean tcZatenVar = false;      
                for(int i = 0; i < Psayac; i++){
                    
                    if(pZimmetliPersonelTcDizi[i] == Tc && aktifZimmetVarDizi[i]){
                        
                        tcZatenVar = true;
                        break;    
                    }
                }
                
                
                if(tcZatenVar){
                    
                    System.out.println("HATA: Bu Tc kimlik numarsı zaten kullanımda.");
                }
                
                
                else{

                // TC 11 HANE KONTROL NOKTASI
                long geciciTc = Tc;
                int basamakSayisi = 0;
                
                    if(geciciTc == 0){
                        
                        basamakSayisi = 1;
                    }
                    else{
                        
                        while(geciciTc != 0){
                            
                            geciciTc /=10;
                            basamakSayisi++;    
                        }   
                    }
                    
                    if(basamakSayisi == 11){
                        
                        pZimmetliPersonelTcDizi[Psayac] = Tc;
                        tcGecerli = true;    
                    }
                    else{
                        
                        System.out.println("HATA: TC Kimlik Numarası tam 11 haneli olmalıdır. Tekrar deneyin.");
                    }   
                }
            }    
            else{
                
                System.out.println("HATA: TC Kimlik Numarası sadece rakamlardan oluşmalıdır.");
                s.next();
            }
        }
        
        
        // ÜRÜN ADI GİRİŞİ (BOŞLUK KONTROLÜ)
        boolean urunAdGecerli = false;
        while (!urunAdGecerli) {
            
            System.out.print("Ürün Adını Girin: ");
            String urunAdGiris = s.nextLine();

            String temizGiris = urunAdGiris.replace('|', ' ').trim(); 

            if (temizGiris.length() == 0) {
                
                System.out.println("HATA: Ürün Adı boşluk veya boş bırakılamaz.");
            } 
            else {
                
                pZimmetAdiDizi [Psayac] = temizGiris;
                urunAdGecerli = true;
            }
        }
        
        // ÜRÜN KODU GİRİŞİ (BOŞLUK KONTROLÜ)
        boolean urunKodGecerli = false;
        while (!urunKodGecerli) {
            
            System.out.print("Ürün Kodunu Girin: ");
            String urunKodGiris = s.nextLine();

            String temizGiris = urunKodGiris.replace('|', ' ').trim();

            if (temizGiris.length() == 0) {
                
                System.out.println("HATA: Ürün Kodu boşluk veya boş bırakılamaz.");
            } 
            else {
                
                pZimmetKoduDizi [Psayac] = temizGiris;
                urunKodGecerli = true;
            }
        }
        
        
        aktifZimmetVarDizi[Psayac] = true;
        
        System.out.println("");
        
        System.out.println("Başarılı: " + pZimmetliPersonelDizi[Psayac] + " kişisine yeni zimmet başarıyla yapıldı.");
        
        Psayac++;
    }
    
    
    
    
    //DEPOYA ÜRÜN TANIMLA
    
    static void depoyaUrunTanimla(Scanner s) { 
        
          if(Dsayac >= 100){
              
             System.out.println("HATA: Depoya daha fazla ürün girişi yapılamaz (100).");
             
             return;
        }
        
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("          ••• DEPOYA ÜRÜN TANIMLAMA •••          ");
        
        
        // DEPO ÜRÜN ADI GİRİŞİ (BOŞLUK KONTROLÜ)
        boolean depoAdGecerli = false;
        while (!depoAdGecerli) {
            
            System.out.print("Depo Ürünü Adını Girin: ");
            String depoAdGiris = s.nextLine();
            
            String temizGiris = depoAdGiris.replace('|', ' ').trim();
            
            if (temizGiris.length() == 0) { 
                System.out.println("HATA: Depo Ürünü Adı boşluk veya boş bırakılamaz.");
            } else {
                dDepoAdiDizi[Dsayac] = temizGiris;
                depoAdGecerli = true;
            }
        }
        
        // DEPO ÜRÜN KODU GİRİŞİ (BOŞLUK KONTROLÜ)
        boolean depoKodGecerli = false;
        while (!depoKodGecerli) {
            
            System.out.print("Depo Ürünü Kodunu Girin: ");
            String depoKodGiris = s.nextLine();

            String temizGiris = depoKodGiris.replace('|', ' ').trim();
            
            if (temizGiris.length() == 0) { 
                System.out.println("HATA: Depo Ürünü Kodu boşluk veya boş bırakılamaz.");
            } else {
                dDepoKoduDizi[Dsayac] = temizGiris;
                depoKodGecerli = true;
            }
        }
        
        System.out.println("Başarılı: Depo ürünü başarıyla tanımlandı: " + dDepoAdiDizi[Dsayac] + " (" + dDepoKoduDizi[Dsayac] + ")");
        
        Dsayac++; 
    }

    
    
    
    // PERSONEL ZİMMETİ GÖRÜNTÜLEME
    
    static void personelZimmetGoruntule(Scanner s) { 
        
        System.out.println("");
        System.out.println("         ••• PERSONEL ZİMMET DETAYI SORGULAMA •••         ");
        System.out.println("");
        
        if(Psayac == 0) {
            
            System.out.println("   »» Kayıtlı personel zimmeti bulunmamaktadır.   ");
            
            return;
        }

        System.out.print("Personel TC'sini Giriniz (11 Rakam): ");
        
        long arananTC = 0;
        
        //VERİ TİPİ KONTROL NOKTASI
        if (s.hasNextLong()) {
            
            arananTC = s.nextLong();
            s.nextLine();
        } 
        else {
            
            System.out.println("HATA: TC sorgulaması için sadece rakam girin.");
            s.next();
            
            return;
        }
      
        
        //11 HANE KONTROL NOKTASI
        long geciciArananTC = arananTC; 
        int basamakSayisi = 0;
        
        if (geciciArananTC == 0) {
            basamakSayisi = 1;
        } 
        else {
            
            while (geciciArananTC != 0) {
                geciciArananTC /= 10; 
                basamakSayisi++;
            }
        }
        
        if (basamakSayisi != 11) {
            
            System.out.println("HATA: Sorgulanan TC 11 haneli olmalıdır.");
            return;
        } 

        boolean bulundu = false;
        
        for(int i = 0; i < Psayac; i++){
        
            if (pZimmetliPersonelTcDizi[i] == arananTC && aktifZimmetVarDizi[i]) {
                
                System.out.println("-------------------------------------------");
                System.out.println("Kayıt Sırası: " + i);
                System.out.println("Personel Adı: " + pZimmetliPersonelDizi[i]);
                System.out.println("TC: " + pZimmetliPersonelTcDizi[i]);
                System.out.println("Ürün Adı: " + pZimmetAdiDizi[i]);
                System.out.println("Ürün Kodu: " + pZimmetKoduDizi[i]);
                System.out.println("-------------------------------------------");
                
                bulundu = true;
                
                System.out.println("Zimmeti kaldırmak (iade almak) ister misiniz? (1: Evet, 0: Hayır)");
                System.out.print("Seçim: ");
                
                if (s.hasNextInt()) {
                    
                    int secim = s.nextInt();
                    s.nextLine();
                    
                    if (secim == 1) {                                    
                                                 
                        for (int j = i; j < Psayac - 1; j++) {              
                            
                            pZimmetAdiDizi[j] = pZimmetAdiDizi[j + 1];
                            pZimmetKoduDizi[j] = pZimmetKoduDizi[j + 1];
                            pZimmetliPersonelDizi[j] = pZimmetliPersonelDizi[j + 1];
                            pZimmetliPersonelTcDizi[j] = pZimmetliPersonelTcDizi[j + 1];
                            aktifZimmetVarDizi[j] = aktifZimmetVarDizi[j + 1];
                        }
                        
                        Psayac--;
                        
                        System.out.println("Başarılı: Zimmet kaydı kaldırıldı (Sırası: " + i + ")");  
                    }
                } 
                else {
                    
                    System.out.println("Geçersiz giriş yapıldı. Zimmet kaydı silinmedi, menüye dönülüyor.");
                    s.next(); 
                }
                
                break; 
            }       
        }
        
        if (!bulundu) {
             
             System.out.println("HATA: Girilen TC kimlik numarasına ait aktif zimmet kaydı bulunamadı.");
        }
    }

    
    
    //DEPO GÖRÜNTÜLEME
    
    static void depoBilgisiGoruntule() { 
        
        System.out.println("");      
        System.out.println("             ••• DEPO DETAYI •••             ");
        System.out.println("");
        
        if (Dsayac == 0) {
            
            System.out.println("»» Depoda tanımlı ürün bulunmamaktadır.");
            
            return;
                        
        }
        
        
        for(int i = 0; i < Dsayac; i++){
            
            System.out.println("-------------------------------------------");
            System.out.println("Kayıt Sırası: " + i);
            System.out.println("Ürün Adı: " + dDepoAdiDizi[i]);
            System.out.println("Ürün Kodu: " + dDepoKoduDizi[i]);
        }
        
        System.out.println("-------------------------------------------");  
    }
    

    
    // ZİMMETLERİ KAYDETME
    
    static void verileriKaydet() throws IOException {
        
      
        File fZimmet = new File("zimmetler.txt");
        FileWriter zimmetYaz = new FileWriter(fZimmet);
        
        for(int i = 0; i < Psayac; i++){
            
            if(aktifZimmetVarDizi[i]){
                
                zimmetYaz.write(pZimmetliPersonelDizi[i] + "|" + 
                               pZimmetliPersonelTcDizi[i] + "|" + 
                               pZimmetAdiDizi[i] + "|" + 
                               pZimmetKoduDizi[i] + "\n");
            }
        }
        zimmetYaz.close();
        
        File fDepo = new File("depo.txt");
        FileWriter depoYaz = new FileWriter(fDepo);
        
        for(int i = 0; i < Dsayac; i++){
            
            depoYaz.write(dDepoAdiDizi[i] + "|" + dDepoKoduDizi[i] + "\n");
        }
        depoYaz.close();
        
    }
    
    
    
    
    // ZİMMETLERİ OKUMA
    
    static void verileriOku() throws IOException {
        
        File fZimmet = new File("zimmetler.txt");
        FileWriter fwOlustur = new FileWriter(fZimmet, true);
        fwOlustur.close();
        
        Scanner zimmetOku = new Scanner(fZimmet);
        
        while(zimmetOku.hasNextLine()){
            
            String satir = zimmetOku.nextLine();
            String[] veri = satir.split("\\|"); 
            
            if(veri.length == 4){
                
                pZimmetliPersonelDizi[Psayac] = veri[0];
                
                Scanner sc = new Scanner(veri[1]);
                
                if(sc.hasNextLong()){
                    
                     pZimmetliPersonelTcDizi[Psayac] = sc.nextLong();
                }
                
                pZimmetAdiDizi[Psayac] = veri[2];
                pZimmetKoduDizi[Psayac] = veri[3];
                aktifZimmetVarDizi[Psayac] = true;
                Psayac++;
            }
        }
        
        
        File fDepo = new File("depo.txt");

        FileWriter fwDepoOlustur = new FileWriter(fDepo, true);
        fwDepoOlustur.close();
        
        Scanner DepoOku = new Scanner(fDepo);
        
        while(DepoOku.hasNextLine()){
            
            String satir = DepoOku.nextLine();
            String[] veri = satir.split("\\|");
            
            if(veri.length == 2){
                
                dDepoAdiDizi[Dsayac] = veri[0];
                dDepoKoduDizi[Dsayac] = veri[1];
                Dsayac++;
            }
        }
    }
}
