import os
import random
import sys
import time
import string
import cchardet
import requests
import json

from pyquery import PyQuery as pq
from MagicGoogle.config import USER_AGENT, DOMAIN, BLACK_DOMAIN, URL_SEARCH, URL_NEXT, URL_NUM, LOGGER

if sys.version_info[0] > 2:
    from urllib.parse import quote_plus, urlparse, parse_qs
else:
    from urllib import quote_plus
    from urlparse import urlparse, parse_qs


class MagicGoogle():
    """
    Magic google search.
    """

    def __init__(self, proxies=None):
        self.proxies = random.choice(proxies) if proxies else None

    def search_by_keywords(self, list_query, num = None, start=0, pause = 2):
        listOut = []
        for lq in list_query:
            dictOut = {}
            dictOut['keyword'] = lq
            tmp_list = self.search_by_keyword(lq, language=None, num=num, start=0, pause=2, out_format = 'string')
            dictOut['record'] = tmp_list
            listOut.append(dictOut)
        
        return json.dumps(listOut)
        
    def search_by_keyword(self, query, language=None, num=None, start=0, pause=2, out_format='json'):
        """
        Get the results you want,such as title,description,url
        :param query:
        :param language:
        :param num:
        :param start:
        :return: Generator
        """
        content = self.search_page(query, language, num, start, pause)
        pq_content = self.pq_html(content)
        
        listOut = []
        for item in pq_content('div.g').items():
            result = {}
            result['title'] = item('h3.r>a').eq(0).text()
            href = item('h3.r>a').eq(0).attr('href')
            if href == None:
                continue
            if href:
                url = self.filter_link(href)
                if url == None:
                    continue
                result['url'] = url
            text = item('span.st').text()
            result['text'] = text
            listOut.append(result)
        if out_format == 'json':
            return json.dumps(listOut)
        else:
            return listOut

    def search_by_images(self, list_image, language=None, num=None, start=0, pause=2):
        listOut = []
        for item in list_image:
            dictOut = {}
            dictOut['file'] = item
            content = self.search_by_image(item, language, num, start, pause, 'string')
            dictOut['record'] = content
            listOut.append(dictOut)
        
        return json.dumps(listOut)
        
    def search_by_image(self, query, language=None, num=None, start=0, pause=2, out_format='json'):
        """
        Get the results you want,such as title,description,url
        :param query:
        :param language:
        :param num:
        :param start:
        :return: Generator
        """
        content = self.search_image_page(query, language, num, start, pause)
        if out_format == 'json':
            return json.dumps(content)
        else:
            return content
        

    def get_image_name(self, path):
        """
        get image file name.
        :return: image name.
        """
        a = os.path.split(path)
        return a[1]

    def get_image_type(self, image_name):
        """
        get image file type.
        :return: image type.
        """
        a = image_name.split('.')
        if len(a) < 2:
            return 0
        
        if a[1] == 'jpg':
            return 'image/jpeg'
        elif a[1] == 'gif':
            return 'image/gif'
        elif a[1] == 'png':
            return 'image/png'
        elif a[1] == 'bmp':
            return 'image/bmp'
        elif a[1] == 'tif':
            return 'image/tiff'
        else:
            return ''

    def get_image_content(self, path):
        """
        read binary image file.
        :return: binary.
        """
        try:
            image_file = open(path,'rb')
        except:
            return ""
        ret_tmp = image_file.read()
        image_file.close()
        return ret_tmp

    def get_random_boundary(self):
        """
        Get a random boundary string.
        :return: Random boundary string.
        """
        salt = ''.join(random.sample(string.ascii_letters + string.digits, 16))
        return '----WebKitFormBoundary' + salt

    def search_image_page(self, query, language=None, num=None, start=0, pause=1):
        """
        Google 
        :param query: image file path .jpg、.gif、.png、.bmp、.tif 
        :param language: Language
        :return: result
        """
        image_name = self.get_image_name(query)
        image_type = self.get_image_type(image_name)
        
        if image_name == '' or image_type == '':
            return No
            
        time.sleep(pause)
        domain = 'images.google.com'
        url = "https://{domain}/searchbyimage/upload"
        url = url.format(domain = domain)
        boundary = self.get_random_boundary()
        user_agent = self.get_random_user_agent()
        headers = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 QIHU 360SE', 'content-type': "multipart/form-data; boundary=" + boundary}
        datas = ""
        datas += '--' + boundary + '\r\n'
        datas += "Content-Disposition: form-data; name=\"image_url\"\r\n\r\n";
        datas += '\r\n--' + boundary + '\r\n'
        datas += "Content-Disposition: form-data; name=\"encoded_image\"; filename=\"%s\"\r\n" % (image_name);
        datas += "Content-Type: %s\r\n\r\n" % (image_type);
        datas += self.get_image_content(query)
        datas += '\r\n--' + boundary + '\r\n'
        datas += "Content-Disposition: form-data; name=\"image_content\"\r\n\r\n";
        datas += '\r\n--' + boundary + '\r\n'
        datas += "Content-Disposition: form-data; name=\"filename\"\r\n\r\n";
        datas += '\r\n--' + boundary + '\r\n'
        datas += "Content-Disposition: form-data; name=\"hl\"\r\n\r\n";
        datas += "zh-CN\r\n";
        datas += '--' + boundary + '--\r\n'
        text = ""
 
        try:
            requests.packages.urllib3.disable_warnings(requests.packages.urllib3.exceptions.InsecureRequestWarning)
            r = requests.post(url=url,
                             proxies=self.proxies,
                             headers=headers,
                             data = datas,
                             allow_redirects=False,
                             verify=False,
                             timeout=30)
            LOGGER.info(url)
            content = r.content
            charset = cchardet.detect(content)
            text = content.decode(charset['encoding'])
        except Exception as e:
            LOGGER.exception(e)
            return None
        
        pq_content = self.pq_html(text)
        url1 = pq_content('A').eq(0).attr('href')
        tmp_list = []
        for i in range(0, int(num/10)):
            time.sleep(pause)
            url_tmp = url1 + '&start=%s' %(i * 10 + start)
            headers = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 QIHU 360SE'}
            try:
                requests.packages.urllib3.disable_warnings(requests.packages.urllib3.exceptions.InsecureRequestWarning)
                r = requests.get(url=url_tmp,
                                 proxies=self.proxies,
                                 headers=headers,
                                 allow_redirects=False,
                                 verify=False,
                                 timeout=30)
                LOGGER.info(url_tmp)
                content = r.content
                charset = cchardet.detect(content)
                text = content.decode(charset['encoding'])
                pq_content = self.pq_html(text)
                for item in pq_content('div.g').items():
                    result = {}
                    result['title'] = item('h3.r>a').eq(0).text()
                    href = item('h3.r>a').eq(0).attr('href')
                    if href == None:
                        continue
                    if href:
                        url = self.filter_link(href)
                        if url == None:
                            continue
                        result['url'] = url
                    text = item('span.st').text()
                    result['text'] = text
                    tmp_list.append(result)
            except Exception as e:
                LOGGER.exception(e)
                return None
                
        return tmp_list

    def search_page(self, query, language=None, num=None, start=0, pause=2):
        """
        Google search
        :param query: Keyword
        :param language: Language
        :return: result
        """
        time.sleep(pause)
        domain = self.get_random_domain()
        if start > 0:
            url = URL_NEXT
            url = url.format(
                domain=domain, language=language, query=quote_plus(query), num=num, start=start)
        else:
            if num is None:
                url = URL_SEARCH
                url = url.format(
                    domain=domain, language=language, query=quote_plus(query))
            else:
                url = URL_NUM
                url = url.format(
                    domain=domain, language=language, query=quote_plus(query), num=num)
        if language is None:
            url = url.replace('hl=None&', '')
        # Add headers
        headers = {'user-agent': self.get_random_user_agent()}
        try:
            requests.packages.urllib3.disable_warnings(requests.packages.urllib3.exceptions.InsecureRequestWarning)
            r = requests.get(url=url,
                             proxies=self.proxies,
                             headers=headers,
                             allow_redirects=False,
                             verify=False,
                             timeout=30)
            LOGGER.info(url)
            content = r.content
            charset = cchardet.detect(content)
            text = content.decode(charset['encoding'])
            return text
        except Exception as e:
            LOGGER.exception(e)
            return None

    def search_url(self, query, language=None, num=None, start=0, pause=2):
        """
        :param query:
        :param language:
        :param num:
        :param start:
        :return: Generator
        """
        content = self.search_page(query, language, num, start, pause)
        pq_content = self.pq_html(content)
        for item in pq_content('h3.r').items():
            href = item('a').attr('href')
            if href:
                url = self.filter_link(href)
                if url:
                    yield url

    def filter_link(self, link):
        """
        Returns None if the link doesn't yield a valid result.
        Token from https://github.com/MarioVilas/google
        :return: a valid result
        """
        try:
            # Valid results are absolute URLs not pointing to a Google domain
            # like images.google.com or googleusercontent.com
            o = urlparse(link, 'http')
            if o.netloc:
                return link
            # Decode hidden URLs.
            if link.startswith('/url?'):
                link = parse_qs(o.query)['q'][0]
                # Valid results are absolute URLs not pointing to a Google domain
                # like images.google.com or googleusercontent.com
                o = urlparse(link, 'http')
                if o.netloc:
                    return link
        # Otherwise, or on error, return None.
        except Exception as e:
            LOGGER.exception(e)
        return 'https://www.google.com' +link

    def pq_html(self, content):
        """
        Parsing HTML by pyquery
        :param content: HTML content
        :return:
        """
        return pq(content)

    def get_random_user_agent(self):
        """
        Get a random user agent string.
        :return: Random user agent string.
        """
        return random.choice(self.get_data('user_agents.txt', USER_AGENT))

    def get_random_domain(self):
        """
        Get a random domain.
        :return: Random user agent string.
        """
        domain = random.choice(self.get_data('all_domain.txt', DOMAIN))
        if domain in BLACK_DOMAIN:
            self.get_random_domain()
        else:
            return domain

    def get_data(self, filename, default=''):
        """
        Get data from a file
        :param filename: filename
        :param default: default value
        :return: data
        """
        root_folder = os.path.dirname(__file__)
        user_agents_file = os.path.join(
            os.path.join(root_folder, 'data'), filename)
        try:
            with open(user_agents_file) as fp:
                data = [_.strip() for _ in fp.readlines()]
        except:
            data = [default]
        return data
