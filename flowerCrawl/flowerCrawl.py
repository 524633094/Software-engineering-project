# -*-coding:utf-8 -*-
#改变标准输出的默认编码
import requests
from bs4 import BeautifulSoup as Bs
import pymysql
QUWEI = "http://www.aihuhua.com/hua/quwei/page-1.html"
FANGXIANG = "http://www.aihuhua.com/hua/fangxiang/page-1.html"
GUANGUO = "http://www.aihuhua.com/hua/guanguo/page-1.html"
GUANHUA = "http://www.aihuhua.com/hua/guanhua/page-1.html"
GUANYE = "http://www.aihuhua.com/hua/guanye/page-1.html"
GUANJING = "http://www.aihuhua.com/hua/guanjing/page-1.html"
JIEQING = "http://www.aihuhua.com/hua/jieqing/page-1.html"
CHUIDIAO = "http://www.aihuhua.com/hua/chuidiao/page-1.html"
GUOSHU = "http://www.aihuhua.com/hua/guoshu/page-1.html"
CAOPING = "http://www.aihuhua.com/hua/dibeicaoping/page-1.html"
SHUIPEI = "http://www.aihuhua.com/hua/shuipei/page-1.html"
PENGZAI = "http://www.aihuhua.com/hua/penzai/page-1.html"
addresses = [GUOSHU,CAOPING,SHUIPEI,PENGZAI]
#数据库连接
db = pymysql.connect("123.207.124.113","admin","mysql","SEproject",use_unicode=True, charset="utf8")
cursor = db.cursor()
class urlEnity:
    def __init__(self,url,endpage):
        self.url = url
        self.endpage = endpage
    def printurlenity(self):
        print(self.url+"****"+self.endpage)
urlenitys = []
pagesUrl = []
def getEndPage(addresses):
    for url in addresses:
        r = requests.get(url)
        b = Bs(r.text)
        item = b.find('a',class_ = "next").previous_sibling
        endpage = item.string.replace("..","")
        enity = urlEnity(url,endpage)
        urlenitys.append(enity)
    return urlenitys

def printurlenity(urlenitys):
    for enity in urlenitys:
        enity.printurlenity()

def startCrawl(urlenitys):
    print("开始爬取页面链接")
    for enity in urlenitys:
        for i in range(1,int(enity.endpage)):
            url = enity.url.replace("1",str(i))
            print(url)
            getContenUrl(url)
def getContenUrl(url):
    ContentUrls = []
    r = requests.get(url)
    b = Bs(r.text)
    items = b.find_all("a",class_ = "title",target = "_blank")
    for item in items:
        contenurl = item.get("href")
        getContent(contenurl)
        print(contenurl+"完成")

def getContent(contentUrl):
    r = requests.get(contentUrl)
    b = Bs(r.text)
    name = ""
    othername = ""
    pltype = ""
    pltype = ""
    plmode = ""
    pltime = ""
    plinfo = ""
    intro = ""
    form = ""
    habit = ""
    plantmehod = ""
    flowerlanguage = ""
    sql = 'insert into flower(name, othername,pltype,plmode,pltime,plinfo,intro,form,habit,plantmehod,flowerlanguage) values(%s, %s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
    #基本信息
    try:
        item = b.find("div",class_ = "cont")
        name = item.find("h1",class_ = "name").get("title")
        labarr = item.find_all("label")
        plinfo = item.find("p").get_text()
        othername = labarr[0].get_text()
        pltype = labarr[1].find("a").get("title")
        plmode = labarr[2].get_text()
        pltime = labarr[3].find("a").get("title")
        #附加信息
        intro = b.find(id = "doc-content").find("dd").get_text()
        form = b.find(id = "doc-9b51f49757644682f44c681fd327fe9b").find("dd").get_text()
        habit = b.find(id = "doc-2353e0b8be52db129e7205b21a5b11d2").find("dd").get_text()
        plantmehod = b.find(id = "doc-10003c15d099f1513ce250a3bd90e396").find("dd").get_text()
        flowerlanguage = b.find(id = "doc-fe9b11d324ed6e42c917a0b0d30ea651").find("dd").get_text()
        cursor.execute(sql,(name, othername,pltype,plmode,pltime,plinfo,intro,form,habit,plantmehod,flowerlanguage))
        db.commit()
    except Exception as e:
        if name != "":
            cursor.execute(sql,(name, othername,pltype,plmode,pltime,plinfo,intro,form,habit,plantmehod,flowerlanguage))
            db.commit()
        print(e)
        


if __name__ == '__main__':
     getEndPage(addresses)
     startCrawl(urlenitys)
     db.close()

    # # printurlenity(urlenitys)
    # # getContenUrl(GUANGUO)
    # getContent("http://www.aihuhua.com/huahui/cuiluli.html")