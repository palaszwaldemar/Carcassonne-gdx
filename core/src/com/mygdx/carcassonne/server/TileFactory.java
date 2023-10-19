package com.mygdx.carcassonne.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TileFactory {
    Queue<Tile> getListOfTile() {
        LinkedList<Tile> tiles = new LinkedList<>();
        FileHandle fileHandle = Gdx.files.internal("TilesInfo.csv"); // TODO: 27.04.2023 wyeliminować wczytywanie pliku
        String text = fileHandle.readString("UTF-8");
        Scanner reader = new Scanner(text);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            Tile tile = parseCsvLineToTile(line);
            tiles.add(tile);
        }
        Tile tile = tiles.remove();
        Collections.shuffle(tiles);
        tiles.addFirst(tile);
        return tiles;
    }

    private Tile parseCsvLineToTile(String line) {
        String[] tab = line.split(";");
        String directionRoadString = tab[1];
        String directionCityString = tab[2];
        String name = tab[0];
        char[] directionsRoadChar = directionRoadString.toCharArray();
        char[] directionsCityChar = directionCityString.toCharArray();
        return new Tile(directionsRoadChar, directionsCityChar, name);
    }
}
