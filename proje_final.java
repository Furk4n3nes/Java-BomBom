import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class proje_final {

    private static final int SATIR_SAYISI = 10;
    private static final int SUTUN_SAYISI = 10;

    public static void main(String[] args) {
        // Haritayı dosyadan okuyarak başlatma
        char[][] harita = haritayiOku("harita.txt");

        if (harita == null) {
            System.out.println("Harita yüklenirken hata oluştu.");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Haritayı gösterme
            haritayiYazdir(harita);

            System.out.print("Satır giriniz: ");
            int satir = scanner.nextInt();
            System.out.print("Sütun giriniz: ");
            int sutun = scanner.nextInt();

            // Oyunu sonlandırma
            if (satir == 0 && sutun == 0) {
                System.out.println("Oyun sonlandırıldı.");
                break;
            }
            // Girilen konumu kontrol etme
            if (satir < 1 || satir > SATIR_SAYISI || sutun < 1 || sutun > SUTUN_SAYISI) {
                System.out.println("Geçersiz koordinat! Tekrar deneyin.");
                continue;
            }
            // İşaretleme işlemini gerçekleştirme
            kontrolEtVeGuncelle(harita, satir - 1, sutun - 1);
        }
    }
    // Haritayı dosyadan okuma
    public static char[][] haritayiOku(String dosyaAdi) {
        char[][] harita = new char[SATIR_SAYISI][SUTUN_SAYISI];

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            int satirIndex = 0;
            while ((satir = br.readLine()) != null && satirIndex < SATIR_SAYISI) {

                satir = satir.replaceAll("\\s", "");

                if (satir.length() < SUTUN_SAYISI) {
                    System.out.println("Harita satırı eksik, dosya okuması durduruldu.");
                    return null;
                }

                harita[satirIndex] = satir.toCharArray();
                satirIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return harita;
    }
    // Haritayı ekrana yazdırma
    public static void haritayiYazdir(char[][] harita) {
        for (char[] satir : harita) {
            for (char hucre : satir) {
                System.out.print(hucre+ " ");
            }
            System.out.println();
        }
    }
    // Kontrol etme ve işaretleme işlemini gerçekleştirme
    public static void kontrolEtVeGuncelle(char[][] harita, int satir, int sutun) {
        char hedef = harita[satir][sutun];
        kontrolEt(harita, satir, sutun, hedef);
    }
    // Harita üzerinde rekürsif olarak kontrol etme işlemi
    public static void kontrolEt(char[][] harita, int satir, int sutun, char hedef) {
        if (satir < 0 || satir >= SATIR_SAYISI || sutun < 0 || sutun >= SUTUN_SAYISI|| harita[satir][sutun] != hedef) {
            return;
        }
        // Hedef işaretleme
        harita[satir][sutun] = 'X';
        kontrolEt(harita, satir - 1, sutun, hedef);
        kontrolEt(harita, satir, sutun + 1, hedef);
        kontrolEt(harita, satir + 1, sutun, hedef);
        kontrolEt(harita, satir, sutun - 1, hedef);
    }
}

