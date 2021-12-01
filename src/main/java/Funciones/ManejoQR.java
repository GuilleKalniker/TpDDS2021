/*package Funciones;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class ManejoQR {

  public static String generarQR(long data, String fileName, Integer weidth, Integer height) {

    String ubicacionQr = "src/lista_contraseñas_no_seguras/QRs";
    String qrcodeFormat = "png";

    try {
      HashMap<EncodeHintType, String> hints = new HashMap<>();
      hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
      QRCodeWriter writer = new QRCodeWriter();
      BitMatrix bitMatrix = writer.encode(String.valueOf(data), BarcodeFormat.QR_CODE, weidth, height, hints);

      File file = new File(ubicacionQr, fileName+"."+qrcodeFormat);
      MatrixToImageWriter.writeToPath(bitMatrix, qrcodeFormat, file.toPath());

      return file.getAbsolutePath();
    }
    catch (Exception e) {
      throw new RuntimeException("No se pudo generar el QR");
    }
  }

  public static String leerQR(String path){
    try{
    LuminanceSource source;
    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));
    return new MultiFormatReader().decode(binaryBitmap).getText();
    } catch (Exception e){
      throw new RuntimeException("No se puedo leer el QR");
    }
  }
} */
