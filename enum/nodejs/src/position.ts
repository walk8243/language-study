export const Position = {
	TOP: Symbol('Position.top'),
	BOTTOM: Symbol('Position.bottom'),
	LEFT: Symbol('Position.left'),
	RIGHT: Symbol('Position.right'),
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
		default:
			return '';
	}
}
