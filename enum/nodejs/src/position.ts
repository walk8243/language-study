export const Position = {
	TOP: 0,
	BOTTOM: 1,
	LEFT: 2,
	RIGHT: 3,
} as const;
type Position = (typeof Position)[keyof typeof Position];

export const toJapanese = (position: Position): string => {
	switch (position) {
		case Position.TOP:
			return '上';
		case Position.BOTTOM:
			return '下';
		case Position.LEFT:
			return '左';
		case Position.RIGHT:
			return '右';
	}
}
