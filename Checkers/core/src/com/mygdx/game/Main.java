package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture board, selectedRegion;
	ArrayList<Path> pathways;
	RedPiece[] redPieces;
	BluePiece[] bluePieces;
	GameObject selectedPiece;
	int x = 15, rY = 1265, bY = 515;
	boolean isTouched = false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		board = new Texture("checkers-board.jpg");
		pathways = new ArrayList<Path>();
		selectedPiece = null;
		selectedRegion = new Texture("selected-piece.png");
		redPieces = new RedPiece[8];
		bluePieces = new BluePiece[8];

		for (int i = 0; i < redPieces.length; i++) {
			if (i != 0) {
				if (i % 2 == 1) {
					rY += 130;
					bY += 130;
				} else {
					rY -= 130;
					bY -= 130;
				}
			}
			redPieces[i] = new RedPiece(new Texture("red-piece.png"), "Red", x, rY);
			bluePieces[i] = new BluePiece(new Texture("blue-piece.png"), "Blue", x, bY);
			x += 138;
		}

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				if (!isTouched) {
					selectedPiece = GameLogic.getSelectedPiece(redPieces, bluePieces, x, 1900 - y);
					pathways = GameLogic.getPathways(selectedPiece, redPieces, bluePieces);

					if (selectedPiece != null)
						isTouched = true;
				}

				else {
					if (GameLogic.getPlayerTurn()) {
						for (BluePiece piece : bluePieces) {
							if (piece.equals(selectedPiece))
								GameLogic.movePiece(selectedPiece, pathways, x, 1900 - y);
						}
					}

					else {
						for (RedPiece piece : redPieces) {
							if (piece.equals(selectedPiece))
								GameLogic.movePiece(selectedPiece, pathways, x, 1900 - y);
						}
					}
					selectedPiece = null;
					isTouched = false;
				}
				return false;
			}
		});
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(board, 0, 500, 1100, 1000);

		if (isTouched) {
			batch.draw(selectedRegion, selectedPiece.getX() - 15, selectedPiece.getY() - 15, 135, 125);

			if (pathways.size() > 0) {
				for (Path path : pathways)
					batch.draw(path.getObject(), path.getX(), path.getY(), 135, 125);
			}
		}

		for (int i = 0; i < redPieces.length; i++) {
			batch.draw(redPieces[i].getObject(), redPieces[i].getX(), redPieces[i].getY(), 100, 100);
			batch.draw(bluePieces[i].getObject(), bluePieces[i].getX(), bluePieces[i].getY(), 100, 100);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		board.dispose();
	}
}
