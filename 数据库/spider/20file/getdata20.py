import urllib3
import requests
import selenium
import bs4
import re
import csv
import pandas as pd
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys   
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait
from typing import (Tuple, List)

"""
id          int             -
name        varchar         -
author      varchar         -
relicTime   varchar         -
description varchar         -
imageUrl    varchar         -
moreUrl     varchar         -
"""



base_url = 'https://emuseum.nich.go.jp/'
page_url = 'https://emuseum.nich.go.jp/result?freeword=chinese&langId=en&webView=&fwSearch=Search'
url = 'https://emuseum.nich.go.jp/detail?langId=zh&webView=&content_base_id=100949&content_part_id=0&content_pict_id=0'


# browser = webdriver.Chrome()
# try:
#     browser.get(url=url)
#     with open('test.html', 'w') as fp:
#         fp.write(browser.page_source)
# finally:
#     browser.close()



def get_detail_url(url:str)->list:
    r = requests.get(url=url)
    soup = BeautifulSoup(r.text, 'lxml')
    res = soup.find_all(name='div', attrs={'class': 'item'})
    
    # item:bs4.element.Tag = res[0]
    # # print(type(item))
    # detail_url = item.find(name='a')['href'].__str__()
    # image_url = item.find(name='img')['src'].__str__()
    detail_urls = []
    image_urls = []
    for item in res:
        item:bs4.element.Tag
        tempurl = base_url + item.find(name='a')['href'].__str__()
        detail_urls.append(tempurl)
        tempurl = base_url + item.find(name='img')['src'].__str__()
        image_urls.append(tempurl)
    return detail_urls, image_urls
    
# r = requests.request(url=url, method='get')

# with open('test.html', 'w') as fp:
#     fp.write(r.text)

def get_detail(urls:List[str]):
    description = []
    name = []
    for url in urls:
        r = requests.get(url=url)
        soup = BeautifulSoup(r.text, 'lxml')
        # with open('detail.html', 'w') as fp:
        #     fp.write(r.text)
        temp = soup.find(attrs={'id': 'summary'}).text.replace('\n', '')
        name.append(temp)
        temp = soup.find(attrs={'id': 'caption'}).text
        description.append(temp)
    return name, description


def save_data(*args):
    names, descs, img_urls, more_urls = args
    Id = 200
    fp = open('data20.csv', 'w')
    data = csv.writer(fp)
    row = ('Id', 'name', 'author', 'relicTime', 'descriptoin',\
           'imageUrl', 'moreUrl')
    data.writerow(row)
    for pos in range(len(names)):
        row = (Id, names[pos], 'null', 'null',\
               descs[pos], img_urls[pos], more_urls[pos])
        data.writerow(row)
        Id += 1

def main():
    detail_urls, img_urls = get_detail_url(page_url)
    # for url in detail_urls:
    #     get_detail(url=url)
    name, desc = get_detail(detail_urls)

    save_data(name, desc, img_urls, detail_urls)
    # with open('desc.txt', 'w') as fp:
    #     fp.write(str(desc))
    # with open('name.txt', 'w') as fp:
    #     fp.write(str(name))

if __name__ == '__main__':
    main()