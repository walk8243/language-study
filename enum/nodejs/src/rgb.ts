export const RGB = {
	RED: 1,
	GREEN: 2,
	BLUE: 4,
} as const;
type RGB = (typeof RGB)[keyof typeof RGB];

export const toJapanese = (rgb: RGB): string => {
	switch (rgb) {
		case RGB.RED:
			return '赤';
		case RGB.GREEN:
			return '緑';
		case RGB.BLUE:
			return '青';
	}
}
