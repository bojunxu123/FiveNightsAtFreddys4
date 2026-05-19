package org.fivenightsatfreddys4;

import java.util.*;

public enum Position {
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

	private final String label;
	private final int row;
	private final int col;
	private final EnumMap<Direction, Position> exits = new EnumMap<>(Direction.class);

	static {
		// Bidirectional links follow the supplied layout.
		link(LIVING_ROOM_LEFT, Direction.RIGHT, LIVING_ROOM_CENTER);
		link(LIVING_ROOM_CENTER, Direction.RIGHT, LIVING_ROOM_RIGHT);
		link(LIVING_ROOM_RIGHT, Direction.RIGHT, KITCHEN);

		link(LIVING_ROOM_LEFT, Direction.DOWN, LEFT_HALLWAY);
		link(LIVING_ROOM_CENTER, Direction.DOWN, CLOSET);
		link(LIVING_ROOM_RIGHT, Direction.DOWN, RIGHT_HALLWAY);

		link(LEFT_HALLWAY, Direction.RIGHT, CLOSET);
		link(CLOSET, Direction.RIGHT, RIGHT_HALLWAY);

		link(LEFT_HALLWAY, Direction.DOWN, LEFT_DOOR);
		link(RIGHT_HALLWAY, Direction.DOWN, RIGHT_DOOR);
		link(LEFT_DOOR, Direction.RIGHT, BEDROOM);
		link(BEDROOM, Direction.RIGHT, RIGHT_DOOR);

		link(CLOSET, Direction.DOWN, BEDROOM);
		link(BEDROOM, Direction.DOWN, BED);
	}

	Position(String label, int row, int col) {
		this.label = label;
		this.row = row;
		this.col = col;
	}

	public String getLabel() {
		return label;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Optional<Position> move(Direction direction) {
		return Optional.ofNullable(exits.get(direction));
	}

	public boolean canMove(Direction direction) {
		return exits.containsKey(direction);
	}

	public boolean isAdjacent(Position other) {
		return exits.containsValue(other);
	}

	public Set<Position> getNeighbors() {
		return Collections.unmodifiableSet(new LinkedHashSet<>(exits.values()));
	}

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

	private static void link(Position from, Direction direction, Position to) {
		from.exits.put(direction, to);
		to.exits.put(direction.opposite(), from);
	}

	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT;

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
