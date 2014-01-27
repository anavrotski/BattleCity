package interfaces;
import java.awt.Graphics;
import java.awt.Point;

public interface Movable extends Bounds
	{
	public Point getCurrentCoords();
	public boolean checkBoardBounds();
	public void updateLocation();
	public void draw(Graphics g);
	public void dispose();
	public boolean checkCollision(int x, int y);
	}
