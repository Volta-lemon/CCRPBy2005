# -*- coding: utf-8 -*-
import pymysql
import csv

if __name__ == '__main__':
    # 建立与 MySQL 数据库的连接
    db = pymysql.connect(host='101.200.148.39', port=3306, user='ccrp', password='SfDHMXPpEzdcx7p4', database='ccrp')

    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()

    # 读取 CSV 文件并将数据插入到 MySQL 数据库中
    with open('input.csv', 'r') as csvfile:
        csvreader = csv.reader(csvfile)
        next(csvreader) # 跳过标题行
        for row in csvreader:
            name = row[1]
            relicTime = row[2]
            type = row[3]
            metiral = row[4]
            author = row[5]
            descriptoin = row[6]
            imageUrl = row[7]
            moreUrl = row[8]
            # 使用 INSERT INTO 语句将数据插入到 MySQL 数据库中
            sql = "INSERT INTO artifact (artifactName, relicTime, author, description, imageUrl, moreUrl) VALUES ( %s, %s, %s, %s, %s, %s)"
            values = (name, relicTime, author, descriptoin, imageUrl, moreUrl)
            cursor.execute(sql, values)
            db.commit()

    # 关闭游标和数据库连接
    cursor.close()
    db.close()



