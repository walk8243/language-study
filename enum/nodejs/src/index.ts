import { Position, toJapanese } from './position';

(() => {
	console.log('equal:', Position.TOP === Position.TOP, Position.TOP === Position.BOTTOM);
	console.log('toJapanese:', toJapanese(Position.TOP));
})();
