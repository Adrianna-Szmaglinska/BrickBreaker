import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

    public int map[][];
    public int bricksWidth;
    public int bricksHeight;

    public MapGenerator(int row , int col){
        map = new int[row][col];
         for (int[] map1 : map) {
             for (int j = 0; j < map[0].length; j++) {
                 map1[j] = 1;
             }
         }
        bricksWidth = 540/col;
        bricksHeight = 150/row;
    }
    public void draw(Graphics2D g) {
        int paddingX = 5; // Adjust this value for the desired horizontal padding
        int paddingY = 5; // Adjust this value for the desired vertical padding
                
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    int x = j * (bricksWidth + paddingX) + 80;
                    int y = i * (bricksHeight + paddingY) + 50;
    
                    g.setColor(Color.lightGray);
                    g.fillRect(x, y, bricksWidth, bricksHeight);
    
                    g.setStroke(new BasicStroke(1));
                    //g.setColor(Color.black);
                    g.drawRect(x, y, bricksWidth, bricksHeight);

                }
            }

        }
    }
    public void setBricksValue(int value,int row,int col)
    {
        map[row][col] = value;

    }
}