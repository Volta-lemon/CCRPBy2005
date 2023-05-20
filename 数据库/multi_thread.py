import requests
import re
from bs4 import BeautifulSoup
import csv
from lxml import etree
import concurrent.futures


def rs(text):
    """去除文本中的多余换行"""
    return "\n".join([line.strip() for line in text.split("\n") if line.strip()])

def getOne(index):
    urls = r"https://cantorcollection.stanford.edu/objects-1/info?query=mfs%20all%20%22china%22&sort=0&page={}"
    url=urls.format(index)

    response = requests.get(url)
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')
        xhtml = etree.HTML(response.content)
        try:
            body=soup.find(name='body', attrs={"class":'clearfix'})
            container=body.find(name='div', attrs={"class":'container'},recursive=False)
            row=container.find(name='div', attrs={"class":'row'},recursive=False)
            col_xs_12=row.find(name='div', attrs={"class":'col-xs-12'},recursive=False)
            position_relative=col_xs_12.find(name='div', attrs={"style":'position: relative;'},recursive=False)
            container2=position_relative.find(name='div', attrs={"class":'container'},recursive=False)
            row2=container2.find(name='div', attrs={"class":'row'},recursive=False)
            img=container2.find(name='a', attrs={"class":'highslide'},recursive=True)
            ImageUriP="https://cantorcollection.stanford.edu"
            imageUri=ImageUriP+img.attrs['href']

            col=row2.find(name='div', attrs={"class":'col'},recursive=False)
            Name=col.find(name='em').get_text()
            text = xhtml.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/text()")
            year = rs(text[1])
            BY = xhtml.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/a")
            author = rs(BY[0].text)
            try:
                proV = container2.find(name='div', attrs={"class":"embarkInfoDescription"},recursive=False)
                descr = rs(proV[1])
            except:
                descr = None
        except:
            return None
    else:
        print("网页请求失败，状态码：", response.status_code)
        return rtl
    rtl=[index,url,imageUri,Name,year,author,descr]
    return rtl

def worker(i):
    a = getOne(i)
    print(i)
    with open('data.csv', 'a', newline='') as file:
        writer = csv.writer(file)
        try:
            writer.writerow(a)
        except:
            print("error")

if __name__ == '__main__':
    with concurrent.futures.ThreadPoolExecutor(max_workers=100) as executor:
        for i in range(600, 1000):
            executor.submit(worker, i)
