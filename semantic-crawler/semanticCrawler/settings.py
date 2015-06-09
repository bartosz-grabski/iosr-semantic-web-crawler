# -*- coding: utf-8 -*-

# Scrapy settings for semanticCrawler project
#
# For simplicity, this file contains only the most important settings by
# default. All the other settings are documented here:
#
#     http://doc.scrapy.org/en/latest/topics/settings.html
#

BOT_NAME = 'semanticCrawler'

SPIDER_MODULES = ['semanticCrawler.spiders']
NEWSPIDER_MODULE = 'semanticCrawler.spiders'

ITEM_PIPELINES = [
    'scrapy_mongodb.MongoDBPipeline',
    ]

DUPEFILTER_CLASS = 'scrapy.dupefilter.RFPDupeFilter'

MONGODB_URI = 'mongodb://172.17.84.81:27017'
MONGODB_DATABASE = 'queries'
MONGODB_COLLECTION = 'results'

# Crawl responsibly by identifying yourself (and your website) on the user-agent
#USER_AGENT = 'semanticCrawler (+http://www.yourdomain.com)'
