import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File input = new File("src/entrada.png");
            BufferedImage image = ImageIO.read(input);

            int startX = 1;
            int startY = 2;

            int newColor = 0xFF0000FF;

            int pixelInicial = image.getRGB(startX, startY) & 0x00FFFFFF;
            int whiteRGB = 0x00FFFFFF;

            if (pixelInicial != whiteRGB) {
                System.out.println("ATENÇÃO: O pixel inicial não é branco puro!");
            }

            boolean usarPilha = true;

            Logica logica = new Logica();
            logica.floodFill(image, startX, startY, newColor, usarPilha);

            File output = new File("saida.png");
            ImageIO.write(image, "png", output);

            System.out.println("Imagem processada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
