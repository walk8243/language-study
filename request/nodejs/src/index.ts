import fetch from 'node-fetch';

async function main() {
	try {
		const res = await fetch('https://www.walk8243.xyz');
		const text = await res.text();
		console.log(text);
	} catch (error) {
		console.error(error);
	}
}

main();
