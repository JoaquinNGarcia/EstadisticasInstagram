import javax.swing.*;
import java.awt.*;

public class LineaDeTiempo extends JPanel {

    private int[] valoresX;
    private int[] valoresY;
    private String[] titulosX;
    private String[] titulosY;

    public LineaDeTiempo(int[] valoresX, int[] valoresY, String[] titulosX, String[] titulosY) {
        this.valoresX = valoresX;
        this.valoresY = valoresY;
        this.titulosX = titulosX;
        this.titulosY = titulosY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        // Establecer el color y el grosor de la línea
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));

        // Dibujar ejes X e Y
        g2d.drawLine(50, height - 50, width - 50, height - 50);
        g2d.drawLine(50, 50, 50, height - 50);

        // Calcular los valores máximo y mínimo en los ejes X e Y
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int valorX : valoresX) {
            if (valorX > maxX) {
                maxX = valorX;
            }
            if (valorX < minX) {
                minX = valorX;
            }
        }
        for (int valorY : valoresY) {
            if (valorY > maxY) {
                maxY = valorY;
            }
            if (valorY < minY) {
                minY = valorY;
            }
        }

        // Calcular la escala de conversión de valores a píxeles
        double escalaX = (double) (width - 100) / (maxX - minX);
        double escalaY = (double) (height - 100) / (maxY - minY);

        // Dibujar las líneas conectando los puntos
        int numValores = valoresX.length;
        int espaciadoX = (width - 100) / (numValores - 1);

        for (int i = 0; i < numValores - 1; i++) {
            int x1 = 50 + (int) ((valoresX[i] - minX) * escalaX);
            int y1 = height - 50 - (int) ((valoresY[i] - minY) * escalaY);

            int x2 = 50 + (int) ((valoresX[i + 1] - minX) * escalaX);
            int y2 = height - 50 - (int) ((valoresY[i + 1] - minY) * escalaY);

            g2d.drawLine(x1, y1, x2, y2);
        }

        // Dibujar los títulos de los ejes
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));

        // Título del eje X
        String tituloX = "Meses";
        int tituloXWidth = g2d.getFontMetrics().stringWidth(tituloX);
        int tituloXHeight = g2d.getFontMetrics().getHeight();
        g2d.drawString(tituloX, width / 2 - tituloXWidth / 2, height - 10);

        // Título del eje Y
        String tituloY = "Valores";
        int tituloYWidth = g2d.getFontMetrics().stringWidth(tituloY);
        int tituloYHeight = g2d.getFontMetrics().getHeight();
        g2d.drawString(tituloY, 15, height / 2 + tituloYWidth / 2);

        // Dibujar los títulos de los valores en el eje X
        for (int i = 0; i < numValores; i++) {
            String titulo = titulosX[i];
            int tituloWidth = g2d.getFontMetrics().stringWidth(titulo);
            int tituloHeight = g2d.getFontMetrics().getHeight();
            int x = 50 + i * espaciadoX - tituloWidth / 2;
            int y = height - 40 + tituloHeight;
            g2d.drawString(titulo, x, y);
        }

        // Dibujar los títulos de los valores en el eje Y
        for (int i = 0; i < maxY; i += maxY / 5) {
            String titulo = String.valueOf(i);
            int tituloWidth = g2d.getFontMetrics().stringWidth(titulo);
            int tituloHeight = g2d.getFontMetrics().getHeight();
            int x = 30 - tituloWidth;
            int y = height - 50 - (int) (i * escalaY) + tituloHeight / 2;
            g2d.drawString(titulo, x, y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gráfico de Líneas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        int[] valoresX = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] valoresY = {20, 40, 30, 50, 40, 60, 50, 70, 60, 80, 70, 90};
        String[] titulosX = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
        String[] titulosY = {"0", "10", "20", "30", "40", "50", "60", "70", "80", "90"};

        LineaDeTiempo grafico = new LineaDeTiempo(valoresX, valoresY, titulosX, titulosY);
        frame.add(grafico);

        frame.setVisible(true);
    }
}
