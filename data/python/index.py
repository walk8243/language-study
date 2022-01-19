import csv
from itertools import groupby

with open('../ohtani.csv') as f:
    reader = csv.DictReader(f)
    l = [row for row in reader]

    m = list(filter(lambda x: x['game_date'] == '2021-08-24', l))
    n = list(map(lambda x: { 'events': x['events'], 'balls': x['balls'], 'strikes': x['strikes'], 'at_bat_number': x['at_bat_number'], 'pitch_number': x['pitch_number'], 'pitch_name': x['pitch_name'] }, m))
    n.sort(key=lambda x: (x['at_bat_number'], x['pitch_number']))
    for key, n in groupby(n, key=lambda x: x['at_bat_number']):
        print(list(n))
