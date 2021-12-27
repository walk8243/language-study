import concurrent.futures
import func

tpe = concurrent.futures.ThreadPoolExecutor(max_workers=3)
futures = []

for i in range(10):
    future = tpe.submit(func.sleep, 'sleep' + str(i+1), 3)
    futures.append(future)

print([ future.result() for future in futures ])

tpe.shutdown()
