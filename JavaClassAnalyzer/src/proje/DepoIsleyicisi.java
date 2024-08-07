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

DepoIsleyicisi sınıfı: Bu sınıf, bir GitHub deposunu klonlar
ve klonlanan depoyu siler.

</p>

*/

package proje;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.io.IOException;

public class DepoIsleyicisi {
	
	
	 public static void cloneRepository(String depoURL) {
	        try {
	            String klonKomutu = "git clone " + depoURL + " repo";
	            Process islem = Runtime.getRuntime().exec(klonKomutu);
	            islem.waitFor();

	            BufferedReader okuyucu = new BufferedReader(new InputStreamReader(islem.getInputStream()));
	            String satir;
	            while ((satir = okuyucu.readLine()) != null) {
	                System.out.println(satir);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void directoryiSil(Path x) {
	        try {
	            Files.walk(x)
	                 .sorted(Comparator.reverseOrder())
	                 .map(Path::toFile)
	                 .forEach(File::delete);
	            
	        } catch (IOException e) {
	            System.err.println("Failed to delete the directory.");
	            e.printStackTrace();
	        }
	    }
	
	
	

}
