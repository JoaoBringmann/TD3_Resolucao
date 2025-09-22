import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Logica {

    private static final int SAVE_INTERVAL = 500;

    public void floodFill(BufferedImage image, int startX, int startY, int newColor, boolean useStack) {
        int width = image.getWidth();
        int height = image.getHeight();

        int targetColor = image.getRGB(startX, startY);
        int targetRGB = targetColor & 0x00FFFFFF; // só RGB
        int newRGB = newColor & 0x00FFFFFF;

        if (targetRGB == newRGB) {
            System.out.println("Pixel inicial já tem a cor nova.");
            return;
        }

        Estrutura<Coordenada> estrutura = useStack ? new Pilha<>() : new Fila<>();
        estrutura.push(new Coordenada(startX, startY));

        boolean[][] visited = new boolean[width][height];

        while (!estrutura.isEmpty()) {
            Coordenada p = estrutura.pop();

            int x = p.x;
            int y = p.y;

            if (x < 0 || y < 0 || x >= width || y >= height) continue;
            if (visited[x][y]) continue;

            int currentColor = image.getRGB(x, y) & 0x00FFFFFF;

            if (currentColor != targetRGB) continue;

            // Marca como visitado antes de pintar para evitar loops infinitos
            visited[x][y] = true;
            image.setRGB(x, y, newColor);

            estrutura.push(new Coordenada(x + 1, y));
            estrutura.push(new Coordenada(x - 1, y));
            estrutura.push(new Coordenada(x, y + 1));
            estrutura.push(new Coordenada(x, y - 1));
        }
    }

    private void salvarImagemIntermediaria(BufferedImage image, int passo) {
        try {
            File output = new File("saida_passos/saida_" + passo + ".png");
            output.getParentFile().mkdirs(); // cria a pasta se não existir
            ImageIO.write(image, "png", output);
            System.out.println("Imagem salva no passo " + passo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
