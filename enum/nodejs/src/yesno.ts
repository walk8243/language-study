const yes = Symbol('yes');
const no = Symbol('no');
export const YesNo = {
	yes,
	no,
} as const;
type YesNo = typeof yes | typeof no;

export const toJapanese = (yesno: YesNo): string => {
	switch (yesno) {
		case yes:
			return 'はい';
		case no:
			return 'いいえ';
	}
}
