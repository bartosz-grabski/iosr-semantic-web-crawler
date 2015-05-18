# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class MetaItem(scrapy.Item):
    query_id = scrapy.Field()
    description = scrapy.Field()
    title = scrapy.Field()
    keywords = scrapy.Field()
    url = scrapy.Field()