import scrapy
from scrapy.selector import Selector
from semanticCrawler.items import MetaItem
import re

class MetaSpider(scrapy.Spider):
    name = "dmoz"

    def __init__(self, urls=None, query_id=None, *args, **kwargs):
        super(MetaSpider, self).__init__(*args, **kwargs)
        self.start_urls = urls.split(',')
        self.query_id = query_id
        print self.start_urls

    def parse(self, response):
        item = MetaItem()
        item['title'] = Selector(response=response).xpath('//title/text()').extract()
        item['keywords'] = Selector(response=response).xpath('//head//keywords/text()').extract()
        item['description'] = Selector(response=response).xpath('//head//description/text()').extract()
        item['url'] = response.url
        item['query_id'] = self.query_id
        return item
        # linkRegex = re.compile(response.url+r'.*?/')
        # re.findall(linkRegex, Selector(response=response).xpath'//a')