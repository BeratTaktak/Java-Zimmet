package Zimmet;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Zimmet{

    
    static int Psayac = 0; // Personel sayacı
    static int Dsayac = 0; // Depo sayacı
    
    
    static String [] pUrunAdi = new String[100];
    static String [] pUrunKodu = new String[100];
    static String [] pİsim = new String[100];
    static long [] pİsimTC = new long [100]; // LONG TANIMLANDI ÇÜNKÜ TC İNT İÇİNE SIĞMIYOR    
    static boolean [] pAktif = new boolean[100];
    
    static String [] dUrunAdi = new String[100];
    static String [] dUrunKodu = new String[100];
    

    
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
            System.out.println("3. TÜM ZİMMETLERİ SİL (Temizle)");
            System.out.println("4. TÜM DEPOYU SİL (Temizle)");
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
                case 3 -> tumZimmetleriSil();
                case 4 -> tumDepoUrunleriniSil();
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
                
                pİsim[Psayac] = temizGiris;
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
                    
                    if(pİsimTC[i] == Tc && pAktif[i]){
                        
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
                        
                        pİsimTC[Psayac] = Tc;
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
                
                pUrunAdi [Psayac] = temizGiris;
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
                
                pUrunKodu [Psayac] = temizGiris;
                urunKodGecerli = true;
            }
        }
        
        
        pAktif[Psayac] = true;
        
        System.out.println("");
        
        System.out.println("Başarılı: " + pİsim[Psayac] + " kişisine yeni zimmet başarıyla yapıldı.");
        
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
                dUrunAdi[Dsayac] = temizGiris;
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
                dUrunKodu[Dsayac] = temizGiris;
                depoKodGecerli = true;
            }
        }
        
        System.out.println("Başarılı: Depo ürünü başarıyla tanımlandı: " + dUrunAdi[Dsayac] + " (" + dUrunKodu[Dsayac] + ")");
        
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
        
            if (pİsimTC[i] == arananTC && pAktif[i]) {
                
                System.out.println("-------------------------------------------");
                System.out.println("Kayıt Sırası: " + i);
                System.out.println("Personel Adı: " + pİsim[i]);
                System.out.println("TC: " + pİsimTC[i]);
                System.out.println("Ürün Adı: " + pUrunAdi[i]);
                System.out.println("Ürün Kodu: " + pUrunKodu[i]);
                System.out.println("-------------------------------------------");
                
                bulundu = true;
                
                System.out.println("Zimmeti kaldırmak (iade almak) ister misiniz? (1: Evet, 0: Hayır)");
                System.out.print("Seçim: ");
                
                if (s.hasNextInt()) {
                    
                    int secim = s.nextInt();
                    s.nextLine();
                    
                    if (secim == 1) {                                    
                                                 
                        for (int j = i; j < Psayac - 1; j++) {              
                            
                            pUrunAdi[j] = pUrunAdi[j + 1];
                            pUrunKodu[j] = pUrunKodu[j + 1];
                            pİsim[j] = pİsim[j + 1];
                            pİsimTC[j] = pİsimTC[j + 1];
                            pAktif[j] = pAktif[j + 1];
                        }
                        
                        Psayac--;
                        
                        System.out.println("Başarılı: Zimmet kaydı kaldırıldı");  
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
            System.out.println("Ürün Adı: " + dUrunAdi[i]);
            System.out.println("Ürün Kodu: " + dUrunKodu[i]);
        }
        
        System.out.println("-------------------------------------------");  
    }
    

    
    // ZİMMETLERİ KAYDETME
    
    static void verileriKaydet() throws IOException {
        
      
        File fZimmet = new File("zimmetler.txt");
        FileWriter zimmetYaz = new FileWriter(fZimmet);
        
        for(int i = 0; i < Psayac; i++){
            
            if(pAktif[i]){
                
                zimmetYaz.write(pİsim[i] + "|" + 
                               pİsimTC[i] + "|" + 
                               pUrunAdi[i] + "|" + 
                               pUrunKodu[i] + "\n");
            }
        }
        zimmetYaz.close();
        
        File fDepo = new File("depo.txt");
        FileWriter depoYaz = new FileWriter(fDepo);
        
        for(int i = 0; i < Dsayac; i++){
            
            depoYaz.write(dUrunAdi[i] + "|" + dUrunKodu[i] + "\n");
        }
        depoYaz.close();
        
    }
    
    
    
    
    // ZİMMETLERİ OKUMA
    
    static void verileriOku() throws IOException {
        
        File fZimmet = new File("zimmetler.txt");
        
        if (!fZimmet.exists()) { 
            
            fZimmet.createNewFile();
        }
        
        Scanner zimmetOku = new Scanner(fZimmet);
        
        while(zimmetOku.hasNextLine()){
            
            String satir = zimmetOku.nextLine();
            String[] veri = satir.split("\\|"); 
            
            if(veri.length == 4){
                
                pİsim[Psayac] = veri[0];
                
                Scanner sc = new Scanner(veri[1]);
                
                if(sc.hasNextLong()){
                    
                     pİsimTC[Psayac] = sc.nextLong();
                }
                
                pUrunAdi[Psayac] = veri[2];
                pUrunKodu[Psayac] = veri[3];
                pAktif[Psayac] = true;
                Psayac++;
            }
        }
        zimmetOku.close();
        
        
        File fDepo = new File("depo.txt");

        if (!fDepo.exists()) {
            
            fDepo.createNewFile();
        }
        
        Scanner DepoOku = new Scanner(fDepo);
        
        while(DepoOku.hasNextLine()){
            
            String satir = DepoOku.nextLine();
            String[] veri = satir.split("\\|");
            
            if(veri.length == 2){
                
                dUrunAdi[Dsayac] = veri[0];
                dUrunKodu[Dsayac] = veri[1];
                Dsayac++;
            }
        }
        DepoOku.close();
    }
    
    
    
    // TÜM ZİMMETLERİ SİLME
    
    static void tumZimmetleriSil() throws IOException {
        Scanner s = new Scanner(System.in);
        
        System.out.println("-------------------------------------------");
        System.out.println("!!! DİKKAT !!!");
        System.out.println("Kayıtlı TÜM zimmet verileri silinecek ve geri getirilemez.");
        System.out.println("Onaylıyor musunuz? (1: Evet 0: Hayır)");
        System.out.print("Seçim: ");
        
        if(s.hasNextInt()){
            
            int onay = s.nextInt();
            s.nextLine();
            
            if(onay == 1){
               
                Psayac = 0; 
                
                verileriKaydet(); 
                
                System.out.println(">> Başarılı: Tüm zimmet kayıtları silindi ve sistem temizlendi.");
            }
            
            else {
                
                System.out.println("İşlem iptal edildi.");
            } 
        }
        
        else {
            
            System.out.println("Hatalı tuşlama, işlem iptal.");
        }
    }
    
    
    
    // TÜM DEPO ÜRÜNLERİNİ SİLME
    static void tumDepoUrunleriniSil() throws IOException {
        Scanner s = new Scanner(System.in);

        System.out.println("-------------------------------------------");
        System.out.println("!!! DİKKAT !!!");
        System.out.println("Depoda tanımlı TÜM ürünler silinecek.");
        System.out.println("Onaylıyor musunuz? (1: Evet, 0: Hayır)");
        System.out.print("Seçim: ");

        if (s.hasNextInt()) {
            
            int onay = s.nextInt();
            s.nextLine();

            if (onay == 1) {

                Dsayac = 0; 
                
                verileriKaydet();
                
                System.out.println(">> Başarılı: Depo tamamen temizlendi.");
            } 
            
            else {
                
                System.out.println("İşlem iptal edildi.");
            } 
        }
        
        else {
            
            System.out.println("Hatalı tuşlama, işlem iptal.");
        }
    }
    
    
    
}