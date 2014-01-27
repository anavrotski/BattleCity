public enum Direction
{
	LEFT, UP, RIGHT, DOWN, DEFAULT;

	public static Direction getDir(int keyCode)
	{
		if (keyCode < 37 || keyCode > 40)
		{
			keyCode = 41;
		}
		return Direction.values()[keyCode - 37];
	}
}
