package org.fivenightsatfreddys4;

/**
 * Represents every named location in the game map.
 *
 * An enum is like a fixed list of constants — here, each constant IS a room.
 * Each room knows its label (display name), its grid coordinates, and which
 * other rooms it connects to (its "exits").
 *
 * The map looks roughly like this (row, col):
 *
 *   [LIVING_ROOM_LEFT] -- [LIVING_ROOM_CENTER] -- [LIVING_ROOM_RIGHT] -- [KITCHEN]
 *          |                      |                       |
 *    [LEFT_HALLWAY]  --------  [CLOSET]  ----------  [RIGHT_HALLWAY]
 *          |                      |                       |
 *      [LEFT_DOOR]  --------  [BEDROOM]  ----------  [RIGHT_DOOR]
 *                                 |
 *                               [BED]
 */
public enum Position {
	LIVING_ROOM_LEFT(0, 0),
	LIVING_ROOM_CENTER(0, 1),
	LIVING_ROOM_RIGHT(0, 2),
	KITCHEN(0, 3),
	LEFT_HALLWAY( 1, 0),
	CLOSET( 1, 1),
	RIGHT_HALLWAY( 1, 2),
	LEFT_DOOR( 2, 0),
	BEDROOM(2, 1),
	RIGHT_DOOR(2, 2),
	BED( 3, 1);

	private final int row;        // Vertical position on the grid
	private final int col;        // Horizontal position on the grid

	Position( int row, int col) {
		this.row = row;
		this.col = col;
	}

	/** Returns the row (vertical index) of this room on the grid. */
	public int getRow() {
		return row;
	}

	/** Returns the column (horizontal index) of this room on the grid. */
	public int getCol() {
		return col;
	}





}
