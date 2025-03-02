package tile;

import Main.PanelSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager extends Tile {
    PanelSettings gp;
    Tile[] tiles;
    int mapTileNum[] [];

    public TileManager(PanelSettings gp) {
        this.gp = gp;

        tiles = new Tile[10];

        mapTileNum = new  int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();

    }

    public void getTileImage() {

        try {

            tiles[0] = new Tile();
                tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/sand tile.png")));
            tiles[1] = new Tile();
                tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/dungeon tile.png")));
            tiles[2] = new Tile();
            if (tileSpriteNum == 1) {
                tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/water tile.png")));
            }
            else if (tileSpriteNum == 2) {
                tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/water tile 2.png")));
            }
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/rock tile.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/grass tile.png")));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/grass rock tile.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
            try {

                InputStream is = Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/map 1.txt"));
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0, row = 0;
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    String line = br.readLine();

                    while (col < gp.maxWorldCol) {
                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
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
                    int tileNum = mapTileNum[worldcol][worldrow];
                    int screenx = (worldcol * gp.tileSize) - gp.player.worldx + gp.player.screenx;
                    int screeny = (worldrow * gp.tileSize) - gp.player.worldy + gp.player.screeny;
                    g2.drawImage(tiles[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);


            }
        }
    }

}
