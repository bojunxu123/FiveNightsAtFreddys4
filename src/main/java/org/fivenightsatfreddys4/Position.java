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

	// Each room stores a map of direction → neighbouring room.
	// EnumMap is just a fast Map whose keys are enum values (Direction).
	private final EnumMap<Direction, Position> exits = new EnumMap<>(Direction.class);

	/**
	 * The static block runs once when the class is first loaded.
	 * It calls link() to wire up every connection between rooms.
	 * link() is bidirectional, so linking A→RIGHT→B also sets B→LEFT→A.
	 */
	static {
		// Top row (living rooms + kitchen)
		link(LIVING_ROOM_LEFT, Direction.RIGHT, LIVING_ROOM_CENTER);
		link(LIVING_ROOM_CENTER, Direction.RIGHT, LIVING_ROOM_RIGHT);
		link(LIVING_ROOM_RIGHT, Direction.RIGHT, KITCHEN);

		// Living rooms down into hallways / closet
		link(LIVING_ROOM_LEFT, Direction.DOWN, LEFT_HALLWAY);
		link(LIVING_ROOM_CENTER, Direction.DOWN, CLOSET);
		link(LIVING_ROOM_RIGHT, Direction.DOWN, RIGHT_HALLWAY);

		// Middle row (hallways + closet)
		link(LEFT_HALLWAY, Direction.RIGHT, CLOSET);
		link(CLOSET, Direction.RIGHT, RIGHT_HALLWAY);

		// Hallways / closet down into doors / bedroom
		link(LEFT_HALLWAY, Direction.DOWN, LEFT_DOOR);
		link(RIGHT_HALLWAY, Direction.DOWN, RIGHT_DOOR);
		link(LEFT_DOOR, Direction.RIGHT, BEDROOM);
		link(BEDROOM, Direction.RIGHT, RIGHT_DOOR);

		// Closet and bedroom vertical connections
		link(CLOSET, Direction.DOWN, BEDROOM);
		link(BEDROOM, Direction.DOWN, BED);
	}

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

	/**
	 * Tries to move in the given direction from this room.
	 * Returns an Optional — it contains the neighbouring room if one exists
	 * in that direction, or is empty if the move is not possible.
	 *
	 * Example: BEDROOM.move(Direction.DOWN) → Optional.of(BED)
	 *          BEDROOM.move(Direction.UP)   → Optional.of(CLOSET)
	 *          BED.move(Direction.LEFT)     → Optional.empty()
	 */
	public Optional<Position> move(Direction direction) {
		return Optional.ofNullable(exits.get(direction));
	}

	/**
	 * Returns true if there is an exit in the given direction from this room.
	 * Useful for checking movement validity before calling move().
	 */
	public boolean canMove(Direction direction) {
		return exits.containsKey(direction);
	}

	/**
	 * Returns true if the other room is directly connected to this one
	 * (i.e. reachable in a single step, in any direction).
	 */
	public boolean isAdjacent(Position other) {
		return exits.containsValue(other);
	}

	/**
	 * Returns all rooms directly reachable from this one, in the order
	 * they were linked. The set is unmodifiable — you can read it but not change it.
	 */
	public Set<Position> getNeighbors() {
		return Collections.unmodifiableSet(new LinkedHashSet<>(exits.values()));
	}

	/**
	 * Looks up a Position by its display label (case-insensitive, trims whitespace).
	 * Returns an Optional — present if a match was found, empty otherwise.
	 *
	 * Example: Position.fromLabel("Bedroom") → Optional.of(BEDROOM)
	 *          Position.fromLabel("garage")  → Optional.empty()
	 */
	public static Optional<Position> fromLabel(String input) {
		if (input == null || input.isBlank()) {
			return Optional.empty();
		}

		String normalized = input.trim().toLowerCase(Locale.ROOT);
		for (Position position : values()) {
			if (position.label.equals(normalized)) {
				return Optional.of(position);
			}
		}

		return Optional.empty();
	}

	/**
	 * Creates a two-way connection between two rooms along the given direction.
	 * Called only during the static initializer above.
	 *
	 * Example: link(A, RIGHT, B) sets A's RIGHT exit to B
	 *                            and B's LEFT  exit to A.
	 */
	private static void link(Position from, Direction direction, Position to) {
		from.exits.put(direction, to);
		to.exits.put(direction.opposite(), from);
	}

	/**
	 * The four cardinal directions used to navigate between rooms.
	 * Each direction knows its own opposite, used by link() to set both ends
	 * of a connection at once.
	 */
	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT;

		/** Returns the direction directly opposite to this one. */
		public Direction opposite() {
			return switch (this) {
				case UP -> DOWN;
				case DOWN -> UP;
				case LEFT -> RIGHT;
				case RIGHT -> LEFT;
			};
		}
	}

}
