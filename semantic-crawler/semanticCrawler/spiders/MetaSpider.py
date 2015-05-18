import scrapy
from scrapy.selector import Selector
from semanticCrawler.items import MetaItem
import re

class MetaSpider(scrapy.Spider):
    name = "dmoz"

    def __init__(self, url_file, query_id):
        self.start_urls = ['http://'+l.strip() for l in open(url_file).readlines()]
        print self.start_urls

    def parse(self, response):
        item = MetaItem()
        item['title'] = Selector(response=response).xpath('//title/text()').extract()
        item['keywords'] = Selector(response=response).xpath('//head//keywords/text()').extract()
        item['description'] = Selector(response=response).xpath('//head//description/text()').extract()
        item['url'] = response.url
        return item
        # linkRegex = re.compile(response.url+r'.*?/')
        # re.findall(linkRegex, Selector(response=response).xpath'//a')