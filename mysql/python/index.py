import json
import pymysql.cursors

connection = pymysql.connect(host='mysql',
                             user='walk8243',
                             password='shLG425x',
                             database='study',
                             cursorclass=pymysql.cursors.DictCursor)

with open('./member.json', 'r', 10000, 'utf-8') as f:
    members = json.load(f)

    with connection:
        with connection.cursor() as cursor:
            sql = "INSERT INTO `member` (`id`, `name`) VALUES (%s, %s)"
            cursor.execute(sql, (members[0]['id'], members[0]['name']))
        
        connection.commit()

        with connection.cursor() as cursor:
            sql = "SELECT * FROM `member`"
            cursor.execute(sql)
            result = cursor.fetchall()
            print(result)
