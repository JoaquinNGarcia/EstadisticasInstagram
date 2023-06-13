package com.estadisticasInstagram.Graficos;

import javax.swing.*;
import java.awt.*;

public class GraficoTortas extends JPanel {
    private final String[] labels;
    private final int[] data;

    public GraficoTortas(String[] labels, int[] data) {
        this.labels = labels;
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        int total = 0;
        for (int value : data) {
            total += value;
        }

        int startAngle = 0;
        for (int i = 0; i < data.length; i++) {
            int arcAngle = (int) Math.round((double) data[i] / total * 360);
            g.setColor(getColor(i));
            g.fillArc(100, 100, 200, 200, startAngle, arcAngle);

            // Calcular el ángulo medio para colocar el texto en el centro de cada sección
            int midAngle = startAngle + arcAngle / 2;

            // Calcular las coordenadas x e y para colocar el texto
            int radius = 100;
            double angle = Math.toRadians(midAngle);
            int x = (int) (150 + radius * Math.cos(angle) * 0.8); // Ajustar el factor de escala
            int y = (int) (150 + radius * Math.sin(angle) * 0.8); // Ajustar el factor de escala

            // Dibujar el nombre y el porcentaje dentro del gráfico
            String label = labels[i];
            String percentage = String.format("%.1f%%", (double) data[i] / total * 100);
            Graphics2D g2d = (Graphics2D) g;
            if (label.equals("Video")) {
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
                g.setColor(Color.BLUE);
                g.drawString(label, 250, 300);
                g.drawString(percentage, 250, 300 + 15);
            }
            else
                g.setColor(Color.RED);
                if (label.equals("Audio")) {
                    g.setColor(Color.RED);
                    g.drawString(label, x + 50, y);
                    g.drawString(percentage, x + 50, y + 15);
                }
                else{
                    g.setColor(Color.GREEN);
                    g.drawString(label, x, y);
                    g.drawString(percentage, x, y + 15);
                }
            startAngle += arcAngle;
        }
    }

    private Color getColor(int index) {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};
        return colors[index % colors.length];
    }

    public static void createAndShowGUIPieGraphic(int[] data) {
        String[] titles = {"Video", "Imagen", "Audio"};
        JFrame frame = new JFrame("Grafico Torta");
        frame.setSize(400, 400);
        GraficoTortas pieChart = new GraficoTortas(titles, data);
        frame.add(pieChart);
        frame.setVisible(true);

    }
}
