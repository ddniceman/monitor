# coding: utf-8

import re
import socket
import struct
import urllib2
import json
import time
import sys

def get_url_result(str_url):
	strOut = ""
	try:
		response = urllib2.urlopen(str_url)
		strOut = response.read().decode('utf-8')
	except:
		return strOut
	
	return strOut
	
def parse_query_ret(str_query_ret):
	listOut = []
	try:
		i0 = str_query_ret.index("<div class=\"result result2\">")
		s1 = str_query_ret[i0:]
		i1 = s1.index("</div>")
		s2 = s1[0:i1]

		while True:
			i2 = s2.find("target=\"_blank\">")
			if i2 == -1:
				break;
			
			s3 = s2[len("target=\"_blank\">") + i2:]

			s2 = s3
			iIndex = 0
			for i in range(0, i2):
				if s3[i] == '<':
					break;
				iIndex = iIndex + 1
			
			s4 = s3[0:iIndex]
			dictTmpRet = domain_query_record(s4)
			listOut.append(dictTmpRet)
	except:
		return listOut
	return listOut
	
def parse_query_ret_tocken(str_query_ret):
	strOut = ""
	iIndex = 0
	try:
		i0 = str_query_ret.index("var _TOKEN = '")
		s1 = str_query_ret[(i0 + len("var _TOKEN = '")):]
		for i in range(0, len(s1)):
			if s1[i] == "'":
				break;
			iIndex = iIndex + 1
		
		strOut = s1[0:iIndex]
	except:
		return strOut
	return strOut
	
def domain_query_record(str_domain):
	strUrlBase = "http://icp.chinaz.com/" + str_domain
	dictOut = {}
	strQRet = get_url_result(strUrlBase)
	dictOut['name'] = str_domain
	try:
		i0 = strQRet.index("<!--IcpMain01-begin-->")
		i1 = strQRet.index("<!--IcpMain01-end-->")
		s0 = strQRet[i0:i1]
		i2 = s0.index(u'<span>主办单位名称</span>')
		s1 = s0[i2:]
		i3 = s1.index("<p>")
		i4 = s1.index("<a class")
		s2 = s1[i3 + len("<p>"):i4]

		dictOut['compnay'] = s2
		
		
		i5 = s0.index(u'<span>网站备案/许可证号</span><p><font>')
		s3 = s0[i5:]
		i6 = s3.index("</font>")
		s4 = s3[len(u'<span>网站备案/许可证号</span><p><font>'):i6]

		dictOut['id'] = s4
		
		i7 = s0.index(u'<span>审核时间</span><p>')
		s5 = s0[i7:]
		i8 = s5.index("</p>")
		s6 = s5[len(u'<span>审核时间</span><p>'):i8]

		dictOut['date'] = s6
	except:
		dictOut['compnay'] = u'未备案'
		dictOut['id'] = u'未备案'
		dictOut['date'] = u'未备案'
		return dictOut
	
	return dictOut

def check_ip_string(str_ip_string):
	compile_ip=re.compile('^((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)$')
	
	if compile_ip.match(str_ip_string):
		return True    
	else:
		return False  

def ip_revers_query(str_ip_string, out_format='json'):
	strUrlBase = "http://site.ip138.com/"
	strUrlBase1 = "http://site.ip138.com/index/querybyip/?ip=%s&page=%s&token=%s"
	strUrlBase2 = "http://site.ip138.com/domain/write.do?input=%s&token=%s"
	jsonOut = ""
	strBaseIPAddr = ""
	iNetMask = 32
	listOut = []
	
	
	if "/" in str_ip_string:
		str_ip_array = str_ip_string.split('/')
		if len(str_ip_array) != 2:
			print "ip adress string error"
			return null;
		
		if check_ip_string(str_ip_array[0]) != True:
			print "ip adress string error"
			return null;
		
		strBaseIPAddr = str_ip_array[0]
		iNetMask = (int)(str_ip_array[1])
	else:
		if check_ip_string(str_ip_string) != True:
			print "ip adress string error"
			return null;
			
		strBaseIPAddr = str_ip_string
		iNetMask = 32
	
	uiNetMask = 0

	for i in range(32 - iNetMask, 32):
		uiNetMask = uiNetMask + 2 ** i
	
	iBaseIPAddr = int(socket.inet_aton(strBaseIPAddr).encode('hex'),16)
	for i in range(0, 2 ** (32 - iNetMask)):

		iTmpIPAddr = (iBaseIPAddr & uiNetMask) + i
		strTmpIPAddr = socket.inet_ntoa(struct.pack("!I",iTmpIPAddr))
		dictOut = {}
		dictOut['ip'] = strTmpIPAddr
		iIndex = 0
		strTocken = ""
		tmpListOut = []
		while True:
			if iIndex == 0:
				strTmpUrl = strUrlBase + strTmpIPAddr + '/'
				strQRet = get_url_result(strTmpUrl)
				listTempParseRet = parse_query_ret(strQRet)
				tmpListOut.append(listTempParseRet)
				#dictOut['record'] = listTempParseRet
				strTocken = parse_query_ret_tocken(strQRet)
			else:
				strTmpUrl = strUrlBase2 % (strTmpIPAddr, strTocken)
				get_url_result(strTmpUrl)
				strTmpUrl = strUrlBase1 % (strTmpIPAddr, iIndex + 1, strTocken)
				strQRet = get_url_result(strTmpUrl)
				
				try:
					hjson = json.loads(strQRet)
					v = hjson["data"]
					for index,item in enumerate(v):
							vv = item["domain"]
							dictTempParseRet1 = domain_query_record(vv)
							tmpListOut.append(dictTempParseRet1)
				except (ValueError, KeyError, TypeError) as error:
					break;
					
			iIndex = iIndex + 1
		dictOut['record'] = tmpListOut
		listOut.append(dictOut)
		time.sleep(1)

	if out_format == 'json':
		return json.dumps(listOut)
	else:
		return listOut
    
def ip_revers_query_list(list_ip):
	listOut = []
	for li in list_ip:
		dictOut = {}
		dictOut['ip'] = li
		dictOut['ip_ret'] = ip_revers_query(li, 'string')
		listOut.append(dictOut)

	return json.dumps(listOut)
	
def domain_revers_query(str_domain):
	dictOut = ""
	try:
		dictOut = domain_query_record(str_domain)
	except:
		return null
	return json.dumps(dictOut)
		

	#print domain_revers_query("www.12312rodfjj.com")