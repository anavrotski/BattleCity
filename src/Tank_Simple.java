import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank_Simple extends Tank {

	Tank_Simple(Game game) {
		super(game);
		try {
			img = ImageIO.read(new File("src\\res\\tank64.png"));
		} catch (IOException e) {
		}
		// TODO Auto-generated constructor stub

		// start position
		getCurrentCoords().setLocation(Game.GRID_SIZE * Game.COLUMNS / 2,
				Game.GRID_SIZE * (Game.ROWS - 1));
	}
}
