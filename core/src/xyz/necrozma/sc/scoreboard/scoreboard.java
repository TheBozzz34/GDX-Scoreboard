package xyz.necrozma.sc.scoreboard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import xyz.necrozma.sc.communication.WebsocketServer;
import xyz.necrozma.sc.types.GameState;

public class scoreboard extends ApplicationAdapter {

	private static final Logger logger = LogManager.getLogger(scoreboard.class);
	SpriteBatch batch;
	BitmapFont font;
	OrthographicCamera camera;
	Texture background;

	WebsocketServer server;


	@Override
	public void create() {
		Configurator.initialize(new DefaultConfiguration());

		try {
			server = new WebsocketServer(80);
			server.start();
		} catch (IOException e) {
			logger.error("Failed to start websocket server.", e);
			throw new RuntimeException(e);
		}

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

		logger.info("Scoreboard GUI has started.");
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
		font.draw(batch, "Team A: " + 1, 20, Gdx.graphics.getHeight() - 20);
		font.draw(batch, "Team B: " + 2, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 20);
		batch.end();
	}

	private void handleInput() {
	}

	private void update() {
		// You can add any additional update logic here
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		background.dispose();

        server.stop();

		System.exit(0);
    }
}
