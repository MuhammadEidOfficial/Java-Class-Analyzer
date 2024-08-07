////////////////////////////////////////////////////////
//													  //
//             T.C. SAKARYA ÜNİVERSİTESİ              //
//      BİLGİSAYAR VE BİLİŞİM BİLİMLERİ FAKÜLTESİ     //
//           BİLGİSAYAR MÜHENDİSLİĞİ BÖLÜMÜ           //
//    PROGRAMLAMA DİLLERİNİN PRENSİPLERİ ÖDEV RAPORU  //
//                      1.ÖDEV                        //
//                                                    //
//            Muhammed İyd - B211210569               //
//                1.Öğretim B grup                    //
//                                                    //
//         muhamad.iyd1@ogr.sakarya.edu.tr            //
//     Programın yazıldığı tarih: <05.04.2024>        //
//													  //
////////////////////////////////////////////////////////



/*

<p>

Main sınıfı:Bu sınıf, ana sınıf'tır tabiki
ve kullanıcıdan GitHub deposunun URL'sini alır,
bu depoyu klonlar, klonlanan depodaki Java dosyalarını analiz eder
ve ardından indirilen depoyu siler.

</p>

*/

package proje;

import java.io.File;
import java.util.Scanner;
	
	
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
			System.out.print("Lütfen GitHub deposu URL'sini giriniz: ");
			 String depoURL = scanner.nextLine();
			 DepoIsleyicisi.cloneRepository(depoURL);
			 File klasor = new File("repo"); 
			 JavaDosyaAnalizci.javaDosyalariniAnalizEt(klasor); 
			 
			 DepoIsleyicisi.directoryiSil(klasor.toPath());
	}
			 
}
