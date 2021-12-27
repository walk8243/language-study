import time

def sleep(name: str, seconds: int):
    print('非同期処理の開始 name: %s seconds: %d' % (name, seconds))
    time.sleep(seconds)
    print('非同期処理の終了 name: %s seconds: %d' % (name, seconds))
    return name
