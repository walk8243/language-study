import * as position from './position';
import * as yesno from './yesno';

const { Position } = position;
const { YesNo } = yesno;

(() => {
	console.log('Position:', position.toJapanese(Position.TOP));
	console.log('YesNo:', yesno.toJapanese(YesNo.yes));
})();
