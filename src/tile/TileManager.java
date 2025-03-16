package tile;

import Main.PanelSettings;
import Main.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager extends Tile {
    PanelSettings gp;
    public Tile[] tile;
     public int mapTileNum[][][];

    public TileManager(PanelSettings gp) {
        this.gp = gp;

        tile = new Tile[20];

        mapTileNum = new  int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/map 1.txt");

    }

    public void getTileImage() {
        setup(0, "sand tile", false);
        setup(1, "dungeon tile", true);
        setup(2, "water tile", false);
        setup(3, "water tile 2", false);
        setup(4, "grass tile", false);
        setup(5, "grass rock tile", true);
        setup(6, "rock tile", true);
        setup(7, "grass", false);
        setup(8, "grass_flowers", false);
        setup(9, "path", false);
        setup(10, "path_t", false);
        setup(11, "path_b", false);
        setup(12, "path_l", false);
        setup(13, "path_r", false);
        setup(14, "path_tl_corner", false);
        setup(15, "path_tr_corner", false);
        setup(16, "path_bl_corner", false);
        setup(17, "path_br_corner", false);


    }

    public void setup(int index, String imageName, boolean collision) {
        Tools tool = new Tools();
        try{
            tile[index]=new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png")));
            tile[index].image = tool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap(String map) {
            try {

                InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(map));
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0, row = 0;
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    String line = br.readLine();

                    while (col < gp.maxWorldCol) {
                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[0][col][row] = num;
                        col++;
                    }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    public void update(){
        getTileImage();

        tileSpriteCounter++;//sprite updater
        if (tileSpriteCounter > 60){
            System.out.println("updated");
            if (tileSpriteNum == 1){
                tileSpriteNum = 2;
            }
            else if (tileSpriteNum == 2){
                tileSpriteNum = 1;
            }
            tileSpriteCounter = 0;
        }
    }



    public void draw(Graphics2D g2) {
            for (int worldcol = 0; worldcol < gp.maxWorldCol; worldcol++) {
                for (int worldrow = 0; worldrow < gp.maxWorldRow; worldrow++) {
                    int tileNum = mapTileNum[gp.currentMap][worldcol][worldrow];
                    int screenx = (int) ((worldcol * gp.tileSize) - gp.player.worldx + gp.player.screenx);
                    int screeny = (int) ((worldrow * gp.tileSize) - gp.player.worldy + gp.player.screeny);
                    if(((worldcol + gp.tileSize) * gp.tileSize) > gp.player.worldx - gp.player.screenx//only renders tile in player screen
                        && ((worldcol - gp.tileSize) * gp.tileSize) < gp.player.worldx + gp.player.screenx
                        && ((worldrow + gp.tileSize) * gp.tileSize) > gp.player.worldy - gp.player.screeny
                        && ((worldrow - gp.tileSize) * gp.tileSize) < gp.player.worldy + gp.player.screeny){
                        g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
                    }



            }
        }
    }

}
