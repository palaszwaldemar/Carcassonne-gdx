package com.mygdx.carcassonne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TileFactory {

    Queue<Tile> getListOfTile() {
        LinkedList<Tile> tiles = new LinkedList<>();
            FileHandle fileHandle = Gdx.files.internal("TilesInfo.csv");
            String text = fileHandle.readString("UTF-8");
            Scanner reader = new Scanner(text);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Tile tile = parseCsvLineToTile(line);
                tiles.add(tile);
            }
        Collections.shuffle(tiles);
        System.out.println(tiles);
        return tiles;
    }

    private Tile parseCsvLineToTile(String line) {
        String[] tab = line.split(";");
        Texture image = new Texture(Gdx.files.internal("/tiles/PNG/Base_Game_C2_Tile_" + tab[0] + ".png"));
        Tile tile = new Tile(0, 0, image);
        String directionRoadString = tab[1];
        String directionCityString = tab[2];
        char[] directionsRoadChar = directionRoadString.toCharArray();
        char[] directionsCityChar = directionCityString.toCharArray();
        for (char direction : directionsRoadChar) {
            tile.setRoad(direction);
        }
        for (char direction : directionsCityChar) {
            tile.setCity(direction);
        }
        return tile;
    }
}
