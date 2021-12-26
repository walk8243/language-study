fruits = { 'apple': 'りんご', 'banana': 'バナナ', 'orange': 'オレンジ' }

print('fruits:', fruits)
print('list(fruits):', list(fruits))
print('fruits.keys():', fruits.keys())
print('fruits.values():', fruits.values())
print('fruits.items():', fruits.items())

print('***************************')
for key, value in fruits.items():
    print('%s is %s' % (key, value))
print('***************************')

print('apple is', 'apple' in fruits)
print('grape is', 'grape' in fruits)
print('りんご is', 'りんご' in fruits)
print('[keys] apple is', 'apple' in fruits.keys())
print('[values] りんご is', 'りんご' in fruits.values())
