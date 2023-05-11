import csv

# 定义一个函数，将每行数据提取出需要的信息并以元组的形式返回
def extract_info(row):
    name = row[1]
    type_ = row[3]
    metiral = row[4]
    belong = row[9]
    locate = row[10]
    return (name, type_, metiral, belong, locate)

if __name__ == '__main__':
    # 读取输入CSV文件
    with open('input.csv', 'r',encoding='cp1252') as infile:
        reader = csv.reader(infile)
        # 跳过标题行
        next(reader)
        # 提取每一行的信息，并将其作为元组存储在列表中
        data = [extract_info(row) for row in reader]

    # 写出输出CSV文件
    with open('output.csv', 'w', newline='',encoding='cp1252') as outfile:
        writer = csv.writer(outfile)
        # 写入标题行
        writer.writerow(['name', 'attr', 'value'])
        # 将每个对象的信息写成一个包含4行的集合
        for row in data:
            name = row[0]
            type_ = row[1]
            metiral = row[2]
            belong = row[3]
            locate = row[4]
            writer.writerow([name, 'type', type_])
            writer.writerow([name, 'metiral', metiral])
            writer.writerow([name, 'belong', belong])
            writer.writerow([name, 'locate', locate])
