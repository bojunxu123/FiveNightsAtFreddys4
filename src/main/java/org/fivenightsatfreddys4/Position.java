package org.fivenightsatfreddys4;

import java.util.*;

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
	// Each entry is: CONSTANT_NAME("display label", row, col)
	LIVING_ROOM_LEFT("living room left", 0, 0),
	LIVING_ROOM_CENTER("living room center", 0, 1),
	LIVING_ROOM_RIGHT("living room right", 0, 2),
	KITCHEN("kitchen", 0, 3),
	LEFT_HALLWAY("left hallway", 1, 0),
	CLOSET("closet", 1, 1),
	RIGHT_HALLWAY("right hallway", 1, 2),
	LEFT_DOOR("left door", 2, 0),
	BEDROOM("bedroom", 2, 1),
	RIGHT_DOOR("right door", 2, 2),
	BED("bed", 3, 1);

	private final String label;   // Human-readable name shown in the UI
	private final int row;        // Vertical position on the grid
	private final int col;        // Horizontal position on the grid

	// Constructor — called automatically for each enum constant above.
	Position(String label, int row, int col) {
		this.label = label;
		this.row = row;
		this.col = col;
	}

	/** Returns the display name of this room (e.g. "left hallway"). */
	public String getLabel() {
		return label;
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
