package Funciones;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

public class creacionDeQR {
/*
  public static void generarQR(String data, String Path) {
    try {
      Integer anchoQR = 100, altoQR = 100;

      QRCodeWriter writer = new QRCodeWriter();
      BitMatrix bitMatrix = writer.encode(new String(data.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), BarcodeFormat.QR_CODE, anchoQR, altoQR);
      MatrixToImageWriter.writeToFile(bitMatrix, Path.substring(Path.lastIndexOf(".")+1), new File(Path));
    } catch (Exception e) {
      throw new RuntimeException("No se pudo generar el QR");
    }
  }
*/
}
