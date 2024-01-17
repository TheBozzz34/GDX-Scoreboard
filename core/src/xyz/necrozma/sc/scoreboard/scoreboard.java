package xyz.necrozma.sc.scoreboard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class scoreboard extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	OrthographicCamera camera;
	Texture background;

	int teamAScore = 0;
	int teamBScore = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);
		background = new Texture("background.jpg"); // Replace "background.jpg" with your background image file

		// Use FreeTypeFontGenerator to generate a custom font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("CursedTimerUlil-Aznm.ttf")); // Replace "your_font.ttf" with your font file
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32; // Set the font size as needed
		font = generator.generateFont(parameter);
		generator.dispose(); // Dispose the generator to free resources
	}

	@Override
	public void render() {
		handleInput();
		update();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font.draw(batch, "Team A: " + teamAScore, 20, Gdx.graphics.getHeight() - 20);
		font.draw(batch, "Team B: " + teamBScore, Gdx.graphics.getWidth() - 120, Gdx.graphics.getHeight() - 20);
		batch.end();
	}

	private void handleInput() {
		// You can add touch/click handling logic if needed
	}

	private void update() {
		// You can add any additional update logic here
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		background.dispose();
	}
}
