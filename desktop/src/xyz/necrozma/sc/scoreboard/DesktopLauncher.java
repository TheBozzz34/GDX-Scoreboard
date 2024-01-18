package xyz.necrozma.sc.scoreboard;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import xyz.necrozma.sc.license.methods.Helpers;
import xyz.necrozma.sc.license.methods.Key;
import xyz.necrozma.sc.license.models.ActivateModel;
import xyz.necrozma.sc.license.models.LicenseKey;

import xyz.necrozma.sc.types.GameState;


public class DesktopLauncher {

	private static GameState gameState = new GameState();

	public static void main (String[] arg) {

		Logger logger = LogManager.getLogger(DesktopLauncher.class);

		ArgumentParser parser = ArgumentParsers.newFor("GDX Scoreboard").build()
				.defaultHelp(true)
				.description("Project a web controlled scoreboard.");

		parser.addArgument("-k", "--key")
				.help("License key to use.");

		if (arg.length == 0) {
			parser.printHelp();
			System.exit(1);
		}

		String licenseKey = null;

		try {
			licenseKey = parser.parseArgs(arg).getString("key");
		} catch (Exception e) {
			logger.error("Failed to parse arguments.", e);
			System.exit(1);
		}

		String RSAPubKey = "<RSAKeyValue><Modulus>wm+nVcq65oBYMLV6Uifzbet/Mdr+k3j280MwzuHS8NSWoio51giP94yg6dPhNO35ivpZ6aImuoRdOkJX9Ut9+Z3DTIa5GflSbXbNdBtddd6N10mCMOb/3VwxrU9ruIbNtcJPFI6cigR83MkA3QzFSblZJJACYRyGWFBPrgTdblGOb9Lj34VAa5m8ePmHvFGfaxAzG2pSBc85EKx+5E/0sMTNPKQGofkGwODduu47j6/M9ZdmFdzvY5ZS1eDDQa1zqk9x+K8z2Bq0X9885/J3B0wa55gnojlnYchLCDWTEcgc4AC3gVV+/fVKiNJ19dgUU4E49S6Nvp3e7Z0OZwb9cw==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
		String auth = "WyI3MTk1MDA4OSIsIjNYV1FiYjE2L0FMM2kwaUNaaHhmN3o1YktDbVBOQm0vL1RtOWtWR3kiXQ==";

		LicenseKey license = Key.Activate(auth, RSAPubKey,
				new ActivateModel(23590,
						licenseKey,
						Helpers.GetMachineCode()));

		if (license == null || !Helpers.IsOnRightMachine(license)) {
			logger.error("License key is not valid for this machine.");
			System.exit(1);
		} else { logger.info("License key is valid."); }


		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("GDX Scoreboard");
		new Lwjgl3Application(new scoreboard(), config);
	}
}
