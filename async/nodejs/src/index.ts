import { sleep } from './func';

const requests: Request[] = [
	{ name: 'sleep1', seconds: 5 },
	{ name: 'sleep2', seconds: 1 },
	{ name: 'sleep3', seconds: 3 },
];

exec(requests);

function exec(requests: Request[]) {
	doParallel(requests);
	setInterval(() => {
		doParallel(requests);
	}, 10 * 1000);
}

async function doParallel(requests: Request[]) {
	const results = await Promise.all(
		requests.map((request) => sleep(request.name, request.seconds))
	);

	console.log(results);
}

type Request = {
	name: string,
	seconds: number,
};
