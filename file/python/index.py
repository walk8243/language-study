print('********************************************')
with open('../README.md', 'r', 10000, 'utf-8') as f:
    read_data = f.read()
    print(read_data)
print('********************************************')

print('********************************************')
import json
with open('../sample.json', 'r', 10000, 'utf-8') as f:
    x = json.load(f)
    print(x)
print('********************************************')

print('********************************************')
import yaml
with open('../sample.yaml', 'r', 10000, 'utf-8') as f:
    x = yaml.safe_load(f)
    print(x)
print('********************************************')
