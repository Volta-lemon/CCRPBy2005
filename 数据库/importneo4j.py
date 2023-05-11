# -*- coding: utf-8 -*-
from py2neo import Graph, Node, Relationship

if __name__ == '__main__':
    # 连接Neo4j数据库
    graph = Graph("bolt://localhost:7687", auth=("neo4j", "123456"))

    # 读取CSV文件中的数据（假设文件名为data.csv）
    with open('graphdata.csv', 'r') as f:
        lines = f.readlines()[1:]  # 忽略首行标题

    # 创建节点字典和关系列表
    nodes = {}
    relationships = []

    # 遍历数据行并创建节点和边
    for line in lines:
        print("over " + line)
        row = line.strip().split(',')
        name = row[0]
        attr = row[1]
        value = row[2]

        # 检查节点是否已存在，不存在则创建
        if name not in nodes:
            nodes[name] = Node("Artifact", name=name)
            graph.create(nodes[name])

        # 检查属性节点是否已存在，不存在则创建
        if value and (attr not in nodes or value not in nodes[attr]):
            nodes.setdefault(attr, {})[value] = Node(attr, name=value)
            graph.create(nodes[attr][value])

        # 创建关系并连接到文物节点
        if value:
            relationships.append((nodes[name], attr, nodes[attr][value]))

    # 创建博物馆节点和材料节点，并将关系连接到这些节点上
    museums = {}
    materials = {}
    for artifact_node, attr, value_node in relationships:
        if attr == "belong":
            museum_name = value_node["name"]
            if museum_name not in museums:
                museums[museum_name] = Node("Museum", name=museum_name)
                graph.create(museums[museum_name])
            graph.create(Relationship(artifact_node, "BELONGS_TO", museums[museum_name]))
        elif attr == "locate":
            location = value_node["name"]
            if location not in museums:
                museums[location] = Node("Location", name=location)
                graph.create(museums[location])
            graph.create(Relationship(artifact_node, "LOCATED_IN", museums[location]))
        elif attr == "material":
            material_name = value_node["name"]
            if material_name not in materials:
                materials[material_name] = Node("Material", name=material_name)
                graph.create(materials[material_name])
            graph.create(Relationship(artifact_node, "MADE_OF", materials[material_name]))
        elif attr == "type":
            graph.create(Relationship(artifact_node, "HAS_TYPE", value_node))
