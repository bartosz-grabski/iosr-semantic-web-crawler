import scrapy
from scrapy.selector import Selector
from semanticCrawler.items import MetaItem
import re
from scrapy.contrib.spiders import CrawlSpider,Rule
from scrapy.selector import HtmlXPathSelector
from scrapy.http import Request
from scrapy.contrib.linkextractors.lxmlhtml import LxmlLinkExtractor
from urlparse import urlparse
import tldextract

class MetaSpider(CrawlSpider):
    name = "metaspider"

    extractor = LxmlLinkExtractor(unique=True)

    rules =  (
        Rule(extractor, callback='parse_page', follow=True),
    )

    def parse_start_url(self, response):
        list(self.parse_links(response))

    def parse_links(self, response):
        extract = tldextract.extract(response.url)
        domain= "{}.{}".format(extract.domain, extract.suffix)
        for link in LxmlLinkExtractor(deny_domains=domain).extract_links(response):
            yield Request(link.url, callback = self.parse_page)
        for link in LxmlLinkExtractor(allow_domains=domain).extract_links(response):
            yield Request(link.url, callback = self.parse_page)


    def __init__(self, urls=None, keywords=None, query_id=None, *args, **kwargs):
        super(MetaSpider, self).__init__(*args, **kwargs)
        self.start_urls = urls.split(',')
        self.keywords = keywords.split(',')
        self.query_id = query_id

    def parse_page(self, response):
        print response.url
        item = MetaItem()
        item['title'] = Selector(response=response).xpath('//title/text()').extract()
        item['keywords'] = Selector(response=response).xpath('//head//keywords/text()').extract()
        item['description'] = Selector(response=response).xpath('//head//description/text()').extract()
        item['url'] = response.url
        item['query_id'] = self.query_id
        matching = [keyword for keyword in self.keywords if keyword in response.body]
        if len(matching) > 0:
            item['keywords'] = matching
            return item