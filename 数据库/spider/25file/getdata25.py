import typing
import bs4
import csv
import time
import re
import logging
import pandas as pd
from bs4 import BeautifulSoup
from typing import (Tuple, List, )
from selenium import webdriver 


"""
id          int             -
name        varchar         -
relicTime   varchar         -
type        varchar         -
metiral     varchar         -
author      varchar         -
description varchar         -
imageUrl    varchar         -
moreUrl     varchar         -
museum      varchar         -
location    varchar         -
simpleTime  varchar         -
"""

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s - %(levelname)s: %(message)s')

# i可以调整抓取的页数,当前抓取5页，最多140页
page_urls = [f'http://collection.imamuseum.org/results.html?query=china&has_image=T&page={i}'.format(i) for i in range(5, 17)]
headers = {'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36'}


def get_url(urls:list)->Tuple[list, list]:
    detail_urls = []
    img_urls = []
    option = webdriver.ChromeOptions()
    option.add_argument("headless")
    browser = webdriver.Chrome(chrome_options=option)
    for url in urls:
        logging.info('page url %s', url)
        browser.get(url)
        time.sleep(1)
        soup = BeautifulSoup(browser.page_source, 'lxml')
        res = soup.findAll(attrs={'class': 'item col-xs-6 col-md-4'})
        for item in res:
            temp = item.find(name='a')['href']
            detail_urls.append(temp)
            img_urls.append(temp + '0_preview.jpg')
    browser.close()
    return img_urls, detail_urls


def get_detail(urls:list)->Tuple[list, list]:
    names = []
    descs = []
    artists = []
    materials = []
    relicTimes = []
    option = webdriver.ChromeOptions()
    option.add_argument("headless")
    browser = webdriver.Chrome(chrome_options=option)
    for url in urls:
        logging.info('detail url %s', url)
        browser.get(url)
        time.sleep(1)
        soup = BeautifulSoup(browser.page_source, 'lxml')
        try:
            if soup.find(attrs={'id': "object-title"}).text and \
                soup.find(attrs={'id': "object-rights"}).text:
                name = soup.find(attrs={'id': "object-title"}).text +\
                        soup.find(attrs={'id': "object-rights"}).text
            else:
                name = 'null'
        except AttributeError as e:
            continue
        try:    
            desc = soup.find(attrs={'id': "object-data"}).text
        except AttributeError as e:
            desc = 'null'
        names.append(name)
        descs.append(desc)
        try:
            artist = soup.find(attrs={'itemprop': 'creator'}).text
        except AttributeError as e:
            artist = 'China'
        try:
            relicTime = soup.find(attrs={'itemprop': 'dateCreated'}).text
        except AttributeError as e:
            relicTime = 'null'
        try:
            material = soup.find(attrs={'itemprop': 'materials'}).text
        except AttributeError as e:
            material = 'null'
        artists.append(artist)
        relicTimes.append(relicTime)
        materials.append(material)   
    browser.close()

    return (names, descs, artists, relicTimes, materials)


def save_data(*args):
    """
    id          int             -
    name        varchar         -
    relicTime   varchar         -
    type        varchar         -
    metiral     varchar         -
    author      varchar         -
    description varchar         -
    imageUrl    varchar         -
    moreUrl     varchar         -
    museum      varchar         -
    location    varchar         -
    simpleTime  varchar         -
    """

    names, relicTimes, types, metirals, authors, descs, img_urls, more_urls = args

    fp = open('./25file/25data_1.csv', 'w')
    data = csv.writer(fp)
    row = ('Id', 'name', 'relicTime', 'type', 'metiral', 'author',\
            'descriptoin', 'imageUrl', 'moreUrl', 'museum',\
            'location', 'simpleTime')
    data.writerow(row)
    Id = 1
    museum = 'imamuseum'
    location = 'United States'
    for pos in range(len(names)):
        row = (Id, names[pos], relicTimes[pos], types[pos], metirals[pos],\
               authors[pos], descs[pos], img_urls[pos], more_urls[pos],\
               museum, location)
        data.writerow(row)
        Id += 1
def get_other(urls:list)->Tuple[list, list, Tuple]:
    artists = []
    materials = []
    relicTimes = []
    option = webdriver.ChromeOptions()
    option.add_argument("headless")
    browser = webdriver.Chrome(chrome_options=option)
    for url in urls:
        logging.info('detail url %s', url)
        browser.get(url)
        time.sleep(1)
        soup = BeautifulSoup(browser.page_source, 'lxml')
        try:
            artist = soup.find(attrs={'itemprop': 'creator'}).text
        except AttributeError as e:
            artist = 'China'
        try:
            relicTime = soup.find(attrs={'itemprop': 'dateCreated'}).text
        except AttributeError as e:
            relicTime = 'null'
        try:
            material = soup.find(attrs={'itemprop': 'materials'}).text
        except AttributeError as e:
            material = 'null'
        artists.append(artist)
        relicTimes.append(relicTime)
        materials.append(material)   
    browser.close()

    return (artists, relicTimes, materials)


def main():
    img_urls, detail_urls = get_url(page_urls)
    names, descs, authors, relicTimes, metirals = get_detail(detail_urls)
    types = [re.split(' ', name)[2] for name in names]
    args = (names, relicTimes, types, metirals, authors, descs, img_urls, detail_urls)
    save_data(*args)
    # df = pd.read_csv('./25file/25data.csv')
    # names = df['name'].tolist()
    # types = [re.split(' ', name)[2] for name in names]
    # print(types)

    
if __name__ == '__main__':
    main()