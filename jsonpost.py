# -*- coding: utf-8 -*-
import urllib.request
import json
import time
import string
URL = 'http://localhost:8888/api/sensor/1935fa43fe6c4f56b63d1fd8c2866e4e'
DEV_NAME = '树莓派123'


# data = {
#     "devName":"树莓派123",
# 	"sensorOne":"37",
# 	"sensorTwo":"20",
# 	"sensorThree":"30",
# 	"sensorFour":"0",
# 	"sensorFive":"601"
# }
# values = urllib.parse.urlencode(data).encode(encoding='UTF8')
# headers = {'Content-Type': 'application/json'}
# print(data)
# print(values)
# print(json.dumps(data))
# print(json.dumps(data).encode())
# request = urllib.request.Request(url=URL, headers=headers, data=json.dumps(data).encode())
# response = urllib.request.urlopen(request)
# print(response.read())
def sendToSensor(url, data):
	values = urllib.parse.urlencode(data).encode(encoding='utf-8')
	headers = {'Content-Type': 'application/json'}
	request = urllib.request.Request(
        url=URL, headers=headers, data=json.dumps(data).encode())
	response = urllib.request.urlopen(request)
	print(response.read().decode('utf-8'))


def getData():
		headers = {'Content-Type': 'application/json'}
		url = 'http://localhost:8888/api/data/1935fa43fe6c4f56b63d1fd8c2866e4e/树莓派123'
		url = urllib.parse.quote(url,safe=string.printable)
		request = urllib.request.Request(
        	url=url, headers=headers)
		response = urllib.request.urlopen(request)
		print(response.read().decode('utf-8'))
if __name__ == '__main__':
    	getData()
    # while 1:
    #     data = "29,29,30,32,11"
    #     data = data.split(",")
    #     data = {
    #         "devName": DEV_NAME,
    #         "sensorOne": data[0],
    #         "sensorTwo": data[1],
    #         "sensorThree": data[2],
    #         "sensorFour": data[3],
    #         "sensorFive": data[4]
    #     }
    #     sendToSensor(URL, data)
    #     time.sleep(2)
    # print(111)
