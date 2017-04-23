package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by My-Laptop on 21/03/2017.
 */

public class GameLogic {

    private static boolean playerTurn = true;

    public static boolean getPlayerTurn() { return GameLogic.playerTurn; }

    private static boolean check(GameObject object, int x, int y) {
        return (x >= object.getX() - 15
                && x <= object.getX() + 135
                && y >= object.getY() - 15
                && y <= object.getY() + 135);
    }

    public static GameObject getSelectedPiece(RedPiece[] redPieces, BluePiece[] bluePieces, int x, int y) {
        GameObject selected = null;

        if (!playerTurn) {
            for (RedPiece piece : redPieces) {
                if (check(piece, x, y)) {
                    selected = piece;
                }
            }
        }

        else {
            for (BluePiece piece : bluePieces) {
                if (check(piece, x, y)) {
                    selected = piece;
                }
            }
        }
        return selected;
    }

    public static ArrayList<Path> getPathways(GameObject piece, RedPiece[] redPieces, BluePiece[] bluePieces) {
        ArrayList<Path> pathways = new ArrayList<Path>();

        if (piece != null) {
            int lX = piece.getX() - 138, rX = piece.getX() + 138, y = piece.getY(), jY = 0;
            boolean left = true, right = true, bOR = false, rOB = false, lNoJump = false, rNoJump = false;

            if (piece.getObjectType().equals("Blue"))
                y += 125;
            else
                y -= 125;

            for (int i = 0; i < redPieces.length; i++) {

                if (check(redPieces[i], lX, y) || check(bluePieces[i], lX, y)) {
                    if (piece.getObjectType().equals("Blue") && check(redPieces[i], lX, y))
                        bOR = true;
                    else if (piece.getObjectType().equals("Red") && check(bluePieces[i], lX, y))
                        rOB = true;
                    else if ((piece.getObjectType().equals("Blue") && check(bluePieces[i], lX, y))
                            || (piece.getObjectType().equals("Red") && check(redPieces[i], lX, y)))
                        left = false;
                }
            }

            if (left && !bOR && !rOB)
                lNoJump = true;

            for (int i = 0; i < redPieces.length; i++) {

                if (check(redPieces[i], rX, y) || check(bluePieces[i], rX, y)) {
                    if (piece.getObjectType().equals("Blue") && check(redPieces[i], rX, y))
                        bOR = true;
                    else if (piece.getObjectType().equals("Red") && check(bluePieces[i], rX, y))
                        rOB = true;
                    else if ((piece.getObjectType().equals("Blue") && check(bluePieces[i], rX, y))
                            || (piece.getObjectType().equals("Red") && check(redPieces[i], rX, y)))
                        right = false;
                }
            }

            if (right && !bOR && !rOB)
                rNoJump = true;

            if (bOR)
                jY = y + 125;
            else if (rOB)
                jY = y - 125;

            for (int i = 0; i < redPieces.length; i++) {
                if (left && (bOR || rOB)) {
                    if (check(redPieces[i], lX - 138, jY) || check(bluePieces[i], lX - 138, jY))
                        left = false;
                }

                if (right  && (bOR || rOB)) {
                    if (check(redPieces[i], rX + 138, jY) || check(bluePieces[i], rX + 138, jY))
                        right = false;
                }
            }

            if (bOR || rOB) {
                if (left && !lNoJump)
                    lX -= 138;
                if (right && !rNoJump)
                    rX += 138;
            }

            if (left) {
                if (lNoJump)
                    pathways.add(new Path(new Texture("path.png"), "Path", lX, y));
                else
                    pathways.add(new Path(new Texture("path.png"), "Path", lX, jY));
            }
            if (right) {
                if (rNoJump)
                    pathways.add(new Path(new Texture("path.png"), "Path", rX, y));
                else
                    pathways.add(new Path(new Texture("path.png"), "Path", rX, jY));
            }
        }
        return pathways;
    }

    public static void movePiece(GameObject piece, ArrayList<Path> pathways, int x, int y) {

        for (Path path : pathways) {
            if (check(path, x, y)) {
                piece.relocate(path.getX(), path.getY());

                if (playerTurn)
                    playerTurn = false;
                else
                    playerTurn = true;
            }
        }
    }
}
