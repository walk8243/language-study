export function sleep(name: string, seconds: number) {
	return new Promise((resolve, reject) => {
		console.log(`非同期処理の開始 name: ${name} seconds: ${seconds}`);
		setTimeout(() => {
			console.log(`非同期処理の終了 name: ${name} seconds: ${seconds}`);
			resolve(name);
		}, seconds * 1000);
	});
}
